// port-lint: source src/string.rs
@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)

package io.github.kotlinmania.corefoundation.string

import io.github.kotlinmania.corefoundation.base.CFIndex
import io.github.kotlinmania.corefoundation.base.CFTypeID
import io.github.kotlinmania.corefoundation.base.CFTypeRef
import io.github.kotlinmania.corefoundation.base.TCFType
import io.github.kotlinmania.corefoundation.base.toCFIndex
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import platform.CoreFoundation.CFGetRetainCount
import platform.CoreFoundation.CFGetTypeID
import platform.CoreFoundation.CFIndexVar
import platform.CoreFoundation.CFRangeMake
import platform.CoreFoundation.CFShow
import platform.CoreFoundation.CFStringCreateWithBytes
import platform.CoreFoundation.CFStringGetBytes
import platform.CoreFoundation.CFStringGetCStringPtr
import platform.CoreFoundation.CFStringGetLength
import platform.CoreFoundation.CFStringGetTypeID
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreFoundation.kCFStringEncodingUTF8

typealias CFStringRef = platform.CoreFoundation.CFStringRef

class CFString internal constructor(
    private val ref: CFStringRef,
) : TCFType<CFStringRef> {
    companion object {
        fun new(string: String): CFString {
            val bytes = string.encodeToByteArray()
            val stringRef =
                if (bytes.isEmpty()) {
                    CFStringCreateWithBytes(
                        kCFAllocatorDefault,
                        null,
                        0.toCFIndex(),
                        kCFStringEncodingUTF8,
                        false,
                    )
                } else {
                    bytes.usePinned { pinned ->
                        CFStringCreateWithBytes(
                            kCFAllocatorDefault,
                            pinned.addressOf(0).reinterpret(),
                            bytes.size.toCFIndex(),
                            kCFStringEncodingUTF8,
                            false,
                        )
                    }
                }
            require(stringRef != null) { "Failed to create CFString" }
            return CFString(stringRef)
        }

        fun fromStaticString(string: String): CFString {
            // Kotlin ByteArray backing stores are not statically allocated and cannot be safely
            // handed to CFStringCreateWithBytesNoCopy as a long-lived buffer.
            return new(string)
        }
    }

    fun charLen(): CFIndex = CFStringGetLength(ref)

    override fun toString(): String {
        val cString = CFStringGetCStringPtr(ref, kCFStringEncodingUTF8)
        if (cString != null) {
            return cString.toKString()
        }

        val length = charLen()
        if (length == 0L) {
            return ""
        }

        memScoped {
            val bytesRequired = alloc<CFIndexVar>()
            val charsMeasured =
                CFStringGetBytes(
                    ref,
                    CFRangeMake(0, length),
                    kCFStringEncodingUTF8,
                    0u.convert(),
                    false,
                    null,
                    0,
                    bytesRequired.ptr,
                )
            require(charsMeasured == length) { "Failed to measure CFString UTF-8 length" }
            require(bytesRequired.value in 1..Int.MAX_VALUE.toLong()) { "CFString too large to convert to Kotlin String" }

            val buffer = ByteArray(bytesRequired.value.toInt())
            buffer.usePinned { pinned ->
                val bytesUsed = alloc<CFIndexVar>()
                val charsWritten =
                    CFStringGetBytes(
                        ref,
                        CFRangeMake(0, length),
                        kCFStringEncodingUTF8,
                        0u.convert(),
                        false,
                        pinned.addressOf(0).reinterpret(),
                        buffer.size.toCFIndex(),
                        bytesUsed.ptr,
                    )
                require(charsWritten == length) { "Failed to convert CFString to String" }
                require(bytesUsed.value == buffer.size.toLong()) { "Unexpected bytes used" }
            }
            return buffer.decodeToString()
        }
    }

    override fun asConcreteTypeRef(): CFStringRef = ref

    override fun asCFTypeRef(): CFTypeRef = ref

    override fun typeId(): CFTypeID = CFStringGetTypeID()

    override fun retainCount(): CFIndex = CFGetRetainCount(ref)

    override fun typeOf(): CFTypeID = CFGetTypeID(ref)

    override fun show() {
        CFShow(ref)
    }
}

/**
 * Creates a CFString from a Kotlin String.
 */
fun String.toCFString(): CFString = CFString.new(this)
