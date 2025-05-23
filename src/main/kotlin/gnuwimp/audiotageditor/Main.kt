/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.Swing
import java.awt.Font
import java.awt.Image
import java.awt.Toolkit
import java.util.prefs.Preferences
import javax.swing.JOptionPane
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

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

            noImage       = "gnuwimp/audiotageditor/cover.png".loadImageFromResource()
            icon          = "gnuwimp/audiotageditor/AudioTagEditor.png".loadImageFromResource()
            Swing.bigFont = Font(Font.SANS_SERIF, Font.PLAIN, 24)
            Swing.defFont = Font(Font.SANS_SERIF, Font.PLAIN, 12)
            pref          = Preferences.userNodeForPackage(Main.javaClass)
            window        = MainWindow(pref)
        }
        catch(e: Exception) {
            e.printStackTrace()
            JOptionPane.showMessageDialog(null, e, Constants.APP_NAME, JOptionPane.ERROR_MESSAGE)
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
            JOptionPane.showMessageDialog(null, e, Constants.APP_NAME, JOptionPane.ERROR_MESSAGE)
        }
    }
}
