package org.suggs.katas.organisation

import org.slf4j.LoggerFactory
import org.suggs.katas.organisation.domain.Person
import java.io.File

class OrganisationTreeVisualiser {

    companion object {

        private val log = LoggerFactory.getLogger(this::class.java)

        fun visualiseSimpleTree(tree: TreeNode<Person>): String {
            return """${"\n"}@startuml ${"\n"}${tree.executeOverTree { person -> visualisePersonAsUseCase(person) }} ${"\n"}@enduml"""
        }

        private fun visualisePersonAsUseCase(someone: Person): String {
            return """(${someone.manager}) <-- (${someone.name}) ${"\n"}"""
        }

        fun visualiseCommitterTree(tree: TreeNode<Person>): String {
            return "\n@startuml\n" + tree.executeOverTree { person -> visualisePersonObject(person) } + tree.executeOverTree { person -> visualisePersonRelationship(person) } + "\n@enduml"
        }

        private fun visualisePersonObject(someone: Person): String {
            return """object ${someone.name} #${guageColourFor(someone)}{
    rank = ${someone.rank}
    committer = ${someone.committer}
    engineer = ${someone.engineer}
} ${"\n"}
""".trimIndent()
        }

        private fun guageColourFor(someone: Person): String {
            return when{
                someone.committer == someone.engineer -> "lightgreen"
                !someone.committer && someone.engineer -> "red"
                else -> "pink"
            }
        }

        private fun visualisePersonRelationship(someone: Person): String {
            return """${someone.manager} <-- ${someone.name} ${"\n"}"""
        }

        fun writePumlToFile(puml: String) {
            File(buildFileForOutput()).bufferedWriter().use { out -> out.write(puml) }
        }

        private fun buildFileForOutput(): String {
            val dir = System.getenv("puml.output.dir") ?: "build/puml"
            log.info("Writing puml to directory [$dir]")
            if (!File(dir).exists()) {
                if (!File(dir).mkdirs()) throw IllegalStateException("Failed to create directory [$dir]")
            }
            return "$dir/tree.puml"
        }


    }

}