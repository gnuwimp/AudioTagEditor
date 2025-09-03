/*
 * Copyright 2016 - 2025 gnuwimp@gmail.com
 * Released under the GNU General Public License v3.0
 */

package gnuwimp.swing

import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.*
import javax.swing.tree.*

/***
 *           _ _______
 *          | |__   __|
 *          | |  | |_ __ ___  ___
 *      _   | |  | | '__/ _ \/ _ \
 *     | |__| |  | | | |  __/  __/
 *      \____/   |_|_|  \___|\___|
 *
 *
 */

/**
 * Copy closed icon to leaf icon (make them same).
 */
fun JTree.copyClosedIconToLeafIcon() {
    (this.cellRenderer as DefaultTreeCellRenderer).leafIcon = (this.cellRenderer as DefaultTreeCellRenderer).closedIcon
}

/***
 *      _____  _      _____        _
 *     |  __ \(_)    |  __ \      | |
 *     | |  | |_ _ __| |  | | __ _| |_ __ _
 *     | |  | | | '__| |  | |/ _` | __/ _` |
 *     | |__| | | |  | |__| | (_| | || (_| |
 *     |_____/|_|_|  |_____/ \__,_|\__\__,_|
 *
 *
 */

/**
 * Store file data for a tree node.
 */
data class DirData(val file: File) {
    /**
     * Get directory name or file path
     */
    override fun toString(): String {
        return file.name.ifEmpty { file.path }
    }
}

/***
 *      _____  _      _      _     _
 *     |  __ \(_)    | |    (_)   | |
 *     | |  | |_ _ __| |     _ ___| |_ ___ _ __   ___ _ __
 *     | |  | | | '__| |    | / __| __/ _ \ '_ \ / _ \ '__|
 *     | |__| | | |  | |____| \__ \ ||  __/ | | |  __/ |
 *     |_____/|_|_|  |______|_|___/\__\___|_| |_|\___|_|
 *
 *
 */

/**
 * Interface for callbacks when directory path has been changed.
 */
interface DirListener {
    /**
     * Callback method to override
     */
    fun pathChanged(path: String)
}

/***
 *      _____  _      _____                        _      _     _
 *     |  __ \(_)    |  __ \                      | |    (_)   | |
 *     | |  | |_ _ __| |__) |__  _ __  _   _ _ __ | |     _ ___| |_ ___ _ __   ___ _ __
 *     | |  | | | '__|  ___/ _ \| '_ \| | | | '_ \| |    | / __| __/ _ \ '_ \ / _ \ '__|
 *     | |__| | | |  | |  | (_) | |_) | |_| | |_) | |____| \__ \ ||  __/ | | |  __/ |
 *     |_____/|_|_|  |_|   \___/| .__/ \__,_| .__/|______|_|___/\__\___|_| |_|\___|_|
 *                              | |         | |
 *                              |_|         |_|
 */

/**
 * Mouse right click listener for popup menu.
 */
class DirPopupListener(val menu: JPopupMenu) : MouseAdapter() {
    /**
     * Show popup menu
     */
    override fun mousePressed(mouseEvent: MouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent) == true) {
            menu.show(mouseEvent.component, mouseEvent.x, mouseEvent.y)
        }
    }
}

/***
 *      _____  _   _______
 *     |  __ \(_) |__   __|
 *     | |  | |_ _ __| |_ __ ___  ___
 *     | |  | | | '__| | '__/ _ \/ _ \
 *     | |__| | | |  | | | |  __/  __/
 *     |_____/|_|_|  |_|_|  \___|\___|
 *
 *
 */

/**
 * A directory tree panel.
 * With a popup mouse menu with a few options.
 */
class DirTree(val dirListener: DirListener) : JPanel(), ActionListener {
    private val topNode:      DefaultMutableTreeNode
    private val treeModel:    DefaultTreeModel
    private val tree          = JTree()
    private val menu          = JPopupMenu()
    private var selectedNode  = DefaultMutableTreeNode()
    private val mouseListener = DirPopupListener(menu)
    init {
        val scroll = JScrollPane(tree)

        layout = BorderLayout()
        add(scroll, BorderLayout.CENTER)

        menu.addItems(listOf("Reload selected directory", "Rename directory", "Create directory", "Delete directory"), this)

        if (Swing.isUnix == true) {
            topNode = DefaultMutableTreeNode(DirData(File("/")))
        }
        else {
            topNode = DefaultMutableTreeNode("My Computer")

            for (file in File.listRoots()) {
                topNode.add(DefaultMutableTreeNode(DirData(file)))
            }
        }

        treeModel                         = DefaultTreeModel(topNode)
        tree.model                        = treeModel
        tree.selectionModel.selectionMode = TreeSelectionModel.SINGLE_TREE_SELECTION
        tree.showsRootHandles             = true
        tree.isEditable                   = false

        tree.addMouseListener(mouseListener)
        tree.copyClosedIconToLeafIcon()

        tree.addTreeSelectionListener {
            val node = tree.lastSelectedPathComponent as DefaultMutableTreeNode?

            if (node != null && node !== topNode) {
                selectedNode = node
                addDir(selectedNode)
                dirListener.pathChanged((selectedNode.userObject as DirData).file.path)
                tree.expandPath(TreePath(selectedNode.path))
            }
        }
    }

