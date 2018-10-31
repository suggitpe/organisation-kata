package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class TreeBuilder {


    fun buildOrganisationTreeFrom(staff: List<Person>): TreeNode {
        return buildOrganisationTreeFrom(staff, EmptyTreeNode())
    }

    private fun buildOrganisationTreeFrom(staff: List<Person>, node: TreeNode): TreeNode {
        return when {
            staff.isEmpty() -> node
            else -> buildOrganisationTreeFrom(staff.drop(1), node.insert(staff.first()))
        }
    }

}