package org.suggs.katas.organisation

import org.junit.jupiter.api.Test
import org.suggs.katas.organisation.OrganisationTreeBuilder.Companion.buildOrganisationTreeFrom
import org.suggs.katas.organisation.OrganisationTreeVisualiser.Companion.visualiseCommitterTree
import org.suggs.katas.organisation.OrganisationTreeVisualiser.Companion.writePumlToFile
import org.suggs.katas.organisation.StaffListReader.Companion.readListOfStaff

class OrganisationTreeAcceptanceTest {

    @Test
    fun `read staff list and generate organisation tree`() {
        writePumlToFile(
                visualiseCommitterTree(
                        buildOrganisationTreeFrom(
                                readListOfStaff("ListOfStaff.csv"))))
    }
}