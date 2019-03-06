package org.suggs.katas.organisation

interface TreeNode<T> {

    fun isEmpty(): Boolean

    fun parent(): TreeNode<T>?

    fun value(): T

    fun children(): List<TreeNode<T>>

    fun insert(toAdd: T, checkIfInsertIsManagerOf: (T) -> Boolean, checkIfInsertIsChildOf: (T) -> Boolean): TreeNode<T>

    fun treeCount(): Int

    fun findInTree(checkName: (T) -> Boolean): TreeNode<T>?

    fun executeOverTree(doSomething: (T) -> String ): String
}