// port-lint: source src/base.rs
package io.github.kotlinmania.corefoundation.base

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class CFTypeRef internal constructor(
    internal val value: kotlinx.cinterop.CPointer<out kotlinx.cinterop.CPointed>,
)
