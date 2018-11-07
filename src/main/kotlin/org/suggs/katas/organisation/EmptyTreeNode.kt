package org.suggs.katas.organisation

class EmptyTreeNode<T>(private val value: T) : TreeNode<T> {

    override fun isEmpty(): Boolean = true

    override fun parent(): TreeNode<T>? {
        return null
    }

    override fun value(): T {
        return value
    }

    override fun children(): List<TreeNode<T>> {
        return ArrayList()
    }

    override fun insert(toAdd: T, checkIfInsertIsManagerOf: (T) -> Boolean, checkIfInsertIsChildOf: (T) -> Boolean): TreeNode<T> {
        return PopulatedTreeNode(toAdd, null)
    }

    override fun findInTree(checkName: (T) -> Boolean): TreeNode<T>? {
        return null
    }

    override fun treeCount(): Int {
        return 0
    }


}