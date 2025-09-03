/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.*
import javax.swing.JButton
import javax.swing.JSeparator

/***
 *      _           __ _   _____                 _
 *     | |         / _| | |  __ \               | |
 *     | |     ___| |_| |_| |__) |_ _ _ __   ___| |
 *     | |    / _ \  _| __|  ___/ _` | '_ \ / _ \ |
 *     | |___|  __/ | | |_| |  | (_| | | | |  __/ |
 *     |______\___|_|  \__|_|   \__,_|_| |_|\___|_|
 *
 *
 */

/**
 * Show a directory tree and some buttons
 * Load tracks when directory changes
 */
class LeftPanel() : LayoutPanel(size = Swing.defFont.size / 2 + 1), DirListener {

    private val aboutButton     = JButton(Constants.LABEL_ABOUT)
    private val logButton       = JButton(Constants.LABEL_SHOW_LOG)
    private val quitButton      = JButton(Constants.LABEL_QUIT)
    private val reloadButton    = JButton(Constants.LABEL_RELOAD_DIR)
    private val reloadRecButton = JButton(Constants.LABEL_RELOAD_DIR_REC)
    private val separator       = JSeparator()
    private val setupButton     = JButton(Constants.LABEL_SETUP)
    private var path            = ""
    private val dirTree         = DirTree(dirListener = this)

    /**
     * Get current directory
     */
    val currentPath: String get() = path

    init {
        add(dirTree,         x = 1, y =   1, w = -1, h = -34)
        add(reloadButton,    x = 1, y = -33, w = -1, h =   4)
        add(reloadRecButton, x = 1, y = -28, w = -1, h =   4)
        add(logButton,       x = 1, y = -23, w = -1, h =   4)
        add(separator,       x = 1, y = -17, w = -1, h =   1)
        add(setupButton,     x = 1, y = -15, w = -1, h =   4)
        add(aboutButton,     x = 1, y = -10, w = -1, h =   4)
        add(quitButton,      x = 1, y =  -5, w = -1, h =   4)

        aboutButton.toolTipText  = Constants.TOOL_ABOUT
        logButton.toolTipText    = Constants.TOOL_SHOW_LOG
        quitButton.toolTipText   = Constants.TOOL_QUIT
        reloadButton.toolTipText = Constants.TOOL_RELOAD
        reloadRecButton.toolTipText = Constants.TOOL_RELOAD_RECURSIVE

        /**
         * Show about dialog box.
         */
        aboutButton.addActionListener {
            AboutHandler(Constants.DIALOG_ABOUT, Constants.aboutApp()).show(parent = Main.window, width = Swing.defFont.size * 60, height = Swing.defFont.size * 50)
        }

        /**
         * Show log dialog.
         */
        logButton.addActionListener {
            val dialog = TextDialog(text = Swing.logMessage, showLastLine = true, title = Constants.DIALOG_LOG, parent = Main.window)
            dialog.isVisible = true
        }

        /**
         * Quit application.
         */
        quitButton.addActionListener {
            Main.window.quit()
        }

        /**
         * Reload current directory.
         */
        reloadButton.addActionListener {
            if (Data.saveChangedAskFirst(displayErrorDialogOnSave = false, abortOnFailedSave = true)) {
                Data.loadTracks(newPath = Data.path)
            }
        }

        /**
         * Reload current directory and all sub directories.
         */
        reloadRecButton.addActionListener {
            if (Data.saveChangedAskFirst(displayErrorDialogOnSave = false, abortOnFailedSave = true)) {
                Data.loadTracks(newPath = Data.path, recursive = true)
            }
        }

        /**
         * Show setup dialog.
         */
        setupButton.addActionListener {
            val dialog = SetupDialog(parent = Main.window, label = "AudioTagEditor", panels = listOf(SetupApp()), width = Swing.defFont.size * 40, height = Swing.defFont.size * 20)
            dialog.isVisible = true
        }
    }

    /**
     *
     */
    fun enableTree(value: Boolean) {
        dirTree.enableTree(value)
    }

    /**
     * Restore path in tree so it is selected.
     */
    fun restore(path: String) {
        dirTree.restore(path)
    }

    /**
     * Callback for path changes in directory tree.
     * If data has been changed ask for saving.
     * Path must be changed.
     */
    override fun pathChanged(path: String) {
        if (Data.saveChangedAskFirst(displayErrorDialogOnSave = true, abortOnFailedSave = false) == true) {
            this.path = path

            Data.loadTracks(newPath = path)
        }
    }

    /**
     * Set tooltip text for the tree.
     */
    fun toolTipText(value: String) {
        dirTree.toolTipText(value)
    }
}
