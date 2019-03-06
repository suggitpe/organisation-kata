package org.suggs.katas.organisation

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.JUnitJupiterSoftAssertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.slf4j.LoggerFactory
import org.suggs.katas.organisation.OrganisationTreeBuilder.Companion.buildOrganisationTreeFrom
import org.suggs.katas.organisation.StaffListReader.Companion.readListOfStaff
import org.suggs.katas.organisation.domain.Person.Companion.someoneCalled


class OrganisationTreeTest {

    private val log = LoggerFactory.getLogger(javaClass)

    @RegisterExtension
    val softly = JUnitJupiterSoftAssertions()

    private val pete = someoneCalled("Pete").withAManagerCalled("Paul")
    private val jack = someoneCalled("Jack").withAManagerCalled("Pete")
    private val dale = someoneCalled("Dale").withAManagerCalled("Pete")
    private val bert = someoneCalled("Bert").withAManagerCalled("Dale")
    private val josh = someoneCalled("Josh").withAManagerCalled("Pete")
    private val john = someoneCalled("John").withAManagerCalled("Bert")
    private val nick = someoneCalled("Nick").withAManagerCalled("John")

    private val everyone = listOf(jack, pete, dale, bert, josh, john, nick)

    @Test
    fun `can be dumped to a string`() {
        val tree = buildOrganisationTreeFrom(everyone)
        log.info("\n" + tree.toString())
    }

    @Test
    fun `builds an empty tree when passed no names`() {
        val tree = buildOrganisationTreeFrom(listOf())
        assertThat(tree.isEmpty()).isTrue()
    }

    @Test
    fun `builds a non-empty tree when passed names`() {
        val tree = buildOrganisationTreeFrom(listOf(pete))
        assertThat(tree.isEmpty()).isFalse()
    }

    @Test
    fun `builds tree with zero count when passed no names`() {
        val tree = buildOrganisationTreeFrom(listOf())
        assertThat(tree.treeCount()).isEqualTo(0)
    }

    @Test
    fun `builds an organisation tree of one person when passed a person`() {
        val tree = buildOrganisationTreeFrom(listOf(pete))
        softly.assertThat(tree.treeCount()).isEqualTo(1)
        softly.assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `builds an organisation tree of two people`() {
        val tree = buildOrganisationTreeFrom(listOf(pete, jack))
        softly.assertThat(tree.treeCount()).isEqualTo(2)
        softly.assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `correctly places people in the tree when they are out of order`() {
        val tree = buildOrganisationTreeFrom(listOf(jack, pete))
        softly.assertThat(tree.treeCount()).isEqualTo(2)
        softly.assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `builds an organisation tree of three layers`() {
        val tree = buildOrganisationTreeFrom(everyone)
        softly.assertThat(tree.treeCount()).isEqualTo(7)
        softly.assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `ignores orphaned people from the tree`() {
        val tree = buildOrganisationTreeFrom(listOf(pete, bert))
        assertThat(tree.treeCount()).isEqualTo(1)
    }

    @Test
    fun `returns null if it cannot find someone`() {
        val tree = buildOrganisationTreeFrom(listOf(pete))
        assertThat(tree.findInTree { treeNode -> treeNode.name == jack.name }).isNull()
    }

    @Test
    fun `can find someone in a tree with a depth of 1`() {
        val tree = buildOrganisationTreeFrom(listOf(pete))
        assertThat(tree.findInTree { it -> it.name == pete.name }).isNotNull
    }

    @Test
    fun `can find someone in a tree with a depth of 2`() {
        val tree = buildOrganisationTreeFrom(listOf(pete, jack))
        assertThat(tree.findInTree { it -> it.name == jack.name }).isNotNull
    }

    @Test
    fun `can find someone in a tree with a depth of 3`() {
        val tree = buildOrganisationTreeFrom(listOf(pete, dale, bert))
        assertThat(tree.findInTree { it -> it.name == bert.name }).isNotNull
    }

    @Test
    fun `can find someone in a deeply nested tree`() {
        val tree = buildOrganisationTreeFrom(everyone)
        softly.assertThat(tree.treeCount()).isEqualTo(7)
        softly.assertThat(tree.findInTree { it.name == pete.name }).`as`("pete not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == jack.name }).`as`("jack not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == dale.name }).`as`("dale not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == bert.name }).`as`("bert not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == josh.name }).`as`("josh not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == john.name }).`as`("john not found").isNotNull
        softly.assertThat(tree.findInTree { it.name == nick.name }).`as`("nick not found").isNotNull
    }

    @Test
    fun `can read staff from a file into a tree`() {
        val staffList = readListOfStaff("ListOfStaff.csv")
        val tree = buildOrganisationTreeFrom(staffList)
        assertThat(staffList.size == tree.treeCount())
    }
}

