package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class EmptyTreeNode(private val value: Person) : TreeNode {

    override fun parent(): TreeNode? {
        return null
    }

    override fun value(): Person {
        return value
    }

    override fun children(): List<TreeNode> {
        return ArrayList()
    }

    override fun insert(toAdd: Person, checkIfInsertIsManagerOf: (Person) -> Boolean, checkIfInsertIsChildOf: (Person) -> Boolean): TreeNode {
        return PopulatedTreeNode(toAdd, null)
    }

    override fun findInTree(checkName: (Person) -> Boolean): TreeNode? {
        return null
    }

    override fun treeCount(): Int {
        return 0
    }


}