package org.suggs.katas.organisation

import org.assertj.core.api.JUnitJupiterSoftAssertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.suggs.katas.organisation.StaffListReader.Companion.readListOfStaff


class StaffListReaderTest {

    @RegisterExtension
    val softly = JUnitJupiterSoftAssertions()

    @Test fun `read from a csv to create a list of staff`() {
        val staff = readListOfStaff("ListOfStaff.csv")
        softly.assertThat(staff.size).isEqualTo(9)
        softly.assertThat(staff.first().name).isEqualTo("Paul")
    }

}