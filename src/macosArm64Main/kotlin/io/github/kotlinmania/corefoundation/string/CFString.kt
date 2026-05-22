// port-lint: source src/string.rs
package io.github.kotlinmania.corefoundation.string

import CoreFoundation.*
import io.github.kotlinmania.corefoundation.base.CFIndex
import io.github.kotlinmania.corefoundation.base.CFTypeID
import io.github.kotlinmania.corefoundation.base.CFTypeRef
import io.github.kotlinmania.corefoundation.base.TCFType
import io.github.kotlinmania.corefoundation.base.toCFIndex
import kotlinx.cinterop.*

actual typealias CFStringRef = kotlinx.cinterop.COpaquePointer?

actual class CFString internal constructor(private val ref: CFStringRef) : TCFType<CFStringRef> {
    actual companion object {
        actual fun new(string: String): CFString {
            val bytes = string.encodeToByteArray()
            val stringRef = if (bytes.isEmpty()) {
                CoreFoundation.CFStringCreateWithBytes(
                    CoreFoundation.kCFAllocatorDefault,
                    null,
                    0.toCFIndex(),
                    CoreFoundation.kCFStringEncodingUTF8.toUInt(),
                    0u.convert()
                )
            } else {
                bytes.usePinned { pinned ->
                    CoreFoundation.CFStringCreateWithBytes(
                        CoreFoundation.kCFAllocatorDefault,
                        pinned.addressOf(0).reinterpret(),
                        bytes.size.toCFIndex(),
                        CoreFoundation.kCFStringEncodingUTF8.toUInt(),
                        0u.convert()
                    )
                }
            }
            require(stringRef != null) { "Failed to create CFString" }
            return CFString(stringRef)
        }

        actual fun fromStaticString(string: String): CFString {
            // Kotlin ByteArray backing stores are not statically allocated and cannot be safely
            // handed to CFStringCreateWithBytesNoCopy as a long-lived buffer.
            return new(string)
        }
    }

    actual fun charLen(): CFIndex {
        return CoreFoundation.CFStringGetLength(ref)
    }

    actual override fun toString(): String {
        val cString = CoreFoundation.CFStringGetCStringPtr(ref, CoreFoundation.kCFStringEncodingUTF8.toUInt())
        if (cString != null) {
            return cString.toKString()
        }

        val length = charLen()
        if (length == 0L) {
            return ""
        }

        memScoped {
            val bytesRequired = alloc<CFIndexVar>()
            val charsMeasured = CoreFoundation.CFStringGetBytes(
                ref,
                CoreFoundation.CFRangeMake(0, length),
                CoreFoundation.kCFStringEncodingUTF8.toUInt(),
                0u.convert(),
                0u.convert(),
                null,
                0,
                bytesRequired.ptr
            )
            require(charsMeasured == length) { "Failed to measure CFString UTF-8 length" }
            require(bytesRequired.value in 1..Int.MAX_VALUE.toLong()) { "CFString too large to convert to Kotlin String" }

            val buffer = ByteArray(bytesRequired.value.toInt())
            buffer.usePinned { pinned ->
                val bytesUsed = alloc<CFIndexVar>()
                val charsWritten = CoreFoundation.CFStringGetBytes(
                    ref,
                    CoreFoundation.CFRangeMake(0, length),
                    CoreFoundation.kCFStringEncodingUTF8.toUInt(),
                    0u.convert(),
                    0u.convert(),
                    pinned.addressOf(0).reinterpret(),
                    buffer.size.toCFIndex(),
                    bytesUsed.ptr
                )
                require(charsWritten == length) { "Failed to convert CFString to String" }
                require(bytesUsed.value == buffer.size.toLong()) { "Unexpected bytes used" }
            }
            return buffer.decodeToString()
        }
    }

    override fun asConcreteTypeRef(): CFStringRef {
        return ref
    }

    override fun asCFTypeRef(): CFTypeRef {
        return ref?.reinterpret()
    }

    override fun typeId(): CFTypeID {
        return CoreFoundation.CFStringGetTypeID()
    }

    override fun retainCount(): CFIndex {
        return CoreFoundation.CFGetRetainCount(ref)
    }

    override fun typeOf(): CFTypeID {
        return CoreFoundation.CFGetTypeID(ref)
    }

    override fun show() {
        CoreFoundation.CFShow(ref)
    }
}
