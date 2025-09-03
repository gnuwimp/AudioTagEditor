/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.util.Task

/***
 *      _______        _     _____                                   _ _
 *     |__   __|      | |   / ____|                   /\            | (_)
 *        | | __ _ ___| | _| (___   __ ___   _____   /  \  _   _  __| |_  ___
 *        | |/ _` / __| |/ /\___ \ / _` \ \ / / _ \ / /\ \| | | |/ _` | |/ _ \
 *        | | (_| \__ \   < ____) | (_| |\ V /  __// ____ \ |_| | (_| | | (_) |
 *        |_|\__,_|___/_|\_\_____/ \__,_| \_/ \___/_/    \_\__,_|\__,_|_|\___/
 *
 *
 */

/**
 * Thread task save audio tags.
 */
class TaskSaveAudio(val track: Track, fileName: String) : Task(max = 1) {
    init {
        message = fileName
    }

    /**
     *
     */
    override fun run() {
        if (abort == true) {
            throw Exception(Constants.ERROR_ABORTING_SAVING)
        }
        else {
            track.save(updateTagsWithUserData = true)
            progress = 1
        }
    }
}
