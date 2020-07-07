package data

import konveyor.base.randomBuild

object CommonDataFactory {

    val string get() = randomBuild<String>()

    val strings get() = MutableList(10) { string }

    val int get() = randomBuild<Int>()

    val ints get() = MutableList(10) { int }
}