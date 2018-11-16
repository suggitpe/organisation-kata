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
            return buildListOfStaffFrom(staffData.drop(1), ArrayList())
        }

        private tailrec fun buildListOfStaffFrom(staffData: List<String>, listOfPeople: List<Person>): List<Person> {
            return when {
                staffData.isEmpty() -> listOfPeople
                else -> buildListOfStaffFrom(staffData.drop(1), addToList(listOfPeople, staffData.first()))
            }
        }

        private fun addToList(listOfStaff: List<Person>, toAdd: String): List<Person> {
            return listOfStaff.plus(buildPersonFrom(toAdd))
        }

        private fun buildPersonFrom(lineFromFile: String): Person {
            val personData = lineFromFile.split(",")
            return someoneCalled(personData[0].trim())
                    .withAManagerCalled(personData[1].trim())
                    .withARankOf(personData[2].trim())
                    .thatIsAnEngineer(personData[3].trim().toBoolean())
                    .thatCommits(personData[4].trim().toBoolean())
        }

    }

}
