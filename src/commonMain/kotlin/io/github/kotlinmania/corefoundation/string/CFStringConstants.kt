// port-lint: source src/string.rs (partial - FFI declarations from core-foundation-sys)
package io.github.kotlinmania.corefoundation.string

// Copyright 2013 The Servo Project Developers. See the COPYRIGHT
// file at the top-level directory of this distribution.
//
// Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or
// http://www.apache.org/licenses/LICENSE-2.0> or the MIT license
// <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your
// option. This file may not be copied, modified, or distributed
// except according to those terms.

import io.github.kotlinmania.corefoundation.base.CFIndex

/**
 * String encoding constants.
 */
typealias CFStringEncoding = UInt

const val kCFStringEncodingMacRoman: CFStringEncoding = 0u
const val kCFStringEncodingWindowsLatin1: CFStringEncoding = 0x0500u
const val kCFStringEncodingISOLatin1: CFStringEncoding = 0x0201u
const val kCFStringEncodingNextStepLatin: CFStringEncoding = 0x0B01u
const val kCFStringEncodingASCII: CFStringEncoding = 0x0600u
const val kCFStringEncodingUnicode: CFStringEncoding = 0x0100u
const val kCFStringEncodingUTF8: CFStringEncoding = 0x08000100u
const val kCFStringEncodingNonLossyASCII: CFStringEncoding = 0x0BFFu
const val kCFStringEncodingUTF16: CFStringEncoding = 0x0100u
const val kCFStringEncodingUTF16BE: CFStringEncoding = 0x10000100u
const val kCFStringEncodingUTF16LE: CFStringEncoding = 0x14000100u
const val kCFStringEncodingUTF32: CFStringEncoding = 0x0c000100u
const val kCFStringEncodingUTF32BE: CFStringEncoding = 0x18000100u
const val kCFStringEncodingUTF32LE: CFStringEncoding = 0x1c000100u

/**
 * Boolean type used in CoreFoundation C API.
 */
typealias Boolean = UByte

/**
 * Range structure.
 */
data class CFRange(
    val location: CFIndex,
    val length: CFIndex
)
