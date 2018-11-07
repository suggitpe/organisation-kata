package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person

class PersonTreeBuilder {


    fun buildOrganisationTreeFrom(staff: List<Person>): TreeNode<Person> {
        return buildOrganisationTreeFrom(staff, EmptyTreeNode(Person("", "")))
    }

    private fun buildOrganisationTreeFrom(staff: List<Person>, node: TreeNode<Person>): TreeNode<Person> {
        return when {
            staff.isEmpty() -> node
            else -> {
                val toAdd = staff.first()
                buildOrganisationTreeFrom(staff.drop(1), node.insert(toAdd, { it -> it.manager == toAdd.name }, { it -> it.name == toAdd.manager }))
            }
        }
    }

}