package org.suggs.katas.organisation

import org.suggs.katas.organisation.domain.Person
import org.suggs.katas.organisation.domain.Person.Companion.someoneCalled
import java.io.File

class StaffListReader {

    companion object {

        fun readListOfStaff(filename: String): List<Person> {
            return buildListOfStaffFrom(File(this::class.java.classLoader.getResource(filename).file).readLines())
        }

        private fun buildListOfStaffFrom(staffData: List<String>): List<Person> {
            return buildListOfStaffFrom(removeHeadFrom(staffData), ArrayList())
        }

        private fun removeHeadFrom(staffData: List<String>) = staffData.drop(1)

        private tailrec fun buildListOfStaffFrom(staffData: List<String>, listOfPeople: List<Person>): List<Person> {
            return when {
                staffData.isEmpty() -> listOfPeople
                else -> buildListOfStaffFrom(removeHeadFrom(staffData), addToList(listOfPeople, staffData.first()))
            }
        }

        private fun addToList(listOfStaff: List<Person>, toAdd: String): List<Person> {
            return listOfStaff.plus(buildPersonFrom(toAdd))
        }

        private fun buildPersonFrom(lineFromFile: String): Person {
            return buildPersonFrom(lineFromFile.split(","))
        }

        private fun buildPersonFrom(personData: List<String>): Person {
            return someoneCalled(personData[0].trim())
                    .withAManagerCalled(personData[1].trim())
                    .withARankOf(personData[2].trim())
                    .thatIsAnEngineer(personData[3].trim().toBoolean())
                    .thatCommits(personData[4].trim().toBoolean())
        }

    }

}
