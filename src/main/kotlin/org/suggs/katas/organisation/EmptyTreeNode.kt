package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class EmptyTreeNode : TreeNode {

    override fun parent(): TreeNode? {
        return null
    }

    override fun value(): Person {
        return Person("", "")
    }

    override fun children(): List<TreeNode> {
        return ArrayList()
    }

    override fun insert(personToAdd: Person): TreeNode {
        return PopulatedTreeNode(personToAdd, null)
    }

    override fun findInTree(name: String): TreeNode? {
        return null
    }

    override fun treeCount(): Int {
        return 0
    }


}