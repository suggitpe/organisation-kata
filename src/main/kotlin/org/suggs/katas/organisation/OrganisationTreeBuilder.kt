package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class OrganisationTreeBuilder {

    companion object {

        fun buildOrganisationTreeFrom(staff: List<Person>): TreeNode<Person> {
            return buildOrganisationTreeFrom(staff, EmptyTreeNode(noOne()))
        }

        private tailrec fun buildOrganisationTreeFrom(staff: List<Person>, tree: TreeNode<Person>): TreeNode<Person> {
            return when {
                staff.isEmpty() -> tree
                else -> {
                    val toAdd = staff.first()
                    buildOrganisationTreeFrom(staff.drop(1), tree.insert(toAdd, { it -> it.manager == toAdd.name }, { it -> it.name == toAdd.manager }))
                }
            }
        }

        private fun noOne(): Person {
            return Person("", "")
        }
    }
}

private class EmptyTreeNode<T>(private val value: T) : TreeNode<T> {

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

    override fun toString(): String {
        return "EMPTY TREE"
    }

    override fun executeOverTree(doSometing: (T) -> String): String {
        return ""
    }

}