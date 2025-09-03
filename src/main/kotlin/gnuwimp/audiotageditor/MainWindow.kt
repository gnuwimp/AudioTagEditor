/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*
import kotlin.system.exitProcess

/***
 *      __  __       _   __          ___           _
 *     |  \/  |     (_)  \ \        / (_)         | |
 *     | \  / | __ _ _ _ _\ \  /\  / / _ _ __   __| | _____      __
 *     | |\/| |/ _` | | '_ \ \/  \/ / | | '_ \ / _` |/ _ \ \ /\ / /
 *     | |  | | (_| | | | | \  /\  /  | | | | | (_| | (_) \ V  V /
 *     |_|  |_|\__,_|_|_| |_|\/  \/   |_|_| |_|\__,_|\___/ \_/\_/
 *
 *
 */

/**
 * Main window object.
 * Show panel on the left with directory tree and global buttons.
 * And four tab widgets to the right.
 * A splitter is between tree and tab container widget.
 */
class MainWindow(title: String = Constants.APP_NAME) : JFrame(title) {
    private val albumOptions = AlbumOptions()
    private val albumTab     = JSplitPane()
    private val albumTable   = AlbumTable()
    private val fileOptions  = FileOptions()
    private val fileTab      = JSplitPane()
    private val fileTable    = FileTable()
    private val leftPanel    = LeftPanel()
    private val main         = JPanel()
    private val centerPane   = JSplitPane()
    private val southPane    = JSplitPane()
    private val statBar      = StatusBar(StatusBar.Align.LEFT)
    private val statusBar    = StatusBar(StatusBar.Align.LEFT)
    private val tabs         = JTabbedPane()
    private val titleOptions = TitleOptions()
    private val titleTab     = JSplitPane()
    private val titleTable   = TitleTable()
    private val trackOptions = TrackOptions()
    private val trackTab     = JSplitPane()
    private val trackTable   = TrackTable()
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

    /**
     *
     */
    init {
        defaultCloseOperation     = DO_NOTHING_ON_CLOSE
        iconImage                 = Main.icon
        contentPane               = main
        main.layout               = BorderLayout()
        albumTab.leftComponent    = albumOptions
        albumTab.rightComponent   = albumTable
        fileTab.leftComponent     = fileOptions
        fileTab.rightComponent    = fileTable
        centerPane.leftComponent  = leftPanel
        centerPane.rightComponent = tabs
        titleTab.leftComponent    = titleOptions
        titleTab.rightComponent   = titleTable
        trackTab.leftComponent    = trackOptions
        trackTab.rightComponent   = trackTable
        Data.messageFunc          = { value: String -> statusBar.errorLabel = value }
        Data.statFunc             = { value: String -> statBar.label = value }

        tabs.border = BorderFactory.createEmptyBorder(5, 3, 5, 3)
        tabs.addTab(Constants.LABEL_TAB_TRACK, null, trackTab, Constants.TOOL_TAB_TRACK)
        tabs.addTab(Constants.LABEL_TAB_FILE, null, fileTab, Constants.TOOL_TAB_FILE)
        tabs.addTab(Constants.LABEL_TAB_TITLE, null, titleTab, Constants.TOOL_TAB_TITLE)
        tabs.addTab(Constants.LABEL_TAB_ALBUM, null, albumTab, Constants.TOOL_TAB_ALBUM)

        southPane.leftComponent = statusBar
        southPane.rightComponent = statBar
        southPane.preferredSize = Dimension(Swing.defFont.size * 10, Swing.defFont.size * 3)

        main.add(centerPane, BorderLayout.CENTER)
        main.add(southPane, BorderLayout.SOUTH)

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

                if (Data.isAnyChangedAndSelected == true) {
                    val answer = MessageDialog.ask(label = Constants.MESSAGE_ASK_SAVE)

                    when (answer) {
                        YesNoCancel.YES -> {
                            selectedTab = if (Data.saveTracks()) tab.selectedIndex else selectedTab
                        }
                        YesNoCancel.NO -> {
                            Data.copyTagsFromAudio()
                            Data.sendUpdate(TrackEvent.LIST_UPDATED)
                            selectedTab = tab.selectedIndex
                        }
                        YesNoCancel.CANCEL -> selectedTab = selectedTab
                    }
                }
                else if (Data.isAnyChanged == true) {
                    Data.copyTagsFromAudio()
                    Data.sendUpdate(TrackEvent.LIST_UPDATED)
                    selectedTab = tab.selectedIndex
                }
                else {
                    selectedTab = tab.selectedIndex
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

        /**
         * Disable directory tree when any track data has been changed.
         */
        Data.addListener(object : TrackListener {
            override fun update(event: TrackEvent) {
                if (Data.isAnyChangedAndSelected == true) {
                    leftPanel.enableTree(false)
                    leftPanel.toolTipText(Constants.TOOL_DIRTREE_DISABLED)
                }
                else {
                    leftPanel.enableTree(true)
                    leftPanel.toolTipText(Constants.TOOL_DIRTREE_ENABLED)
                }
            }
        })

    }

    /**
     * Focus table after a few buttons on track/Option has been pressed.
     */
    fun focusTrackTable() {
        trackTable.focus()
    }


    /**
     * Load preferences from default OS place.
     */
    fun prefLoad() {
        val w = Main.pref.winWidth
        val h = Main.pref.winHeight
        var x = Main.pref.winX
        var y = Main.pref.winY
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

        if (Main.pref.winMax == true) {
            extendedState = MAXIMIZED_BOTH
        }

        albumTab.dividerLocation   = Main.pref.splitAlbum
        centerPane.dividerLocation = Main.pref.splitDir
        fileTab.dividerLocation    = Main.pref.splitFile
        southPane.dividerLocation  = Main.pref.splitStatus
        titleTab.dividerLocation   = Main.pref.splitTitle
        trackTab.dividerLocation   = Main.pref.splitTrack

        validate()
        leftPanel.restore(Main.pref.lastPath)
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

                Main.pref.fontSize    = Swing.defFont.size
                Main.pref.lastPath    = leftPanel.currentPath
                Main.pref.splitAlbum  = albumTab.dividerLocation
                Main.pref.splitDir    = centerPane.dividerLocation
                Main.pref.splitFile   = fileTab.dividerLocation
                Main.pref.splitStatus = southPane.dividerLocation
                Main.pref.splitTitle  = titleTab.dividerLocation
                Main.pref.splitTrack  = trackTab.dividerLocation
                Main.pref.winHeight   = size.height
                Main.pref.winMax      = (extendedState and MAXIMIZED_BOTH != 0)
                Main.pref.winWidth    = size.width
                Main.pref.winX        = pos.x
                Main.pref.winY        = pos.y

                Main.pref.flush()
                savedState = true
            }
        }
        catch (_: Exception) {
        }
    }

    /**
     * Quit but ask to save if data has been changed.
     */
    fun quit() {
        var exit = true

        if (Data.isAnyChangedAndSelected == true) {
            val answer = MessageDialog.ask(label = Constants.MESSAGE_ASK_SAVE)

            exit = when (answer) {
                YesNoCancel.YES -> {
                    if (Data.saveTracks() == false) {
                        MessageDialog.error(label = Constants.ERROR_SAVE)
                        false
                    }
                    else {
                        true
                    }
                }
                YesNoCancel.NO -> true
                YesNoCancel.CANCEL -> false
            }
        }

        if (exit == true) {
            prefSave()
            isVisible = false
            dispose()
            exitProcess(status = 0)
        }
    }
}
