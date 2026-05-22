// port-lint: source src/string.rs
package io.github.kotlinmania.corefoundation.string

import io.github.kotlinmania.corefoundation.base.CFIndex
import io.github.kotlinmania.corefoundation.base.CFTypeID
import io.github.kotlinmania.corefoundation.base.CFTypeRef
import io.github.kotlinmania.corefoundation.base.TCFType

actual typealias CFStringRef = kotlinx.cinterop.CPointer<out kotlinx.cinterop.CPointed>

actual class CFString internal constructor(private val ref: CFStringRef) : TCFType<CFStringRef> {
    actual companion object {
        actual fun new(string: String): CFString {
            TODO("Not yet implemented - requires CoreFoundation interop")
        }

        actual fun fromStaticString(string: String): CFString {
            TODO("Not yet implemented - requires CoreFoundation interop")
        }
    }

    actual fun charLen(): CFIndex {
        TODO("Not yet implemented")
    }

    actual override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun asConcreteTypeRef(): CFStringRef {
        TODO("Not yet implemented")
    }

    override fun asCFTypeRef(): CFTypeRef {
        TODO("Not yet implemented")
    }

    override fun typeId(): CFTypeID {
        TODO("Not yet implemented")
    }

    override fun retainCount(): CFIndex {
        TODO("Not yet implemented")
    }

    override fun typeOf(): CFTypeID {
        TODO("Not yet implemented")
    }

    override fun show() {
        TODO("Not yet implemented")
    }
}
