package org.suggs.katas.organisation.domain

data class Person(val name: String,
                  val manager: String,
                  val engineer: Boolean = false,
                  val committer: Boolean = false) {

    companion object {

        fun someoneCalled(named: String): Person = Person(named, "")
    }

    fun withAManagerCalled(managerName: String): Person = Person(name, managerName)

    fun thatCommits(doTheyCommit: Boolean): Person = Person(name, manager, engineer, doTheyCommit)

    fun thatIsAnEngineer(areTheyAnEngineer: Boolean): Person = Person(name, manager, areTheyAnEngineer, committer)

}