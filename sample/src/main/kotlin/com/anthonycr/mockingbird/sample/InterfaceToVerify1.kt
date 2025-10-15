package com.anthonycr.mockingbird.sample

internal interface InterfaceToVerify1 {

    fun performAction1(one: Int)

    fun performAction2(one: String, two: Long)

    fun performAction3(exception: Exception)

    fun performAction4(one: String, two: Int, exception: Exception)

    fun performAction5(foo: Foo)

}

internal data class Foo(val string: String)
