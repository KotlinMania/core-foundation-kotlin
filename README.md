# core-foundation-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fcore--foundation--kotlin-blue.svg)](https://github.com/KotlinMania/core-foundation-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/core-foundation-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/core-foundation-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/core-foundation-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/core-foundation-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`servo/core-foundation-rs`](https://github.com/servo/core-foundation-rs).

**Original Project:** This port is based on [`servo/core-foundation-rs`](https://github.com/servo/core-foundation-rs). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `servo/core-foundation-rs`

> The text below is reproduced and lightly edited from [`https://github.com/servo/core-foundation-rs`](https://github.com/servo/core-foundation-rs). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs so they continue to resolve from this repository.

## core-foundation-rs

[![Build Status](https://github.com/servo/core-foundation-rs/actions/workflows/rust.yml/badge.svg)](https://github.com/servo/core-foundation-rs/actions)

## Compatibility

Targets macOS 10.7 by default.

To enable features added in macOS 10.8, set Cargo feature `mac_os_10_8_features`. To have both 10.8 features and 10.7 compatibility, also set `mac_os_10_7_support`. Setting both requires weak linkage, which is a nightly-only feature as of Rust 1.19.

For more experimental but more complete, generated bindings take a look at https://github.com/michaelwu/RustKit.
Other alternatives are https://github.com/nvzqz/fruity and https://gitlab.com/objrs/objrs

## Contributing

If you wish to start contributing or even make a one-off change, simply submit a pull request with the code or documentation change and we'll go from there.

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:core-foundation-kotlin:0.1.0-SNAPSHOT")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same MIT license as the upstream [`servo/core-foundation-rs`](https://github.com/servo/core-foundation-rs). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the core-foundation-rs authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`servo/core-foundation-rs`](https://github.com/servo/core-foundation-rs) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
