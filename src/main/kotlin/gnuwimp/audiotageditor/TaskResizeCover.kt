/*
 * Copyright 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.util.Task

/***
 *      _______        _    _____           _          _____
 *     |__   __|      | |  |  __ \         (_)        / ____|
 *        | | __ _ ___| | _| |__) |___  ___ _ _______| |     _____   _____ _ __
 *        | |/ _` / __| |/ /  _  // _ \/ __| |_  / _ \ |    / _ \ \ / / _ \ '__|
 *        | | (_| \__ \   <| | \ \  __/\__ \ |/ /  __/ |___| (_) \ V /  __/ |
 *        |_|\__,_|___/_|\_\_|  \_\___||___/_/___\___|\_____\___/ \_/ \___|_|
 *
 *
 */

/**
 * Thread task resize cover image.
 */
class TaskResizeCover(var track: Track, val size: Int) : Task(max = 1) {
    var resized = false

    /**
     *
     */
    init {
        message = track.fileName
    }

    /**
     *
     */
    override fun run() {
        if (abort == true) {
            throw Exception(Constants.ERROR_ABORTING_SAVING)
        }
        else {
            resized = track.resizeImage(size)
            progress = 1
        }
    }
}
