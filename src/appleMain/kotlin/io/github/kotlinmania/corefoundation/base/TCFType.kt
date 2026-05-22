// port-lint: source src/base.rs
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package io.github.kotlinmania.corefoundation.base

// Copyright 2013 The Servo Project Developers. See the COPYRIGHT
// file at the top-level directory of this distribution.
//
// Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or
// http://www.apache.org/licenses/LICENSE-2.0> or the MIT license
// <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your
// option. This file may not be copied, modified, or distributed
// except according to those terms.

/**
 * Type ID for CF types.
 */
typealias CFTypeID = ULong

/**
 * Index type used throughout CoreFoundation.
 */
typealias CFIndex = Long

/**
 * Generic CF type reference.
 *
 * The upstream Rust crate aliases `CFTypeRef` to `*const c_void`. On Apple targets in Kotlin/Native
 * this is the cinterop `COpaquePointer?` that any Core Foundation object reference can be held as.
 */
typealias CFTypeRef = kotlinx.cinterop.COpaquePointer?

/**
 * Helpers for constructing CFIndex values.
 *
 * Note: only the ULong overload performs bounds checking; Int/Long values are always representable
 * as CFIndex on supported Kotlin targets.
 */
fun Int.toCFIndex(): CFIndex = this.toLong()

fun Long.toCFIndex(): CFIndex = this

fun ULong.toCFIndex(): CFIndex {
    require(this <= Long.MAX_VALUE.toULong()) { "value out of range" }
    return this.toLong()
}

/**
 * All Core Foundation types implement this interface. The associated type Ref specifies the
 * associated Core Foundation type: e.g. for CFType this is CFTypeRef; for CFArray this is
 * CFArrayRef.
 */
interface TCFType<Ref> {
    /**
     * Returns the object as its concrete TypeRef.
     */
    fun asConcreteTypeRef(): Ref

    /**
     * Returns the object as a raw CFTypeRef. The reference count is not adjusted.
     */
    fun asCFTypeRef(): CFTypeRef

    /**
     * Returns the type ID for this class.
     */
    fun typeId(): CFTypeID

    /**
     * Returns the reference count of the object. It is unwise to do anything other than test
     * whether the return value of this method is greater than zero.
     */
    fun retainCount(): CFIndex

    /**
     * Returns the type ID of this object.
     */
    fun typeOf(): CFTypeID

    /**
     * Writes a debugging version of this object on standard error.
     */
    fun show()
}
