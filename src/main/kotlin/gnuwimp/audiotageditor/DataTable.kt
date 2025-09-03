/*
 * Copyright 2021 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.audiotageditor

import gnuwimp.swing.Swing
import gnuwimp.swing.Table
import java.awt.Color
import javax.swing.DefaultCellEditor
import javax.swing.JTextField
import javax.swing.border.LineBorder

/***
 *      _____        _     _______    _     _
 *     |  __ \      | |   |__   __|  | |   | |
 *     | |  | | __ _| |_ __ _| | __ _| |__ | | ___
 *     | |  | |/ _` | __/ _` | |/ _` | '_ \| |/ _ \
 *     | |__| | (_| | || (_| | | (_| | |_) | |  __/
 *     |_____/ \__,_|\__\__,_|_|\__,_|_.__/|_|\___|
 *
 *
 */

/**
 * Table with colors, same for all views.
 */
class DataTable : Table() {
    private val dirtyColor = Color(255, 230, 230)
    private val errorColor = Color(255, 0, 0)

    init {
        setRowHeight(Swing.defFont.size + 8)
    }

    /**
     *
     */
    override fun getRowBgColor(row: Int, column: Int): Color {
        val track = Data.getTrack(row)

        return when {
            track == null -> background
            track.hasError -> errorColor
            track.isChanged -> dirtyColor
            else -> super.getRowBgColor(row, column)
        }
    }

    /**
     *
     */
    fun setFontSizeForEditor(col: Int) {
        val textField = JTextField()
        textField.setFont(Swing.defFont)
        textField.setBorder(LineBorder(Color.BLACK))

        val dce = DefaultCellEditor(textField)
        getColumnModel().getColumn(col).setCellEditor(dce)
    }
}
