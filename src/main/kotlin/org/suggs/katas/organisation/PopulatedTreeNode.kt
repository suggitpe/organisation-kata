package org.suggs.katas.organisation

class PopulatedTreeNode<T>(private val valueNode: T,
                           private var parentNode: TreeNode<T>?,
                           private var childrenNodes: List<PopulatedTreeNode<T>> = ArrayList()) : TreeNode<T> {

    override fun isEmpty(): Boolean = false

    override fun parent(): TreeNode<T>? {
        return parentNode
    }

    override fun value(): T {
        return valueNode
    }

    override fun children(): List<TreeNode<T>> {
        return childrenNodes
    }

    override fun insert(toAdd: T, checkIfInsertIsManagerOf: (T) -> Boolean, checkIfInsertIsChildOf: (T) -> Boolean): TreeNode<T> {
        when {
            checkIfInsertIsManagerOf(valueNode) -> return placeAtTheTopOfTheTree(toAdd)
            checkIfInsertIsChildOf(valueNode) -> return addToTheChildren(toAdd)
            else -> childrenNodes.forEach { it.insert(toAdd, checkIfInsertIsManagerOf, checkIfInsertIsChildOf) }
        }
        return this
    }

    private fun placeAtTheTopOfTheTree(toAdd: T): TreeNode<T> {
        val newTreeHead = PopulatedTreeNode(toAdd, null)
        newTreeHead.childrenNodes += this
        this.parentNode = newTreeHead
        return newTreeHead
    }

    private fun addToTheChildren(toAdd: T): TreeNode<T> {
        childrenNodes += PopulatedTreeNode(toAdd, this)
        return this
    }

    override fun findInTree(checkName: (T) -> Boolean): TreeNode<T>? {
        return when {
            checkName(valueNode) -> this
            childrenNodes.isEmpty() -> null
            else -> childrenNodes.firstOrNull { it.findInTree(checkName) != null }
        }
    }

    override fun treeCount(): Int {
        return 1 + childrenNodes.sumBy { it.treeCount() }
    }

    override fun toString(): String {
        return delegatedToString(1, StringBuilder())
    }

    private fun delegatedToString(level: Int, treeView: StringBuilder): String {
        treeView.append(" ".repeat(level * 4) + "-" + valueNode.toString() + "\n")
        childrenNodes.forEach { it.delegatedToString(level + 1, treeView) }
        return treeView.toString()
    }

    override fun executeOverTree(doSomething: (T) -> String): String {
        return doSomething(valueNode) + childrenNodes.map { it.executeOverTree(doSomething) }.joinToString("")
    }


}
