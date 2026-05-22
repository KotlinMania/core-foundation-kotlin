// port-lint: source src/string.rs
package io.github.kotlinmania.corefoundation.string

// Copyright 2013 The Servo Project Developers. See the COPYRIGHT
// file at the top-level directory of this distribution.
//
// Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or
// http://www.apache.org/licenses/LICENSE-2.0> or the MIT license
// <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your
// option. This file may not be copied, modified, or distributed
// except according to those terms.

/**
 * Immutable strings.
 */

import io.github.kotlinmania.corefoundation.base.CFIndex
import io.github.kotlinmania.corefoundation.base.TCFType

/**
 * Reference to a CFString.
 */
expect class CFStringRef

/**
 * An immutable string in one of a variety of encodings.
 */
expect class CFString : TCFType<CFStringRef> {
    companion object {
        /**
         * Creates a new CFString instance from a Kotlin string.
         */
        fun new(string: String): CFString

        /**
         * Like CFString.new, but references a string that can be used as a backing store
         * by virtue of being statically allocated.
         */
        fun fromStaticString(string: String): CFString
    }

    /**
     * Returns the number of characters in the string.
     */
    fun charLen(): CFIndex

    /**
     * Converts the CFString to a Kotlin String.
     */
    override fun toString(): String
}

/**
 * Creates a CFString from a Kotlin String.
 */
fun String.toCFString(): CFString = CFString.new(this)
