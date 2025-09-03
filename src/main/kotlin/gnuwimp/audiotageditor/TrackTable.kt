/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.*
import gnuwimp.util.numOrZero
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JScrollPane
import javax.swing.ListSelectionModel
import javax.swing.SwingConstants
import javax.swing.table.AbstractTableModel

/***
 *      _______             _ _______    _     _
 *     |__   __|           | |__   __|  | |   | |
 *        | |_ __ __ _  ___| | _| | __ _| |__ | | ___
 *        | | '__/ _` |/ __| |/ / |/ _` | '_ \| |/ _ \
 *        | | | | (_| | (__|   <| | (_| | |_) | |  __/
 *        |_|_|  \__,_|\___|_|\_\_|\__,_|_.__/|_|\___|
 *
 *
 */

/**
 * Create a table with track data.
 */
class TrackTable : LayoutPanel(size = Swing.defFont.size / 2 + 1) {
    private val colSelect         = 0
    private val colTrack          = 1
    private val colFile           = 2
    private val colFormat         = 3
    private val colBitrate        = 4
    private val colTime           = 5
    private val deleteFilesButton = JButton(Constants.LABEL_DELETE_TRACKS)
    private val deleteTagsButton  = JButton(Constants.LABEL_DELETE_TAGS)
    private val filterButton      = JButton(Constants.LABEL_FILTER)
    private val moveDownButton    = JButton(Constants.LABEL_MOVE_DOWN)
    private val moveUpButton      = JButton(Constants.LABEL_MOVE_UP)
    private val resizeButton      = JButton(Constants.LABEL_RESIZE_ALL)
    private val selectButton      = JButton(Constants.LABEL_SELECT_ALL)
    private val table             = DataTable()
    private val unselectButton    = JButton(Constants.LABEL_SELECT_NONE)

    /**
     * Return true if table is in edit mode.
     */
    val isEditing: Boolean
        get() = table.isEditing

