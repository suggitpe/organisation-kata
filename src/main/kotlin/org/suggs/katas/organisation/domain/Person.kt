package org.suggs.katas.organisation.domain

data class Person(val name: String, val manager: String, val engineer: Boolean = false, val committer: Boolean = false)