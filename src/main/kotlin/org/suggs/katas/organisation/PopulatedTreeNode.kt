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

    override fun insert(toAdd: Person): TreeNode {
        when {
            valueNode.manager == toAdd.name -> return placeAtTheTopOfTheTree(toAdd)
            toAdd.manager == valueNode.name -> return addToTheChildren(toAdd)
            else -> childrenNodes.forEach { it -> it.insert(toAdd) }
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

    override fun findInTree(name: String): TreeNode? {
        return when {
            value().name == name -> this
            childrenNodes.isEmpty() -> null
            else -> childrenNodes.firstOrNull { it -> it.findInTree(name) != null }
        }
    }

    override fun treeCount(): Int {
        return 1 + childrenNodes.sumBy { it -> it.treeCount() }
    }

}
