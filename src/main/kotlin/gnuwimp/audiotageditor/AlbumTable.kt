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
 *               _ _                  _______    _     _
 *         /\   | | |                |__   __|  | |   | |
 *        /  \  | | |__  _   _ _ __ ___ | | __ _| |__ | | ___
 *       / /\ \ | | '_ \| | | | '_ ` _ \| |/ _` | '_ \| |/ _ \
 *      / ____ \| | |_) | |_| | | | | | | | (_| | |_) | |  __/
 *     /_/    \_\_|_.__/ \__,_|_| |_| |_|_|\__,_|_.__/|_|\___|
 *
 *
 */

/**
 * Table for album data.
 */
class AlbumTable : LayoutPanel(size = Swing.defFont.size / 2 + 1) {
    private val colSelect      = 0
    private val colTrack       = 1
    private val colArtist      = 2
    private val colAlbum       = 3
    private val colTitle       = 4
    private val colGenre       = 5
    private val colAlbumArtist = 6
    private val table          = DataTable()
    private val filterButton   = JButton(Constants.LABEL_FILTER)
    private val selectButton   = JButton(Constants.LABEL_SELECT_ALL)
    private val unselectButton = JButton(Constants.LABEL_SELECT_NONE)

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

        add(scroll,         x =   1, y =  1, w =  -1, h = -6)
        add(filterButton,   x = -57, y = -5, w =  18, h =  4)
        add(selectButton,   x = -38, y = -5, w =  18, h =  4)
        add(unselectButton, x = -19, y = -5, w =  18, h =  4)

        filterButton.toolTipText   = Constants.TOOL_SELECT_ALBUM
        selectButton.toolTipText   = Constants.TOOL_SELECT_ALL
        unselectButton.toolTipText = Constants.TOOL_SELECT_NONE

        /**
         * Search and select tracks.
         */
        filterButton.addActionListener {
            val answer = InputDialog.ask(label = Constants.MESSAGE_ASK_FILTER_ALBUM, def = OkCancel.OK)

            if (answer.isNullOrBlank() == false) {
                Data.filterOnAlbums(answer)
            }
        }

        /**
         * Select all tracks.
         */
        selectButton.addActionListener {
            Data.selectAll(true)
        }

        /**
         * Unselect all tracks.
         */
        unselectButton.addActionListener {
            Data.selectAll(false)
        }

        /**
         * Create data model for table, show album info, one row per track.
         */
        table.model = object : AbstractTableModel() {
            override fun getColumnClass(column: Int) = when (column) {
                colSelect -> java.lang.Boolean::class.java
                else -> java.lang.String::class.java
            }

            /**
             *
             */
            override fun getColumnCount(): Int = 7

            /**
             *
             */
            override fun getColumnName(column: Int): String = when (column) {
                colSelect -> Constants.LABEL_SELECT
                colTrack -> Constants.LABEL_TRACK
                colArtist -> Constants.LABEL_ARTIST
                colAlbum -> Constants.LABEL_ALBUM
                colTitle -> Constants.LABEL_TITLE
                colGenre -> Constants.LABEL_GENRE
                colAlbumArtist -> Constants.LABEL_ALBUM_ARTIST
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
                        colArtist -> return track.artist
                        colAlbum -> return track.album
                        colTitle -> return track.title
                        colGenre -> return track.genre
                        colAlbumArtist -> return track.albumArtist
                    }
                }

                return "!"
            }

            /**
             *
             */
            override fun isCellEditable(row: Int, column: Int): Boolean = true

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
                    else {
                        val value2 = value as String

                        if (column == colTrack && value2.numOrZero in 1..9999 && value2 != track.track) {
                            track.track = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                        else if (column == colArtist && value2 != track.artist) {
                            track.artist = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                        else if (column == colAlbum && value2 != track.album) {
                            track.album = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                        else if (column == colTitle && value2 != track.title) {
                            track.title = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                        else if (column == colGenre && value2 != track.genre) {
                            track.genre = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                        else if (column == colAlbumArtist && value2 != track.albumArtist) {
                            track.albumArtist = value2
                            Data.sendUpdate(TrackEvent.ITEM_DIRTY)
                        }
                    }
                }
            }
        }

        table.tableHeader.toolTipText = Constants.TOOL_TABLE_HEAD
        table.setColumnWidth(colSelect,      min = 75, pref =  75, max =   100)
        table.setColumnWidth(colTrack,       min = 75, pref =  75, max =   100)
        table.setColumnWidth(colArtist,      min = 75, pref = 300, max = 10000)
        table.setColumnWidth(colAlbum,       min = 75, pref = 300, max = 10000)
        table.setColumnWidth(colTitle,       min = 75, pref = 300, max = 10000)
        table.setColumnWidth(colGenre,       min = 75, pref = 200, max = 10000)
        table.setColumnWidth(colAlbumArtist, min = 75, pref =  75, max = 10000)
        table.setShowGrid(false)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        table.setColumnAlign(colTrack, SwingConstants.CENTER)
        table.setFontSizeForEditor(colArtist)
        table.setFontSizeForEditor(colAlbum)
        table.setFontSizeForEditor(colTitle)
        table.setFontSizeForEditor(colGenre)
        table.setFontSizeForEditor(colAlbumArtist)

        /**
         * Enable table header for receiving mouse clicks so data can be sorted.
         */
        table.tableHeader.addMouseListener(object : TableHeader() {
            override fun mouseClicked(event: MouseEvent?) {
                when (columnIndex(event)) {
                    colSelect -> Data.sortTracks(sortTracksOn = Data.Sort.SELECTED, descending = isControlDown(event))
                    colTrack -> Data.sortTracks(sortTracksOn = Data.Sort.TRACK, descending = isControlDown(event))
                    colArtist -> Data.sortTracks(sortTracksOn = Data.Sort.ARTIST, descending = isControlDown(event))
                    colAlbum -> Data.sortTracks(sortTracksOn = Data.Sort.ALBUM, descending = isControlDown(event))
                    colTitle -> Data.sortTracks(sortTracksOn = Data.Sort.TITLE, descending = isControlDown(event))
                    colGenre -> Data.sortTracks(sortTracksOn = Data.Sort.GENRE, descending = isControlDown(event))
                    colAlbumArtist -> Data.sortTracks(sortTracksOn = Data.Sort.ALBUM_ARTIST, descending = isControlDown(event))
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
                        repaint()
                    }
                    TrackEvent.ITEM_IMAGE -> {
                    }
                    TrackEvent.ITEM_SELECTED -> {
                        table.selectRow = Data.selectedRow
                    }
                    TrackEvent.LIST_UPDATED -> {
                        selectButton.isEnabled   = Data.tracks.isNotEmpty()
                        unselectButton.isEnabled = Data.tracks.isNotEmpty()
                        filterButton.isEnabled   = Data.tracks.isNotEmpty()

                        cancelEditing()
                        table.fireModel()
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
}
