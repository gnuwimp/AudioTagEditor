/*
 * Copyright 2016 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.swing

import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.SwingConstants
import javax.swing.border.BevelBorder

/***
 *       _____ _        _             ____
 *      / ____| |      | |           |  _ \
 *     | (___ | |_ __ _| |_ _   _ ___| |_) | __ _ _ __
 *      \___ \| __/ _` | __| | | / __|  _ < / _` | '__|
 *      ____) | || (_| | |_| |_| \__ \ |_) | (_| | |
 *     |_____/ \__\__,_|\__|\__,_|___/____/ \__,_|_|
 *
 *
 */

/**
 * A simple statusbar widget with a label and possibility to change label color.
 */
class StatusBar(alignText: Align) : LayoutPanel(size = Swing.defFont.size / 2 + 1) {
    /**
     * Text align values.
     */
    enum class Align {
        LEFT, CENTER, RIGHT
    }

    private val _label = JLabel()

    /**
     * Set label and color it red if the string has "error" in it.
     */
    var errorLabel: String
        get() = _label.text

        set(value) {
            _label.text = " $value"

            if (value.indexOf(string = "error", ignoreCase = true) != -1) {
                _label.foreground = Color(255, 0, 0)
            }
            else {
                _label.foreground = parent.foreground
            }
        }

    /**
     * Set or get label.
     */
    var label: String
        get() = _label.text

        set(value) {
            _label.text = " $value"
        }

    /**
     *
     */
    init {
        border = BorderFactory.createEtchedBorder(BevelBorder.LOWERED)
        add(_label, 0, 0, 0, 0)

        _label.font   = Font(Font.MONOSPACED, Font.PLAIN, font.size)
        preferredSize = Dimension(Swing.defFont.size * 10, Swing.defFont.size * 2)

        _label.horizontalAlignment = when(alignText) {
            Align.LEFT -> SwingConstants.LEFT
            Align.CENTER -> SwingConstants.CENTER
            Align.RIGHT -> SwingConstants.RIGHT
        }
    }

    /**
     * Set message and color.
     *
     * @param[text] Label string.
     * @param[color] Label color, default parent.foreground.
     */
    fun labelAndColor(text: String, color: Color = parent.foreground) {
        label             = text
        _label.foreground = color
    }
}
