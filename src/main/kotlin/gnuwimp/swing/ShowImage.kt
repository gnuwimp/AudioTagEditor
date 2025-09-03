/*
 * Copyright 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.swing

import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*

/***
 *       _____ _                 _____
 *      / ____| |               |_   _|
 *     | (___ | |__   _____      _| |  _ __ ___   __ _  __ _  ___
 *      \___ \| '_ \ / _ \ \ /\ / / | | '_ ` _ \ / _` |/ _` |/ _ \
 *      ____) | | | | (_) \ V  V /| |_| | | | | | (_| | (_| |  __/
 *     |_____/|_| |_|\___/ \_/\_/_____|_| |_| |_|\__,_|\__, |\___|
 *                                                      __/ |
 *                                                     |___/
 */

/**
 * A dialog for displaying an image.
 */
class ShowImage(image: BufferedImage?, parent: JFrame? = null, title: String = "", modal: Boolean = true, width: Int = Swing.defFont.size * 60, height: Int = Swing.defFont.size * 50) : BaseDialog(parent, title, modal) {
    init {
        val panel       = LayoutPanel(size = Swing.defFont.size / 2)
        val label       = if (image != null) JLabel(ImageIcon(image)) else JLabel("Error: no image!")
        val scroll      = JScrollPane(label)
        val closeButton = JButton("Close")

        add(panel)
        panel.add(scroll,      x =   1,  y =  1,  w = -1,  h = -6)
        panel.add(closeButton, x = -21,  y = -5,  w = 20,  h =  4)
        pack()

        size                    = Dimension(width, height)
        fontForAll              = Swing.defFont
        label.lineBorder        = true
        label.verticalAlignment = SwingConstants.TOP
        label.font              = Swing.bigFont

        centerWindow()

        /**
         *
         */
        closeButton.addActionListener {
            hideAndDispose()
        }
    }
}

/***
 *       _____ _                 _____                            ______ _ _
 *      / ____| |               |_   _|                          |  ____(_) |
 *     | (___ | |__   _____      _| |  _ __ ___   __ _  __ _  ___| |__   _| | ___
 *      \___ \| '_ \ / _ \ \ /\ / / | | '_ ` _ \ / _` |/ _` |/ _ \  __| | | |/ _ \
 *      ____) | | | | (_) \ V  V /| |_| | | | | | (_| | (_| |  __/ |    | | |  __/
 *     |_____/|_| |_|\___/ \_/\_/_____|_| |_| |_|\__,_|\__, |\___|_|    |_|_|\___|
 *                                                      __/ |
 *                                                     |___/
 */

/**
 * A dialog for displaying an image from a file.
 */
class ShowImageFile(imageFile: String, parent: JFrame? = null, title: String = "", modal: Boolean = true, width: Int = Swing.defFont.size * 60, height: Int = Swing.defFont.size * 50) : BaseDialog(parent, title, modal) {
    init {
        val imageIcon = try {
            ImageIcon(ImageIO.read(File(imageFile)))
        }
        catch (_ : Exception) {
            null
        }

        val panel       = LayoutPanel(size = Swing.defFont.size / 2)
        val label       = if (imageIcon != null) JLabel(imageIcon) else JLabel("Can't read file $imageFile")
        val scroll      = JScrollPane(label)
        val closeButton = JButton("Close")

        add(panel)
        panel.add(scroll,      x =   1,  y =  1,  w = -1,  h = -6)
        panel.add(closeButton, x = -21,  y = -5,  w = 20,  h =  4)
        pack()

        size                    = Dimension(width, height)
        fontForAll              = Swing.defFont
        label.lineBorder        = true
        label.verticalAlignment = SwingConstants.TOP
        label.font              = Swing.bigFont

        centerWindow()

        /**
         *
         */
        closeButton.addActionListener {
            hideAndDispose()
        }
    }
}
