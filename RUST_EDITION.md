# Rust Edition status

This fork is preparing an optional Horizon (Rust) backend while preserving the existing Java launch path as the default. Horizon is an independent engine project; the launcher integration is not yet a playable or released feature.

## Current limitations

- Java remains the only wired launch backend. Do not select or expect Horizon launches yet.
- Horizon upstream targets vanilla Minecraft 26.3. Mod loaders and Java mods are unsupported.
- The Android native library, verified launch-request adapter, account/session handoff, input bridge, and device testing are not complete in this fork.
- The existing Android build workflow builds Zalith Launcher APKs; it does not yet build or embed a Horizon engine library.

## Intended build and test workflow

The planned workflow will pin a Starling source revision, build its Android native library for arm64-v8a with cargo-ndk, embed the library in the launcher APK, and upload a debug APK artifact. Until that workflow and the launch adapter land, a normal launcher APK only exercises the existing Java runtime.

Horizon engine reference: https://github.com/starling-client/starling
