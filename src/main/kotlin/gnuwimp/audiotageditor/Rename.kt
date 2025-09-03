/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.util.*

/**
 * Create a string with formatted number.
 */
fun String.number(formatType: String, seperator: String, number: Int) : String = when (formatType) {
    "insert_1" -> String.format("%d%s%s", number, seperator, this)
    "insert_2" -> String.format("%02d%s%s", number, seperator, this)
    "insert_3" -> String.format("%03d%s%s", number, seperator, this)
    "insert_4" -> String.format("%04d%s%s", number, seperator, this)
    "append_1" -> String.format("%s%s%d", this, seperator, number)
    "append_2" -> String.format("%s%s%02d", this, seperator, number)
    "append_3" -> String.format("%s%s%03d", this, seperator, number)
    "append_4" -> String.format("%s%s%04d", this, seperator, number)
    else -> throw Exception("String.number: internal error")
}

/**
 * Rename album tags.
 */
fun Track.renameAlbum(options: Map<String, String>, index: Int): Int {
    val artist2      = artist
    val album2       = album
    val albumArtist2 = albumArtist
    val genre2       = genre
    val year2        = year
    val comment2     = comment
    val composer2    = composer
    val encoder2     = encoder
    val cover2       = cover
    val track2       = track

    artist      = options.getOrElse(key = "artist") { artist }
    album       = options.getOrElse(key = "album") { album }
    albumArtist = options.getOrElse(key = "album_artist") { albumArtist }
    genre       = options.getOrElse(key = "genre") { genre }
    year        = options.getOrElse(key = "year") { year }
    comment     = options.getOrElse(key = "comment") { comment }
    composer    = options.getOrElse(key = "composer") { composer }
    encoder     = options.getOrElse(key = "encoder") { encoder }
    cover       = options.getOrElse(key = Constants.COVER_EMBEDDED) { cover }

    when (options["copy_artist"]) {
        null -> Unit
        "artist_to_albumartist" ->       albumArtist = artist
        "album_to_albumartist" ->        albumArtist = album
        "artist+album_to_albumartist" -> albumArtist = "$artist - $album"
        "albumartist_to_artist" ->       artist      = albumArtist
        "albumartist_to_album" ->        album       = albumArtist
        else -> Unit
    }

    val num = options["track"]?.numOrZero ?: 0

    if (num > 0) {
        track = "${num + index}"
    }

    return if (artist2 != artist ||
        album2 != album ||
        albumArtist2 != albumArtist ||
        genre2 != genre ||
        year2 != year ||
        comment2 != comment ||
        composer2 != composer ||
        encoder2 != encoder ||
        cover2 != cover ||
        track2 != track)
        1 else 0
}

/**
 * Rename file name.
 */
fun Track.renameFile(options: Map<String, String>): Int {
    var name  = fileName
    var ext   = fileExt
    val name2 = fileName
    val ext2  = fileExt

    if (options["title"] != null) {
        name = title
    }

    if (options["leading"] != null) {
        name = name.removeLeadingNoneLetters
    }

    if (options["trailing"] != null) {
        name = name.removeTrailingNoneLetters
    }

    name = options.getOrElse(key = "set") { name }

    val regex   = options["regex"]
    val remove  = options["remove"]
    val replace = options["replace"]

    if (remove != null && replace == null) {
        name = if (regex != null) {
            name.replace(Regex(remove), "")
        }
        else {
            name.replace(remove, "")
        }
    }
    else if (remove != null && replace != null) {
        name = if (regex != null) {
            name.replace(Regex(remove), replace)
        }
        else {
            name.replace(remove, replace)
        }
    }

    val insertAlbum = options["insert_album"]

    if (insertAlbum != null) {
        name = album + insertAlbum + name
    }

    val insertArtist = options["insert_artist"]

    if (insertArtist != null) {
        name = artist + insertArtist + name
    }

    val insert = options["insert"]

    if (insert != null) {
        name = insert + name
    }

    val append = options["append"]

    if (append != null) {
        name += append
    }

    val capName = options["cap_name"]

    if (capName.isNullOrBlank() == false) {
        name = name.capWords(capName)
    }

    val capExt = options["cap_ext"]

    if (capExt.isNullOrBlank() == false) {
        ext = ext.capWords(capExt)
    }

    val number = options["number"]
    val numberSep = options["number_sep"]

    if (number.isNullOrBlank() == false && numberSep != null) {
        name = name.number(number, numberSep, track.numOrZero.toInt())
    }

    if (options["illegal"] != null) {
        name = name.removeIllegalFileChar
    }

    if (name.isNotEmpty() == true) {
        fileName = name
    }

    fileExt = ext

    return if (fileName != name2 || fileExt != ext2) 1 else 0
}

/**
 * Rename title tag.
 */
fun Track.renameTitle(options: Map<String, String>): Int {
    var title  = this.title
    val title2 = this.title

    if (options["filename"] != null) {
        title = fileName
    }

    if (options["leading"] != null) {
        title = title.removeLeadingNoneLetters
    }

    if (options["trailing"] != null) {
        title = title.removeTrailingNoneLetters
    }

    title = options.getOrElse(key = "set") { title }

    val regex   = options["regex"]
    val remove  = options["remove"]
    val replace = options["replace"]

    if (remove != null && replace == null) {
        title = if (regex != null) {
            title.replace(Regex(remove), "")
        }
        else {
            title.replace(remove, "")
        }
    }
    else if (remove != null && replace != null) {
        title = if (regex != null) {
            title.replace(Regex(remove), replace)
        }
        else {
            title.replace(remove, replace)
        }
    }

    val insertAlbum = options["insert_album"]

    if (insertAlbum != null) {
        title = album + insertAlbum + title
    }

    val insertArtist = options["insert_artist"]

    if (insertArtist != null) {
        title = artist + insertArtist + title
    }

    val insert = options["insert"]

    if (insert != null) {
        title = insert + title
    }

    val append = options["append"]

    if (append != null) {
        title += append
    }

    val capName = options["cap_name"]

    if (capName.isNullOrBlank() == false) {
        title = title.capWords(capName)
    }

    val number    = options["number"]
    val numberSep = options["number_sep"]

    if (number.isNullOrBlank() == false && numberSep != null) {
        title = title.number(number, numberSep, track.numOrZero.toInt())
    }

    this.title = title

    return if (title != title2) 1 else 0
}
