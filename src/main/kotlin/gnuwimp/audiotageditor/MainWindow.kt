/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.StatusBar
import gnuwimp.swing.Swing
import gnuwimp.swing.fontForAll
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.prefs.Preferences
import javax.swing.*
import kotlin.system.exitProcess

/**
 * Main window object.
 * Show panel on the left with directory tree and global buttons.
 * And four tab widgets to the right.
 * A splitter is between tree and tab container widget.
 */
class MainWindow(val pref: Preferences, title: String = Constants.APP_NAME) : JFrame(title) {
    private val albumOptions = gnuwimp.audiotageditor.album.Options(pref)
    private val albumTab     = JSplitPane()
    private val albumTable   = gnuwimp.audiotageditor.album.Table()
    private val fileOptions  = gnuwimp.audiotageditor.file.Options()
    private val fileTab      = JSplitPane()
    private val fileTable    = gnuwimp.audiotageditor.file.Table()
    private val leftPanel    = LeftPanel(pref)
    private val main         = JPanel()
    private val splitPane    = JSplitPane()
    private val statusBar    = StatusBar()
    private val tabs         = JTabbedPane()
    private val titleOptions = gnuwimp.audiotageditor.title.Options()
    private val titleTab     = JSplitPane()
    private val titleTable   = gnuwimp.audiotageditor.title.Table()
    private val trackOptions = gnuwimp.audiotageditor.track.Options(pref)
    private val trackTab     = JSplitPane()
    private val trackTable   = gnuwimp.audiotageditor.track.Table()
    private var oldTabIndex  = 0
    private var savedState   = false

    /**
     * Get or set selected tab.
     * Setting index forces redraw.
     */
    private var selectedTab: Int
        get() = oldTabIndex

        set(value) {
            oldTabIndex = value

            if (value != tabs.selectedIndex) {
                tabs.selectedIndex = value
            }

            tabs.invalidate()
            tabs.selectedComponent.invalidate()
        }

