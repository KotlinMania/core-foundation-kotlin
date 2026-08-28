// port-lint: tests tests/string.rs
package io.github.kotlinmania.corefoundation.string

import kotlin.test.Test
import kotlin.test.assertEquals

class CFStringTest {
    @Test
    fun testStringAndBack() {
        val original = "The quick brown fox jumped over the slow lazy dog."
        val cfstr = CFString.fromStaticString(original)
        val converted = cfstr.toString()
        assertEquals(original, converted)
    }

    @Test
    fun testExtensionToCFString() {
        val text = "Hello from Kotlin Multiplatform"
        val cfstr = text.toCFString()
        assertEquals(text, cfstr.toString())
        assertEquals(text.length.toLong(), cfstr.charLen())
    }
}
