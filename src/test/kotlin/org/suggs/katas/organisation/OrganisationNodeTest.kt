package org.suggs.katas.organisation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.suggs.katas.organisation.domain.Person

class OrganisationNodeTest {

    private val pete = Person("Pete", "Paul")
    private val jack = Person("Jack", "Pete")
    private val dale = Person("Dale", "Pete")
    private val bert = Person("Bert", "Dale")
    private val josh = Person("Josh", "Pete")

    @Test
    fun `builds an empty tree when passed no named`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf())
        assertThat(tree.treeCount()).isEqualTo(0)
    }

    @Test
    fun `builds an organisation tree of one person`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete))
        assertThat(tree.treeCount()).isEqualTo(1)
        assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `builds an organisation tree of two people`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, jack))
        assertThat(tree.treeCount()).isEqualTo(2)
        assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `builds an organisation tree of three layers`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, jack, dale, bert))
        assertThat(tree.treeCount()).isEqualTo(4)
        assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `correctly places people in the tree when they are out of order`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(jack, pete))
        assertThat(tree.treeCount()).isEqualTo(2)
        assertThat(tree.value()).isEqualTo(pete)
    }

    @Test
    fun `ignores orphaned people from the tree`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, bert))
        assertThat(tree.treeCount()).isEqualTo(1)
    }

    @Test
    fun `can find someone in a tree with a depth of 1`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete))
        assertThat(tree.findInTree(pete.name)).isNotNull
    }

    @Test
    fun `can find someone in a tree with a depth of 2`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, jack))
        assertThat(tree.findInTree(jack.name)).isNotNull
    }

    @Test
    fun `can find someone in a tree with a depth of 3`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, dale, bert))
        assertThat(tree.findInTree(bert.name)).isNotNull
    }

    @Test
    fun `can find someone in a deeply nested tree`() {
        val tree = TreeBuilder().buildOrganisationTreeFrom(listOf(pete, jack, dale, bert, josh))
        assertAll("Should find people in the tree",
                Executable { assertThat(tree.findInTree(pete.name)).`as`("pete not found").isNotNull },
                Executable { assertThat(tree.findInTree(jack.name)).`as`("jack not found").isNotNull },
                Executable { assertThat(tree.findInTree(dale.name)).`as`("dale not found").isNotNull },
                Executable { assertThat(tree.findInTree(bert.name)).`as`("bert not found").isNotNull },
                Executable { assertThat(tree.findInTree(josh.name)).`as`("josh not found").isNotNull }
        )
    }
}

