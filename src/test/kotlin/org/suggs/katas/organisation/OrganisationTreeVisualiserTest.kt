package org.suggs.katas.organisation

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.suggs.katas.organisation.OrganisationTreeBuilder.Companion.buildOrganisationTreeFrom
import org.suggs.katas.organisation.OrganisationTreeVisualiser.Companion.visualiseCommitterTree
import org.suggs.katas.organisation.OrganisationTreeVisualiser.Companion.visualiseSimpleTree
import org.suggs.katas.organisation.OrganisationTreeVisualiser.Companion.writePumlToFile
import org.suggs.katas.organisation.domain.Person
import org.suggs.katas.organisation.domain.Person.Companion.someoneCalled

class OrganisationTreeVisualiserTest {

    private val log = LoggerFactory.getLogger(javaClass)
    private val tree = buildOrganisationTree()

    private fun buildOrganisationTree(): TreeNode<Person> {
        return buildOrganisationTreeFrom(listOf(
                someoneCalled("Paul").withAManagerCalled("Scott").withARankOf("MD").thatCommits(true).thatIsAnEngineer(true),
                someoneCalled("Pete").withAManagerCalled("Paul").withARankOf("D").thatCommits(true).thatIsAnEngineer(true),
                someoneCalled("Jack").withAManagerCalled("Pete").withARankOf("D").thatCommits(true).thatIsAnEngineer(true),
                someoneCalled("Dale").withAManagerCalled("Pete").withARankOf("VP").thatCommits(false).thatIsAnEngineer(true),
                someoneCalled("Gemma").withAManagerCalled("Pete").withARankOf("N/A").thatCommits(false).thatIsAnEngineer(false),
                someoneCalled("Bert").withAManagerCalled("Dale").withARankOf("AVP").thatCommits(true).thatIsAnEngineer(true),
                someoneCalled("Phil").withAManagerCalled("Jack").withARankOf("VP").thatCommits(true).thatIsAnEngineer(false)
        ))
    }

    @Test fun `visualise simple tree of people`() {
        val treeVisualisation = visualiseSimpleTree(tree)
        log.info(treeVisualisation)
    }

    @Test fun `visualise committer tree of people`() {
        val treeVisualisation = visualiseCommitterTree(tree)
        log.info(treeVisualisation)
    }

    @Test fun `visualise simple tree to file`() {
        writePumlToFile(visualiseSimpleTree(tree))
    }

    @Test fun `visualise committer tree into file`() {
        writePumlToFile(visualiseCommitterTree(tree))
    }
}