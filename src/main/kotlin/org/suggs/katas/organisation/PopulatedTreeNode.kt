package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class PopulatedTreeNode(private val valueNode: Person,
                        private var parentNode: TreeNode?,
                        private var childrenNodes: List<TreeNode> = ArrayList()) : TreeNode {

    override fun parent(): TreeNode? {
        return parentNode
    }

    override fun value(): Person {
        return valueNode
    }

    override fun children(): List<TreeNode> {
        return childrenNodes
    }

    override fun insert(toAdd: Person, checkIfInsertIsManagerOf: (Person) -> Boolean, checkIfInsertIsChildOf: (Person) -> Boolean): TreeNode {
        when {
            checkIfInsertIsManagerOf(valueNode) -> return placeAtTheTopOfTheTree(toAdd)
            checkIfInsertIsChildOf(valueNode) -> return addToTheChildren(toAdd)
            else -> childrenNodes.forEach { it -> it.insert(toAdd, checkIfInsertIsManagerOf, checkIfInsertIsChildOf) }
        }
        return this
    }

    private fun placeAtTheTopOfTheTree(toAdd: Person): TreeNode {
        val newTreeHead = PopulatedTreeNode(toAdd, null)
        newTreeHead.childrenNodes += this
        this.parentNode = newTreeHead
        return newTreeHead
    }

    private fun addToTheChildren(toAdd: Person): TreeNode {
        childrenNodes += PopulatedTreeNode(toAdd, this)
        return this
    }

    override fun findInTree(checkName: (Person) -> Boolean): TreeNode? {
        return when {
            checkName(valueNode) -> this
            childrenNodes.isEmpty() -> null
            else -> childrenNodes.firstOrNull { it -> it.findInTree(checkName) != null }
        }
    }

    override fun treeCount(): Int {
        return 1 + childrenNodes.sumBy { it -> it.treeCount() }
    }

}
