/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.*
import java.awt.Cursor
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JSeparator

/***
 *      _______             _     ____        _   _
 *     |__   __|           | |   / __ \      | | (_)
 *        | |_ __ __ _  ___| | _| |  | |_ __ | |_ _  ___  _ __  ___
 *        | | '__/ _` |/ __| |/ / |  | | '_ \| __| |/ _ \| '_ \/ __|
 *        | | | | (_| | (__|   <| |__| | |_) | |_| | (_) | | | \__ \
 *        |_|_|  \__,_|\___|_|\_\\____/| .__/ \__|_|\___/|_| |_|___/
 *                                     | |
 *                                     |_|
 */

/**
 * Create a panel with input widgets for all track properties.
 */
class TrackOptions() : LayoutPanel(size = Swing.defFont.size / 2 + 1) {
    private val albumLabel        = JLabel(Constants.LABEL_ALBUM)
    private val albumArtistLabel  = JLabel(Constants.LABEL_ALBUM_ARTIST)
    private val albumArtistInput  = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.albumArtist) { track.albumArtist = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val albumInput        = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.album) { track.album = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val artistLabel       = JLabel(Constants.LABEL_ARTIST)
    private val artistInput       = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.artist) { track.artist = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val commentLabel      = JLabel(Constants.LABEL_COMMENT)
    private val commentInput      = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.comment) { track.comment = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val composerLabel     = JLabel(Constants.LABEL_COMPOSER)
    private val composerInput     = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.composer) { track.composer = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val coverLabel        = JLabel(Constants.LABEL_COVER)
    private val coverIcon         = JLabel()
    private val coverSize         = JLabel()
    private val encoderLabel      = JLabel(Constants.LABEL_ENCODER)
    private val encoderInput      = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.encoder) { track.encoder = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val filenameLabel     = JLabel(Constants.LABEL_FILENAME)
    private val filenameInput     = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.fileName) { track.fileName = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val filepath          = JLabel()
    private val filepathLabel     = JLabel(Constants.LABEL_FILEPATH)
    private val genreGroupLabel   = JLabel(Constants.LABEL_GENRE_GROUP)
    private val genreGroupCombo   = ComboBox<String>(strings = Genre.GROUPS, callback = { genreCombo.setStrings(strings = Genre.get(it), selected = -1) })
    private val genreLabel        = JLabel(Constants.LABEL_GENRE)
    private val genreCombo        = ComboBox<String>(strings = Genre.get(""), callback = { val track = Data.selectedTrack; if (track != null && it != track.genre) { track.genre = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val loadCoverButton   = JButton(Constants.LABEL_LOAD_IMAGE)
    private val removeCoverButton = JButton(Constants.LABEL_REMOVE_COVER)
    private val resizeCoverButton = JButton(Constants.LABEL_RESIZE_COVER)
    private val saveButton        = JButton(Constants.LABEL_SAVE)
    private val separator         = JSeparator()
    private val titleLabel        = JLabel(Constants.LABEL_TITLE)
    private val titleInput        = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.title) { track.title = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val trackLabel        = JLabel(Constants.LABEL_TRACK)
    private val trackInput        = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.track) { track.track = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val undoButton        = JButton(Constants.LABEL_UNDO)
    private val yearLabel         = JLabel(Constants.LABEL_YEAR)
    private val yearInput         = TextField(callback = { val track = Data.selectedTrack; if (track != null && it != track.year) { track.year = it ; Data.sendUpdate(TrackEvent.ITEM_DIRTY) } })
    private val activateWidgets: List<JComponent> = listOf(artistInput, albumInput, albumArtistInput, titleInput, yearInput, genreGroupCombo, genreCombo, trackInput, commentInput, composerInput, encoderInput, filenameInput, loadCoverButton)

    /**
     *
     */
    init {
        val lw = 16
        val rw = 18
        var yp = 1

        add(artistLabel,        x = 1,  y = yp, w = lw, h = 4)
        add(artistInput,        x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(albumLabel,         x = 1,  y = yp, w = lw, h = 4)
        add(albumInput,         x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(albumArtistLabel,   x = 1,  y = yp, w = lw, h = 4)
        add(albumArtistInput,   x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(titleLabel,         x = 1,  y = yp, w = lw, h = 4)
        add(titleInput,         x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(yearLabel,          x = 1,  y = yp, w = lw, h = 4)
        add(yearInput,          x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(genreGroupLabel,    x = 1,  y = yp, w = lw, h = 4)
        add(genreGroupCombo,    x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(genreLabel,         x = 1,  y = yp, w = lw, h = 4)
        add(genreCombo,         x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(trackLabel,         x = 1,  y = yp, w = lw, h = 4)
        add(trackInput,         x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(commentLabel,       x = 1,  y = yp, w = lw, h = 4)
        add(commentInput,       x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(composerLabel,      x = 1,  y = yp, w = lw, h = 4)
        add(composerInput,      x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(encoderLabel,       x = 1,  y = yp, w = lw, h = 4)
        add(encoderInput,       x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(filenameLabel,      x = 1,  y = yp, w = lw, h = 4)
        add(filenameInput,      x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(filepathLabel,      x = 1,  y = yp, w = lw, h = 4)
        add(filepath,           x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(coverLabel,         x = 1,  y = yp, w = lw, h = 4)
        add(coverSize,          x = rw, y = yp, w = -1, h = 4)

        yp += 5
        add(coverIcon,          x = rw, y = yp, w = -1, h = (240 / Swing.defFont.size) * 2)

        yp = -28
        add(loadCoverButton,    x = 1,  y = yp, w = -1, h = 4)

        yp += 5
        add(resizeCoverButton,  x = 1,  y = yp, w = -1, h = 4)

        yp += 5
        add(removeCoverButton,  x = 1,  y = yp, w = -1, h = 4)

        yp += 6
        add(separator,          x = 1,  y = yp, w = -1, h = 1)

        yp += 2
        add(undoButton,         x = 1,  y = yp, w = -1, h = 4)

        yp += 5
        add(saveButton,         x = 1,  y = yp, w = -1, h = 4)

        coverIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
        coverIcon.horizontalAlignment = JLabel.LEFT
        coverIcon.toolTipText         = Constants.TOOL_COVER_SHOW
        genreCombo.isEditable         = true
        loadCoverButton.toolTipText   = Constants.TOOL_LOAD_IMAGE
        removeCoverButton.toolTipText = Constants.TOOL_DELETE_COVER
        resizeCoverButton.toolTipText = Constants.TOOL_RESIZE_COVER
        saveButton.toolTipText        = Constants.TOOL_SAVE
        undoButton.toolTipText        = Constants.TOOL_UNDO
        yearInput.toolTipText         = Constants.TOOL_YEAR

        setIcon(null)
        setButtons(null)

        /**
         * Show full version of cover image.
         */
        coverIcon.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                Data.showCover()
            }
        })

        /**
         * Load cover image from file.
         */
        loadCoverButton.addActionListener {
            Data.loadImageForCurrentTrack(Main.pref)
            Main.window.focusTrackTable()
        }

        /**
         * Remove cover image but don't save changes.
         */
        removeCoverButton.addActionListener {
            Data.removeImage()
            Main.window.focusTrackTable()
        }

        /**
         * Ask user for resize size and scale image but don't save it.
         */
        resizeCoverButton.addActionListener {
            Data.resizeImageForCurrentTrack()
            Main.window.focusTrackTable()
        }

        /**
         * Commit changes to file.
         */
        saveButton.addActionListener {
            Data.saveTracks()
        }

        /**
         * Reload tags from file.
         */
        undoButton.addActionListener {
            val answer = MessageDialog.askOkCancel(label = Constants.MESSAGE_ASK_UNDO)

            if (answer == YesNoCancel.YES) {
                Data.copyTagsFromAudio()
                Data.sendUpdate(TrackEvent.LIST_UPDATED)
            }
        }

        /**
         *
         */
        Data.addListener(object : TrackListener {
            override fun update(event: TrackEvent) {
                val track = Data.selectedTrack

                when (event) {
                    TrackEvent.ITEM_DIRTY -> {
                        setButtons(track)
                    }
                    TrackEvent.ITEM_IMAGE -> {
                        setButtons(track)
                        setIcon(track)
                    }
                    TrackEvent.ITEM_SELECTED -> {
                        setButtons(track)
                        setFields(track)
                        setIcon(track)
                    }
                    TrackEvent.LIST_UPDATED -> {
                        setButtons(track)
                        setFields(track)
                        setIcon(track)
                    }
                }
            }
        })
    }

    /**
     *
     */
    fun setButtons(track: Track?) {
        saveButton.isEnabled        = Data.isAnyChangedAndSelected
        undoButton.isEnabled        = saveButton.isEnabled
        loadCoverButton.isEnabled   = track != null
        resizeCoverButton.isEnabled = track != null && track.cover != Constants.COVER_REMOVED

        activateWidgets.forEach { widget ->
            widget.isEnabled = track != null
        }
    }

    /**
     *
     */
    fun setFields(track: Track?) {
        genreCombo.selectedIndex = -1

        if (track != null) {
            artistInput.text      = track.artist
            albumInput.text       = track.album
            albumArtistInput.text = track.albumArtist
            titleInput.text       = track.title
            yearInput.text        = track.year
            genreCombo.text       = track.genre
            trackInput.text       = track.track
            commentInput.text     = track.comment
            composerInput.text    = track.composer
            encoderInput.text     = track.encoder
            filenameInput.text    = track.fileName
            filepath.text         = track.file.parent
        }
        else {
            artistInput.text      = ""
            albumInput.text       = ""
            albumArtistInput.text = ""
            titleInput.text       = ""
            yearInput.text        = ""
            genreCombo.text       = ""
            trackInput.text       = ""
            commentInput.text     = ""
            composerInput.text    = ""
            encoderInput.text     = ""
            filenameInput.text    = ""
            filepath.text         = ""
        }
    }

    /**
     * Copy image from track to icon and enable buttons.
     */
    fun setIcon(track: Track?) {
        saveButton.isEnabled = Data.isAnyChangedAndSelected

        if (track != null) {
            val (icon, label)           = track.coverIcon
            removeCoverButton.isEnabled = track.cover != Constants.COVER_REMOVED
            resizeCoverButton.isEnabled = track.cover != Constants.COVER_REMOVED
            coverIcon.icon              = icon
            coverSize.text              = label
        }
        else {
            removeCoverButton.isEnabled = false
            resizeCoverButton.isEnabled = false
            coverIcon.icon              = Data.loadIconFromPath().first
            coverSize.text              = ""
        }

        coverIcon.repaint()
    }
}
