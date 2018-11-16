package org.suggs.katas.organisation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.suggs.katas.organisation.StaffListReader.Companion.readListOfStaff

class StaffListReaderTest {

    @Test
    fun `read from a csv to create a list of staff`() {
        val staff = readListOfStaff("ListOfStaff.csv")
        assertThat(staff.size).isEqualTo(9)
        assertThat(staff.first().name).isEqualTo("Paul")
    }

}