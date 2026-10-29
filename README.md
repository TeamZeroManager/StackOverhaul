# StackOverhaul

A Minecraft Java Edition 1.20.1 Forge mod focused on configurable stack sizes for items that are normally stackable, including items registered by other mods.

## Current implementation

- Minecraft 1.20.1 with Forge 47.2.0.
- Forge common config setting: `maxStackSize`.
- Default configured stack limit: **127**.
- Items whose current/original maximum stack size is 1 are left unchanged.
- A Mixin targets `ItemStack#getMaxStackSize()` so the limit is applied through the shared stack-size path.

## Important technical limit

The initial implementation intentionally caps the value at 127. Vanilla Minecraft 1.20.1 stores item counts in signed-byte contexts in important serialization paths; allowing larger counts without patching all relevant save/network paths could corrupt or truncate stack counts. Values above 127 require a more extensive implementation and compatibility testing.

This mod is intended to work with mods that use the standard Minecraft item-stack APIs. It cannot promise compatibility with every mod: some mods implement custom inventory limits, hard-code assumptions about 64 items, or use their own serialization.

## Build environment

- Java 17 JDK
- Minecraft Forge MDK for 1.20.1
- Gradle 8.x compatible with ForgeGradle 6
- IntelliJ IDEA (recommended)

The Gradle Wrapper scripts and wrapper JAR are not included yet. For a local development environment, start from the Forge 1.20.1 MDK and copy its wrapper files into this repository, or install a compatible Gradle version locally.

## Build

After the wrapper files are present, run:

- Windows: `gradlew.bat build`
- Linux/macOS: `./gradlew build`

The built JAR should appear under `build/libs/`.

## Configuration

After the first launch, look for `config/stackoverhaul-common.toml` and adjust `maxStackSize`. Restart the game/world after changing it.

## Status

Early development. The source and build configuration have been added, but the mod has **not yet been compiled or tested in a Minecraft runtime**. The GitHub Actions build workflow will help detect compile/build issues.

## License

No license has been selected yet. Until one is added, all rights are reserved by the repository owner.