    init {
        defaultCloseOperation    = DO_NOTHING_ON_CLOSE
        iconImage                = Main.icon
        contentPane              = main
        Data.messageFunc         = { value: String -> statusBar.message = value }
        main.layout              = BorderLayout()
        splitPane.leftComponent  = leftPanel
        splitPane.rightComponent = tabs
        trackTab.leftComponent   = trackOptions
        trackTab.rightComponent  = trackTable
        fileTab.leftComponent    = fileOptions
        fileTab.rightComponent   = fileTable
        albumTab.leftComponent   = albumOptions
        albumTab.rightComponent  = albumTable
        titleTab.leftComponent   = titleOptions
        titleTab.rightComponent  = titleTable

        tabs.border = BorderFactory.createEmptyBorder(5, 3, 5, 3)
        tabs.addTab(Constants.LABEL_TAB_TRACK, null, trackTab, Constants.TOOL_TAB_TRACK)
        tabs.addTab(Constants.LABEL_TAB_FILE, null, fileTab, Constants.TOOL_TAB_FILE)
        tabs.addTab(Constants.LABEL_TAB_TITLE, null, titleTab, Constants.TOOL_TAB_TITLE)
        tabs.addTab(Constants.LABEL_TAB_ALBUM, null, albumTab, Constants.TOOL_TAB_ALBUM)

        main.add(splitPane, BorderLayout.CENTER)
        main.add(statusBar, BorderLayout.SOUTH)

        pack()

        /**
         * Make certain that every time a tab has been switched that changed data have been saved or discarded, ask user to apply changes also.
         * If cell editor is active it will be canceled.
         */
        tabs.addChangeListener { changeEvent ->
            val tab = changeEvent.source as JTabbedPane

            if (trackTable.isEditing == true) {
                trackTable.cancelEditing()
            }
            else if (fileTable.isEditing == true) {
                fileTable.cancelEditing()
            }
            else if (titleTable.isEditing == true) {
                titleTable.cancelEditing()
            }
            else if (albumTable.isEditing == true) {
                albumTable.cancelEditing()
            }

            if (selectedTab != tab.selectedIndex) {
                val currentRow = Data.selectedRow

                when {
                    Data.isAnyChangedAndSelected -> when (JOptionPane.showConfirmDialog(Main.window, Constants.MESSAGE_ASK_SAVE_HTML, Constants.DIALOG_SAVE, JOptionPane.YES_NO_CANCEL_OPTION)) {
                        Constants.YES -> {
                            selectedTab = if (Data.saveTracks()) tab.selectedIndex else selectedTab
                        }
                        Constants.NO -> {
                            Data.copyTagsFromAudio()
                            Data.sendUpdate(TrackEvent.LIST_UPDATED)
                            selectedTab = tab.selectedIndex
                        }
                        Constants.CANCEL -> selectedTab = selectedTab
                    }
                    Data.isAnyChanged -> {
                        Data.copyTagsFromAudio()
                        Data.sendUpdate(TrackEvent.LIST_UPDATED)
                        selectedTab = tab.selectedIndex
                    }
                    else -> {
                        selectedTab = tab.selectedIndex
                    }
                }

                Data.selectedRow = currentRow
            }
        }

        /**
         * Quit application.
         */
        addWindowListener( object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                quit()
            }
        })
    }

    /**
     * Load preferences from default OS place.
     */
    fun prefLoad() {
        val w = pref.winWidth
        val h = pref.winHeight
        var x = pref.winX
        var y = pref.winY
        val sc = Toolkit.getDefaultToolkit().screenSize

        if (x > sc.getWidth() || x < -50) {
            x = 0
        }

        if (y > sc.getHeight() || y < -50) {
            y = 0
        }

        setLocation(x, y)
        setSize(w, h)

        preferredSize = Dimension(w, h)
        fontForAll    = Swing.defFont

        if (pref.winMax == true) {
            extendedState = MAXIMIZED_BOTH
        }

        splitPane.dividerLocation = pref.splitDir
        trackTab.dividerLocation  = pref.SplitTrack
        fileTab.dividerLocation   = pref.SplitFile
        titleTab.dividerLocation  = pref.SplitTitle
        albumTab.dividerLocation  = pref.splitAlbum

        validate()
        leftPanel.restore(pref.lastPath)
        Data.sendUpdate(TrackEvent.LIST_UPDATED)
    }

    /**
     * Save preferences to default OS place.
     */
    private fun prefSave() {
        try {
            if (savedState == false) {
                val size = size
                val pos  = location

                pref.lastPath   = leftPanel.currentPath
                pref.winWidth   = size.width
                pref.winHeight  = size.height
                pref.winX       = pos.x
                pref.winY       = pos.y
                pref.winMax     = (extendedState and MAXIMIZED_BOTH != 0)
                pref.splitDir   = splitPane.dividerLocation
                pref.SplitTrack = trackTab.dividerLocation
                pref.SplitFile  = fileTab.dividerLocation
                pref.SplitTitle = titleTab.dividerLocation
                pref.splitAlbum = albumTab.dividerLocation

                pref.flush()
                savedState = true
            }
        }
        catch (e: Exception) {
        }
    }

    /**
     * Quit but ask to save if data has been changed.
     */
    fun quit() {
        if (Data.isAnyChangedAndSelected == true && JOptionPane.showConfirmDialog(Main.window, Constants.MESSAGE_ASK_SAVE_HTML, Constants.DIALOG_SAVE, JOptionPane.YES_NO_OPTION) == Constants.YES && Data.saveTracks() == false) {
            JOptionPane.showMessageDialog(Main.window, Constants.ERROR_SAVE_HTML, Constants.DIALOG_SAVE_FAILED, JOptionPane.ERROR_MESSAGE)
        }
        else {
            prefSave()
            isVisible = false
            dispose()
            exitProcess(status = 0)
        }
    }
}
