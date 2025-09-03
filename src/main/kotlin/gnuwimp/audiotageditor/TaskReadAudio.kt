/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.util.Task
import java.io.File

/***
 *      _______        _    _____                _                   _ _
 *     |__   __|      | |  |  __ \              | |   /\            | (_)
 *        | | __ _ ___| | _| |__) |___  __ _  __| |  /  \  _   _  __| |_  ___
 *        | |/ _` / __| |/ /  _  // _ \/ _` |/ _` | / /\ \| | | |/ _` | |/ _ \
 *        | | (_| \__ \   <| | \ \  __/ (_| | (_| |/ ____ \ |_| | (_| | | (_) |
 *        |_|\__,_|___/_|\_\_|  \_\___|\__,_|\__,_/_/    \_\__,_|\__,_|_|\___/
 *
 *
 */

/**
 * Thread task to read audio tags.
 */
class TaskReadAudio(val file: File, fileName: String) : Task(max = 1) {
    var track: Track? = null
        private set

    init {
        message = fileName
    }

    /**
     *
     */
    override fun run() {
        if (abort == true) {
            throw Exception(Constants.ERROR_ABORTING_READING)
        }
        else {
            track    = Track(file)
            progress = 1
        }
    }
}