    /**
     * Callback for popup menu.
     * It will catch eventual exceptions and display it in an error dialog.
     */
    override fun actionPerformed(actionEvent: ActionEvent?) {
        try {
            if (actionEvent == null) {
                return
            }
            else if (actionEvent.actionCommand == "Reload selected directory") {
                addDir(selectedNode)
                treeModel.reload(selectedNode)
                dirListener.pathChanged((selectedNode.userObject as DirData).file.path)
            }
            else if (actionEvent.actionCommand == "Rename directory") {
                val answer = InputDialog.ask(label = "<b>Rename directory!</b>\nEnter new directory name.")
                val dir    = selectedNode.userObject as DirData

                if (answer.isNullOrBlank() == false && answer != dir.file.name) {
                    val from = dir.file
                    val to   = File(from.parent + File.separator + answer)

                    if (from.renameTo(to) == false) {
                        throw Exception("error: can't rename directory")
                    }
                    else {
                        selectedNode.userObject = DirData(to)
                        treeModel.reload(selectedNode)
                        dirListener.pathChanged(to.path)
                    }
                }
            }
            else if (actionEvent.actionCommand == "Create directory") {
                val answer = InputDialog.ask(label = "<b>Create directory!</b>\nEnter new directory name.")

                if (answer.isNullOrEmpty() == false) {
                    val currDir = (selectedNode.userObject as DirData).file
                    val newDir  = File(currDir.path + File.separator + answer)

                    if (newDir.mkdir() == false) {
                        throw Exception("error: can't create directory")
                    }
                    else {
                        selectedNode.removeAllChildren()
                        addDir(selectedNode)
                        treeModel.reload(selectedNode)
                    }
                }
            }
            else if (actionEvent.actionCommand == "Delete directory") {
                val answer = MessageDialog.askOkCancel(label = "<b>Delete directory!</b>\nDirectory must be empty.")

                if (answer == YesNoCancel.YES) {
                    if ((selectedNode.userObject as DirData).file.delete() == false) {
                        throw Exception("error: can't delete directory")
                    }
                    else {
                        val parentNode = selectedNode.parent as DefaultMutableTreeNode

                        addDir(parentNode)
                        treeModel.reload(parentNode)
                        dirListener.pathChanged((parentNode.userObject as DirData).file.path)
                        tree.selectionPath = TreePath(parentNode.path)
                    }
                }
            }
        }
        catch (e: Exception) {
            MessageDialog.error(label = e.toString())
        }
    }

    /**
     * Add child directories to parent directory.
     */
    private fun addDir(node: DefaultMutableTreeNode) {
        node.removeAllChildren()

        val dirNode = node.userObject as DirData

        if (dirNode.file.isDirectory == true) {
            val list = dirNode.file.listFiles()

            if (list != null) {
                for (file in list.sorted()) {
                    if (file.isDirectory == true) {
                        node.add(DefaultMutableTreeNode(DirData(file)))
                    }
                }
            }
        }
    }

    /**
     *
     */
    fun enableTree(value: Boolean) {
        if (value == true) {
            val exist = tree.mouseListeners.find { it == mouseListener }

            if (exist == null) {
                tree.addMouseListener(mouseListener)
            }

            enable(true, this)
        }
        else {
            tree.removeMouseListener(mouseListener)
            enable(false, this)
        }
    }

    /**
     * Create sub path.
     */
    private fun makePath(paths: List<String>, index: Int): String {
        var path = ""

        for (i in 0..index) {
            if (Swing.isUnix == true) {
                when (i) {
                    0    -> path = "/"
                    1    -> path += paths[i]
                    else -> path += "/" + paths[i]
                }
            }
            else {
                if (i > 1) {
                    path += File.separator
                }

                path += paths[i]

                if (i == 0) {
                    path += File.separator
                }
            }
        }

        return path
    }

    /**
     * Restore and select path.
     */
    fun restore(path: String) {
        val paths    = File(path).absolutePath.split(("\\" + File.separator).toRegex()).dropLastWhile(String::isEmpty)
        val node     = restorePath(paths, if (Swing.isUnix) 1 else 0, topNode)
        val treePath = TreePath(node.path)

        tree.selectionPath = treePath
        tree.scrollPathToVisible(treePath)
    }

    /**
     * Restore child subPaths recursively.
     * And return last used tree node.
     */
    private fun restorePath(subPaths: List<String>, index: Int, node: DefaultMutableTreeNode): DefaultMutableTreeNode {
        if (index >= subPaths.size) {
            return node
        }

        if (node.isLeaf == true) {
            addDir(node)
        }

        val path = makePath(subPaths, index)

        for (childNode in node.children()) {
            val dir = (childNode as DefaultMutableTreeNode).userObject as DirData

            if (path == dir.file.absolutePath) {
                return restorePath(subPaths, index + 1, childNode)
            }
        }

        return node
    }

    /**
     * Set tooltip text for the tree.
     */
    fun toolTipText(value: String) {
        tree.toolTipText = value
    }
}
