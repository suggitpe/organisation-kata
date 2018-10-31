package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class PopulatedTreeNode(val valueNode: Person, var parentNode: TreeNode?, var childrenNodes: List<TreeNode> = ArrayList()) : TreeNode {

    override fun parent(): TreeNode? {
        return parentNode
    }

    override fun value(): Person {
        return valueNode
    }

    override fun children(): List<TreeNode> {
        return childrenNodes
    }

    override fun insert(personToAdd: Person): TreeNode {
        when {
            valueNode.manager == personToAdd.name -> return placeAtTheTopOfTheTree(personToAdd)
            personToAdd.manager == valueNode.name -> return addToTheChildren(personToAdd)
            else -> childrenNodes.forEach { it -> it.insert(personToAdd) }
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
