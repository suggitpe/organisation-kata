package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class EmptyTreeNode(private val valueNode: Person) : TreeNode {

    override fun parent(): TreeNode? {
        return null
    }

    override fun value(): Person {
        return valueNode
    }

    override fun children(): List<TreeNode> {
        return ArrayList()
    }

    override fun insert(toAdd: Person): TreeNode {
        return PopulatedTreeNode(toAdd, null)
    }

    override fun findInTree(name: String): TreeNode? {
        return null
    }

    override fun treeCount(): Int {
        return 0
    }


}