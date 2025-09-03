/*
 * Copyright 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.SetupPanel
import gnuwimp.swing.Swing
import gnuwimp.swing.TextField
import gnuwimp.util.numOrZero
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JSpinner
import javax.swing.SpinnerNumberModel

/***
 *       _____      _
 *      / ____|    | |                /\
 *     | (___   ___| |_ _   _ _ __   /  \   _ __  _ __
 *      \___ \ / _ \ __| | | | '_ \ / /\ \ | '_ \| '_ \
 *      ____) |  __/ |_| |_| | |_) / ____ \| |_) | |_) |
 *     |_____/ \___|\__|\__,_| .__/_/    \_\ .__/| .__/
 *                           | |           | |   | |
 *                           |_|           |_|   |_|
 */

/**
 * Change default image cover size.
 * Default application font size.
 * Thread count to use when loading and saving.
 */
class SetupApp() : SetupPanel(Constants.DIALOG_TITLE_SETUP) {
    val resizeLabel    = JLabel(Constants.LABEL_SETUP_IMAGE)
    val resize         = TextField()
    val fontLabel      = JLabel(Constants.LABEL_SETUP_FONT)
    val fontModel      = SpinnerNumberModel(Swing.defFont.size, Constants.MIN_FONT, Constants.MAX_FONT, 1)
    val fontSpinner    = JSpinner(fontModel)
    val threadsLabel   = JLabel(Constants.LABEL_SETUP_THREAD)
    val threadsModel   = SpinnerNumberModel(Main.pref.threads, 1, Constants.MAX_THREADS, 1)
    val threadsSpinner = JSpinner(threadsModel)

    /**
     *
     */
    init {
        add(fontLabel,      x =  1, y =  1, w = 40, h = 4)
        add(fontSpinner,    x = 41, y =  1, w = 20, h = 4)
        add(threadsLabel,   x =  1, y =  6, w = 40, h = 4)
        add(threadsSpinner, x = 41, y =  6, w = 20, h = 4)
        add(resizeLabel,    x =  1, y = 11, w = 40, h = 4)
        add(resize,         x = 41, y = 11, w = 20, h = 4)
    }

    /**
     *
     */
    override fun load() {
        resize.text = "${Main.pref.resize}"
    }

    /**
     *
     */
    override fun save(): Boolean {
        val fontValue    = fontSpinner.value as Int
        val threadsValue = threadsSpinner.value as Int

        Swing.defFont     = Font(Font.SANS_SERIF, Font.PLAIN, fontValue)
        Swing.bigFont     = Font(Font.SANS_SERIF, Font.PLAIN, fontValue * 2)
        Main.pref.resize  = resize.text.numOrZero
        Main.pref.threads = threadsValue

        return true
    }
}