    /**
     *
     */
    init {
        val scroll = JScrollPane()

        scroll.viewport.view = table

        add(scroll,             x =    1, y =  1, w = -1, h = -6)
        add(resizeButton,       x = -152, y = -5, w = 18, h =  4)
        add(deleteFilesButton,  x = -133, y = -5, w = 18, h =  4)
        add(deleteTagsButton,   x = -114, y = -5, w = 18, h =  4)
        add(filterButton,       x =  -95, y = -5, w = 18, h =  4)
        add(selectButton,       x =  -76, y = -5, w = 18, h =  4)
        add(unselectButton,     x =  -57, y = -5, w = 18, h =  4)
        add(moveDownButton,     x =  -38, y = -5, w = 18, h =  4)
        add(moveUpButton,       x =  -19, y = -5, w = 18, h =  4)

        deleteTagsButton.isEnabled    = false
        deleteTagsButton.toolTipText  = Constants.TOOL_DELETE_TAGS
        deleteFilesButton.isEnabled   = false
        deleteFilesButton.toolTipText = Constants.TOOL_DELETE_TRACKS
        filterButton.toolTipText      = Constants.TOOL_SELECT_TITLE
        moveDownButton.isEnabled      = false
        moveDownButton.toolTipText    = Constants.TOOL_MOVE_DOWN
        resizeButton.toolTipText      = Constants.TOOL_RESIZE_ALL
        selectButton.toolTipText      = Constants.TOOL_SELECT_ALL
        unselectButton.toolTipText    = Constants.TOOL_SELECT_NONE
        moveUpButton.isEnabled        = false
        moveUpButton.toolTipText      = Constants.TOOL_MOVE_UP

        /**
         * Remove all tags from all selected tracks.
         */
        deleteTagsButton.addActionListener {
            val answer = InputDialog.ask(label = Constants.MESSAGE_ASK_DELETE_TAGS)

            if (answer != null && answer == "YES") {
                Data.removeTagsForAll()
            }

            focus()
        }

        /**
         *
         */
        deleteFilesButton.addActionListener {
            val answer = InputDialog.ask(label = Constants.MESSAGE_ASK_DELETE_FILES)

            if (answer != null && answer == "YES") {
                Data.deleteTracks()
            }

            focus()
        }

        /**
         * Search and select tracks.
         */
        filterButton.addActionListener {
            val answer = InputDialog.ask(label = Constants.MESSAGE_ASK_FILTER_TITLE, def = OkCancel.OK)

            if (answer.isNullOrBlank() == false) {
                Data.filterOnTitles(answer)
            }
        }

        /**
         * Move current selected row down in track list.
         */
        moveDownButton.addActionListener {
            Data.moveRowDown()
        }

        /**
         * Select all tracks.
         */
        selectButton.addActionListener {
            Data.selectAll(true)
            focus()
        }

        /**
         * Unselect all tracks.
         */
        unselectButton.addActionListener {
            Data.selectAll(false)
            focus()
        }

        /**
         * Move current selected row down up track list.
         */
        moveUpButton.addActionListener {
            Data.moveRowUp()
        }

        /**
         * Resize all selected tracks.
         */
        resizeButton.addActionListener {
            Data.resizeImageForSelectedTracks()
            focus()
        }

        /**
         * Create data model for table, show list of file names and file properties.
         */
        table.model = object : AbstractTableModel() {
            override fun getColumnClass(column: Int) = when (column) {
                colSelect -> java.lang.Boolean::class.java
                else -> java.lang.String::class.java
            }

            /**
             *
             */
            override fun getColumnCount(): Int = 6

            /**
             *
             */
            override fun getColumnName(column: Int): String = when (column) {
                colSelect -> Constants.LABEL_SELECT
                colTrack -> Constants.LABEL_TRACK
                colFile -> Constants.LABEL_FILE
                colFormat -> Constants.LABEL_FORMAT
                colBitrate -> Constants.LABEL_BITRATE
                colTime -> Constants.LABEL_TIME
                else -> "!"
            }

            /**
             *
             */
            override fun getRowCount(): Int = Data.tracks.size

            /**
             *
             */
            override fun getValueAt(row: Int, column: Int): Any {
                val track = Data.getTrack(row)

                if (track != null) {
                    when (column) {
                        colSelect -> return track.isSelected
                        colTrack -> return track.track
                        colFile -> return track.fileName
                        colFormat -> return track.formatInfo
                        colBitrate -> return track.bitrateInfo
                        colTime -> return track.timeInfo
                    }
                }

                return ""
            }

            /**
             *
             */
            override fun isCellEditable(row: Int, column: Int): Boolean = when(column) {
                colSelect -> true
                colTrack -> true
                colFile -> true
                else -> false
            }

            /**
             *
             */
            override fun setValueAt(value: Any?, row: Int, column: Int) {
                val track = Data.getTrack(row)

                if (track != null) {
                    if (column == colSelect) {
                        track.isSelected = value as Boolean
                        Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                    }
                    else if (column == colTrack) {
                        val num = value as String

                        if (num.numOrZero in 1..9999 && num != track.track) {
                            track.track = num
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                    }
                    else if (column == colFile) {
                        val name = value as String

                        if (track.fileName != name) {
                            track.fileName = name
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                    }
                }
            }
        }

        table.tableHeader.toolTipText = Constants.TOOL_TABLE_HEAD_TRACK
        table.setColumnWidth(colSelect,  min =  75, pref =  75, max =   100)
        table.setColumnWidth(colTrack,   min =  75, pref =  75, max =   100)
        table.setColumnWidth(colFile,    min = 150, pref = 500, max = 10000)
        table.setColumnWidth(colFormat,  min =  75, pref =  75, max =   100)
        table.setColumnWidth(colBitrate, min = 100, pref = 100, max =   125)
        table.setColumnWidth(colTime,    min = 100, pref = 100, max =   125)
        table.setShowGrid(false)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        table.setColumnAlign(colTrack, SwingConstants.CENTER)
        table.setColumnAlign(colBitrate, SwingConstants.RIGHT)
        table.setColumnAlign(colTime, SwingConstants.RIGHT)
        table.setFontSizeForEditor(colFile)

        /**
         * Enable table header for receiving mouse clicks so data can be sorted.
         */
        table.tableHeader.addMouseListener(object : TableHeader() {
            override fun mouseClicked(event: MouseEvent?) {
                when (columnIndex(event)) {
                    colSelect -> Data.sortTracks(sortTracksOn = Data.Sort.SELECTED, descending = isControlDown(event))
                    colTrack -> Data.sortTracks(sortTracksOn = Data.Sort.TRACK, descending = isControlDown(event))
                    colFile -> Data.sortTracks(sortTracksOn = Data.Sort.FILE, descending = isControlDown(event))
                    colFormat -> Data.sortTracks(sortTracksOn = Data.Sort.FORMAT, descending = isControlDown(event))
                    colBitrate -> Data.sortTracks(sortTracksOn = Data.Sort.BITRATE, descending = isControlDown(event))
                    colTime -> Data.sortTracks(sortTracksOn = Data.Sort.TIME, descending = isControlDown(event))
                }

                Data.sendUpdate(TrackEvent.LIST_UPDATED)
            }

        })

        /**
         * If new row has been selected do select row in Data object.
         */
        table.selectionModel.addListSelectionListener { lse ->
            val index = (lse.source as ListSelectionModel).minSelectionIndex

            if (lse.valueIsAdjusting == false && Data.selectedRow != index) {
                Data.selectedRow = index
            }
        }

        /**
         * Listener callback for data changes.
         */
        Data.addListener(object : TrackListener {
            override fun update(event: TrackEvent) {
                when (event) {
                    TrackEvent.ITEM_DIRTY -> {
                        deleteTagsButton.isEnabled  = Data.countSelected > 0
                        deleteFilesButton.isEnabled = Data.countSelected > 0

                        Data.statFunc(Data.stat)
                        repaint()
                    }
                    TrackEvent.ITEM_IMAGE -> {
                    }
                    TrackEvent.ITEM_SELECTED -> {
                        table.selectRow             = Data.selectedRow
                        deleteFilesButton.isEnabled = Data.countSelected > 0
                        moveUpButton.isEnabled      = Data.selectedRow > 0
                        moveDownButton.isEnabled    = (Data.selectedRow > -1 && Data.selectedRow < Data.tracks.size - 1)
                    }
                    TrackEvent.LIST_UPDATED -> {
                        deleteTagsButton.isEnabled  = Data.countSelected > 0
                        deleteFilesButton.isEnabled = Data.countSelected > 0
                        moveUpButton.isEnabled      = Data.selectedRow > 0
                        moveDownButton.isEnabled    = (Data.selectedRow > -1 && Data.selectedRow < Data.tracks.size - 1)

                        cancelEditing()
                        table.fireModel()
                        Data.statFunc(Data.stat)
                    }
                }
             }
        })
    }

    /**
     * Close cell editor, if it is enabled.
     */
    fun cancelEditing() {
        if (table.isEditing == true) {
            table.cellEditor.cancelCellEditing()
        }
    }

    /**
     * Focus table.
     */
    fun focus() {
        table.requestFocus()
    }
}
