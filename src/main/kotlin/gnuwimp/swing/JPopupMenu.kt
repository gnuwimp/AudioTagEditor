/*
 * Copyright 2016 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.swing

import java.awt.event.ActionListener
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

/***
 *           _ _____                        __  __
 *          | |  __ \                      |  \/  |
 *          | | |__) |__  _ __  _   _ _ __ | \  / | ___ _ __  _   _
 *      _   | |  ___/ _ \| '_ \| | | | '_ \| |\/| |/ _ \ '_ \| | | |
 *     | |__| | |  | (_) | |_) | |_| | |_) | |  | |  __/ | | | |_| |
 *      \____/|_|   \___/| .__/ \__,_| .__/|_|  |_|\___|_| |_|\__,_|
 *                       | |         | |
 *                       |_|         |_|
 */

/**
 * Add menu items to popup menu
 */
fun JPopupMenu.addItems(labels: List<String>, listener: ActionListener) {
    labels.forEach {
        val item = JMenuItem(it)
        item.addActionListener(listener)
        this.add(item)
    }
}
