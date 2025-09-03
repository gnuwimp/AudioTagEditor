/*
 * Copyright 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.swing

import gnuwimp.util.toHtml
import java.awt.Dimension
import javax.swing.*

/***
 *      _____                   _   _____  _       _
 *     |_   _|                 | | |  __ \(_)     | |
 *       | |  _ __  _ __  _   _| |_| |  | |_  __ _| | ___   __ _
 *       | | | '_ \| '_ \| | | | __| |  | | |/ _` | |/ _ \ / _` |
 *      _| |_| | | | |_) | |_| | |_| |__| | | (_| | | (_) | (_| |
 *     |_____|_| |_| .__/ \__,_|\__|_____/|_|\__,_|_|\___/ \__, |
 *                 | |                                      __/ |
 *                 |_|                                     |___/
 */

/**
 * A dialog to ask for a string.
 */
class InputDialog(parent: JFrame? = PARENT, title: String = TITLE, label: String, icon: Icons = Icons.QUESTION) : BaseDialog(parent, title, true) {
    private val panel = LayoutPanel(size = Swing.defFont.size / 2)
    private val l1    = JLabel()
    private val l2    = JLabel(label.toHtml)
    private val b1    = JButton("Ok")
    private val b2    = JButton("Cancel")
    private val i1    = JTextField()
    private var res   = OkCancel.OK

    /**
     * Set or get input text.
     * Set text to empty to hide button.
     */
    var text: String
        get() = i1.text

        set(value) {
            i1.text = value
        }

    /**
     * Set or get label for button 1.
     * Set text to empty to hide button.
     */
    var textOk: String
        get() = b1.text

        set(value) {
            b1.text = value
        }

    /**
     * Set or get label for button 2.
     * Set text to empty to hide button.
     */
    var textCancel: String
        get() = b2.text

        set(value) {
            b2.text = value
        }

    /**
     *
     */
    init {
        try {
            l1.icon = ImageIcon(icon.loadImage())
            panel.add(l2, x = 12, y =  1, w = -1, h =  12)
            panel.add(i1, x = 12, y = 14, w = -1, h =   4)
        }
        catch (_: Exception) {
            l1.isVisible = false
            panel.add(l2, x = 1, y =   1, w = -1, h =  12)
            panel.add(i1, x = 1, y =  14, w = -1, h =   4)
        }

        add(panel)
        panel.add(l1, x =   1,  y =  1,  w =  8,  h =  8)
        panel.add(b1, x = -21,  y = -5,  w = 20,  h =  4)
        panel.add(b2, x = -42,  y = -5,  w = 20,  h =  4)
        pack()

        l2.border            = BorderFactory.createEtchedBorder()
        l2.verticalAlignment = SwingConstants.TOP
        size                 = Dimension(width, height)
        fontForAll           = Swing.defFont

        /**
         *
         */
        b1.addActionListener {
            res = OkCancel.OK
            hideAndDispose()
        }

        /**
         *
         */
        b2.addActionListener {
            res = OkCancel.CANCEL
            hideAndDispose()
        }
    }

    /**
     * Run dialog and set default button then center dialog on screen or parent.
     */
    fun run(def: OkCancel = OkCancel.OK, w: Int = 40, h: Int = 15): OkCancel {
        if (textCancel.isEmpty() == true) {
            b2.isVisible = false
        }

        when (def) {
            OkCancel.OK -> {
                getRootPane().setDefaultButton(b1)
            }
            OkCancel.CANCEL -> {
                getRootPane().setDefaultButton(b2)
            }
        }

        i1.grabFocus()
        size = Dimension(Swing.defFont.size * w, Swing.defFont.size * h)
        centerWindow()
        isVisible = true

        return res
    }

    companion object {
        /**
         * Show dialog with ok and cancel buttons.
         * Cancel button is the default.
         * Returns a string if ok has been pressed or null if cancel has been pressed.
         */
        fun ask(label: String, text: String = "", def: OkCancel = OkCancel.CANCEL, icon: Icons = Icons.QUESTION): String? {
            val dlg = InputDialog(label = label, icon = icon)
            dlg.text = text
            val res = dlg.run(def = def)
            return if (res == OkCancel.OK) dlg.text else null
        }

        /**
         * Show dialog with ok button only.
         * This always returns a string.
         */
        fun askOkOnly(label: String, text: String = ""): String {
            val dlg = InputDialog(label = label)
            dlg.text = text
            dlg.textCancel = ""
            dlg.run(def = OkCancel.OK)
            return dlg.text
        }
    }
}
