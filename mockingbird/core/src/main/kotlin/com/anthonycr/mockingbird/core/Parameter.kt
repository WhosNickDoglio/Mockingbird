@file:Suppress("UNCHECKED_CAST")

package com.anthonycr.mockingbird.core

private const val MUST_BE_VERIFYING = "You can only call verifyParams inside a verify block"

/**
 * A parameter that has an expected value that will be matched to the actual value.
 *
 * @param expected The expected value that will be matched using the [matcher].
 * @param matcher Used to determine whether the expected and actual parameters match each other.
 * Must return `true` if the parameters match, `false` otherwise.
 */
data class Parameter<T>(
    val expected: T,
    val matcher: (expected: T, actual: T) -> Boolean
)

/**
 * Assert that the actual parameter is equal to [expected] using `==`.
 */
fun <T> eq(expected: T): Parameter<T> = Parameter(expected) { e, a -> e == a }

/**
 * Assert that the actual parameter is the same as the [expected] using the criteria provided by the
 * [matcher].
 *
 * @param expected The expected parameter that is evaluated against the actual parameter using the
 * [matcher].
 * @param matcher Return `true` if the expected is the same as the actual, `false` otherwise.
 */
fun <T> sameAs(
    expected: T,
    matcher: (expected: T, actual: T) -> Boolean
): Parameter<T> = Parameter(expected, matcher)

/**
 * Allows any parameter invocation to match.
 *
 * @param anything Required to execute the test, value is ignored and any parameter passed will match.
 */
fun <T> any(anything: T): Parameter<T> = Parameter(anything) { _, _ -> true }

/**
 * Verify that the parameters ([p0]) match for the function call invoked by [func].
 *
 * @param func The function to invoke.
 * @param p0 The first parameter.
 */
fun <T, P0> T.verifyParams(
    func: T.(P0) -> Unit,
    p0: Parameter<P0>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf { e, a -> p0.matcher(e as P0, a as P0) }
    func(p0.expected)
}

/**
 * Verify that the parameters ([p0]) match for the suspending function call invoked by [func].
 *
 *  @param func The suspending function to invoke.
 *  @param p0 The first parameter.
 */
suspend fun <T, P0> T.verifyParams(
    func: suspend T.(P0) -> Unit,
    p0: Parameter<P0>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf { e, a -> p0.matcher(e as P0, a as P0) }
    func(p0.expected)
}

/**
 * Verify that the parameters ([p0], [p1]) match for the function call invoked by [func].
 *
 * @param func The function to invoke.
 * @param p0 The first parameter.
 * @param p1 The second parameter.
 */
fun <T, P0, P1> T.verifyParams(
    func: T.(P0, P1) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) }
    )
    func(p0.expected, p1.expected)
}

/**
 * Verify that the parameters ([p0], [p1]) match for the suspending function call invoked by [func].
 *
 *  @param func The suspending function to invoke.
 *  @param p0 The first parameter.
 *  @param p1 The second parameter.
 */
suspend fun <T, P0, P1> T.verifyParams(
    func: suspend T.(P0, P1) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) }
    )
    func(p0.expected, p1.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2]) match for the function call invoked by [func].
 *
 * @param func The function to invoke.
 * @param p0 The first parameter.
 * @param p1 The second parameter.
 * @param p2 The third parameter.
 */
fun <T, P0, P1, P2> T.verifyParams(
    func: T.(P0, P1, P2) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) }
    )
    func(p0.expected, p1.expected, p2.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2]) match for the suspending function call invoked by
 * [func].
 *
 *  @param func The suspending function to invoke.
 *  @param p0 The first parameter.
 *  @param p1 The second parameter.
 *  @param p2 The third parameter.
 */
suspend fun <T, P0, P1, P2> T.verifyParams(
    func: suspend T.(P0, P1, P2) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) }
    )
    func(p0.expected, p1.expected, p2.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2], [p3]) match for the function call invoked by
 * [func].
 *
 * @param func The function to invoke.
 * @param p0 The first parameter.
 * @param p1 The second parameter.
 * @param p2 The third parameter.
 * @param p3 The fourth parameter.
 */
fun <T, P0, P1, P2, P3> T.verifyParams(
    func: T.(P0, P1, P2, P3) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>,
    p3: Parameter<P3>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) },
        { e, a -> p3.matcher(e as P3, a as P3) }
    )
    func(p0.expected, p1.expected, p2.expected, p3.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2], [p3]) match for the suspending function call
 * invoked by [func].
 *
 *  @param func The suspending function to invoke.
 *  @param p0 The first parameter.
 *  @param p1 The second parameter.
 *  @param p2 The third parameter.
 *  @param p3 The fourth parameter.
 */
suspend fun <T, P0, P1, P2, P3> T.verifyParams(
    func: suspend T.(P0, P1, P2, P3) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>,
    p3: Parameter<P3>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) },
        { e, a -> p3.matcher(e as P3, a as P3) }
    )
    func(p0.expected, p1.expected, p2.expected, p3.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2], [p3], [p4]) match for the function call invoked by
 * [func].
 *
 * @param func The function to invoke.
 * @param p0 The first parameter.
 * @param p1 The second parameter.
 * @param p2 The third parameter.
 * @param p3 The fourth parameter.
 * @param p4 The fifth parameter.
 */
fun <T, P0, P1, P2, P3, P4> T.verifyParams(
    func: T.(P0, P1, P2, P3, P4) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>,
    p3: Parameter<P3>,
    p4: Parameter<P4>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) },
        { e, a -> p3.matcher(e as P3, a as P3) },
        { e, a -> p4.matcher(e as P4, a as P4) }
    )
    func(p0.expected, p1.expected, p2.expected, p3.expected, p4.expected)
}

/**
 * Verify that the parameters ([p0], [p1], [p2], [p3], [p4]) match for the suspending function call
 * invoked by [func].
 *
 *  @param func The suspending function to invoke.
 *  @param p0 The first parameter.
 *  @param p1 The second parameter.
 *  @param p2 The third parameter.
 *  @param p3 The fourth parameter.
 */
suspend fun <T, P0, P1, P2, P3, P4> T.verifyParams(
    func: suspend T.(P0, P1, P2, P3, P4) -> Unit,
    p0: Parameter<P0>,
    p1: Parameter<P1>,
    p2: Parameter<P2>,
    p3: Parameter<P3>,
    p4: Parameter<P4>
) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { MUST_BE_VERIFYING }
    this._mockingbird_paramMatcher = listOf(
        { e, a -> p0.matcher(e as P0, a as P0) },
        { e, a -> p1.matcher(e as P1, a as P1) },
        { e, a -> p2.matcher(e as P2, a as P2) },
        { e, a -> p3.matcher(e as P3, a as P3) },
        { e, a -> p4.matcher(e as P4, a as P4) }
    )
    func(p0.expected, p1.expected, p2.expected, p3.expected, p4.expected)
}

/**
 * Verify that a function was called without matching any of the parameters.
 *
 * @param invocation The function invocation to verify.
 */
inline fun <T : Any> T.verifyIgnoreParams(invocation: T.() -> Unit) {
    check(this is Verifiable) { MUST_BE_VERIFIABLE }
    check(this._mockingbird_verifying) { "You can only call verifyIgnoreParams inside a verify block" }
    this._mockingbird_paramMatcher = listOf { _, _ -> true }
    this.invocation()
}
