/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.BaseDialog
import gnuwimp.swing.MessageDialog
import gnuwimp.swing.Swing
import java.awt.Font
import java.awt.Image
import java.awt.Toolkit
import java.util.prefs.Preferences
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.plaf.FontUIResource
import kotlin.system.exitProcess

/***
 *      __  __       _
 *     |  \/  |     (_)
 *     | \  / | __ _ _ _ __
 *     | |\/| |/ _` | | '_ \
 *     | |  | | (_| | | | | |
 *     |_|  |_|\__,_|_|_| |_|
 *
 *
 */

/**
 * Application start.
 */
object Main {
    val icon:    Image
    val noImage: Image
    val pref:    Preferences
    val window:  MainWindow

    init {
        try {
            Swing.setup(theme = "nimbus", appName = Constants.APP_NAME, aboutText = Constants.aboutApp(), quitLambda = { quit() })

            noImage = "gnuwimp/audiotageditor/cover.png".loadImageFromResource()
            icon    = "gnuwimp/audiotageditor/AudioTagEditor.png".loadImageFromResource()
            pref    = Preferences.userNodeForPackage(Main.javaClass)

            val f = pref.fontSize

            if (f >= Constants.MIN_FONT && f <= Constants.MAX_FONT) {
                Swing.defFont = Font(Font.SANS_SERIF, Font.PLAIN, f)
                Swing.bigFont = Font(Font.SANS_SERIF, Font.PLAIN, f + 10)
            }

            UIManager.put("ToolTip.font", Font(Font.SANS_SERIF, Font.PLAIN, f))
            UIManager.put("OptionPane.messageFont", FontUIResource(Font(Font.SANS_SERIF, Font.PLAIN, f)))
            UIManager.put("Button.defaultButtonFollowsFocus", true)

            window = MainWindow()
            BaseDialog.PARENT = window
            BaseDialog.TITLE = Constants.APP_NAME
        }
        catch(e: Exception) {
            e.printStackTrace()
            MessageDialog.error(label = e.toString())
            exitProcess(status = 1)
        }
    }

    /**
     * Load application icon.
     */
    private fun String.loadImageFromResource(): Image {
        val classLoader = Main::class.java.classLoader
        val pathShell   = classLoader.getResource(this)

        return Toolkit.getDefaultToolkit().getImage(pathShell)
    }

    /**
     *
     */
    private fun quit() {
        window.quit()
    }

    /**
     *
     */
    @JvmStatic fun main(args: Array<String>) {
        try {
            SwingUtilities.invokeLater {
                window.prefLoad()
                window.isVisible = true
            }
        }
        catch(e: Exception) {
            e.printStackTrace()
            MessageDialog.error(label = e.toString())
        }
    }
}
