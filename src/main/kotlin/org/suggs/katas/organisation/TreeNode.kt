package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

interface TreeNode {

    fun parent(): TreeNode?

    fun value(): Person

    fun children(): List<TreeNode>

    fun insert(toAdd: Person): TreeNode

    fun treeCount(): Int

    fun findInTree(name: String): TreeNode?
}