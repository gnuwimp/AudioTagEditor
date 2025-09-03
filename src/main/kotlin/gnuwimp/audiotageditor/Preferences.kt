/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import java.io.File
import java.util.prefs.Preferences

/***
 *      _____           __
 *     |  __ \         / _|
 *     | |__) | __ ___| |_ ___ _ __ ___ _ __   ___ ___  ___
 *     |  ___/ '__/ _ \  _/ _ \ '__/ _ \ '_ \ / __/ _ \/ __|
 *     | |   | | |  __/ ||  __/ | |  __/ | | | (_|  __/\__ \
 *     |_|   |_|  \___|_| \___|_|  \___|_| |_|\___\___||___/
 *
 *
 */

/**
 * Main font size.
 */
var Preferences.fontSize: Int
    get() {
        val f = getInt("font_size", Constants.DEFAULT_FONT)
        return if (f >= Constants.MIN_FONT && f <= Constants.MAX_FONT) f else Constants.DEFAULT_FONT
    }

    set(value) {
        putInt("font_size", value)
    }

/**
 * Last used path.
 */
var Preferences.lastPath: String
    get() = get("path_last", File(System.getProperty("user.home")).canonicalPath)

    set(value) {
        put("path_last", value)
    }

/**
 * Last used image path.
 */
var Preferences.picPath: String
    get() = get("path_pic", File(System.getProperty("user.home")).canonicalPath)

    set(value) {
        put("path_pic", value)
    }

/**
 * Default resize size.
 */
var Preferences.resize: Long
    get() = getLong("resize", 0)

    set(value) {
        putLong("resize", value)
    }

/**
 * Split view pos for album tab.
 */
var Preferences.splitAlbum: Int
    get() = getInt("split_album", 200)

    set(value) {
        putInt("split_album", value)
    }

/**
 * Split view pos for directories.
 */
var Preferences.splitDir: Int
    get() = getInt("split_dir", 200)

    set(value) {
        putInt("split_dir", value)
    }

/**
 * Split view pos for file tab.
 */
var Preferences.splitFile: Int
    get() = getInt("split_file", 200)

    set(value) {
        putInt("split_file", value)
    }

/**
 * Split view pos for the two status bars.
 */
var Preferences.splitStatus: Int
    get() = getInt("split_status", 600)

    set(value) {
        putInt("split_status", value)
    }

/**
 * Split view pos for title tab.
 */
var Preferences.splitTitle: Int
    get() = getInt("split_title", 200)

    set(value) {
        putInt("split_title", value)
    }

/**
 * Split view pos for track tab.
 */
var Preferences.splitTrack: Int
    get() = getInt("split_track", 200)

    set(value) {
        putInt("split_track", value)
    }

/**
 * Number of threads to use when loading and saving tracks.
 */
var Preferences.threads: Int
    get() {
        val t = getInt("threads", Constants.DEFAULT_THREADS)
        return if (t >= 1 && t <= Constants.MAX_THREADS) t else Constants.DEFAULT_THREADS
    }

    set(value) {
        putInt("threads", value)
    }

/**
 * Window height.
 */
var Preferences.winHeight: Int
    get() = getInt("win_height", 1000)

    set(value) {
        putInt("win_height", value)
    }

/**
 * Window maximized?
 */
var Preferences.winMax: Boolean
    get() = getBoolean("win_max", false)

    set(value) {
        putBoolean("win_max", value)
    }

/**
 * Window width.
 */
var Preferences.winWidth: Int
    get() = getInt("win_width", 1600)

    set(value) {
        putInt("win_width", value)
    }

/**
 * Window x pos.
 */
var Preferences.winX: Int
    get() = getInt("win_x", 50)

    set(value) {
        putInt("win_x", value)
    }

/**
 * Window y pos.
 */
var Preferences.winY: Int
    get() = getInt("win_y", 50)

    set(value) {
        putInt("win_y", value)
    }

