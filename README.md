# StackOverhaul

Minecraft Java Edition **1.20.1 / Forge 47.2.0** mod that raises the maximum stack size of items which are already stackable, with a configurable limit. The goal is to avoid the known Bigger Stacks mixin collision with Stacc API in the target modpack.

## Current implementation

- Registers a Forge common configuration file named `stackoverhaul-common.toml`.
- `maxStackSize` defaults to **127** and can be set from 2 to 127.
- A Mixin changes `ItemStack#getMaxStackSize()` for stacks whose item already has a maximum greater than 1. Non-stackable items remain non-stackable.
- The Mixin does **not** target the `ServerGamePacketListenerImpl` constant that conflicts with Stacc's `CreativeDesyncFixin`.
- GitHub Actions builds the JAR and uploads it as an artifact for 14 days after each successful run.

## Compatibility and limitations

This is an initial, conservative implementation. It has not yet been verified in a live Minecraft client or in the full Otherworld [Dungeons & Dragons] modpack. A successful CI build confirms compilation only; it does not confirm runtime mixin loading or every inventory's behavior.

The limit is capped at 127 for this stage because Minecraft 1.20.1 has byte-sized item-count representations in network serialization paths. Values above 127 must not be enabled without replacing or patching the relevant serialization and synchronization paths. Some modded inventories also enforce their own limits.

The mod is designed to coexist with the Stacc API by avoiding the specific conflicting injection point. Full compatibility with Numismatic Overhaul and all other mods still requires in-game testing.

## Build

Requirements: Java 17 and Gradle 8.8 (or the compatible Gradle 8.x release used by the CI workflow).

Run:

```sh
gradle build
```

The output JAR is in `build/libs/`. GitHub Actions also publishes a downloadable build artifact on each successful workflow run.

## Configuration

Launch Minecraft once, then edit `config/stackoverhaul-common.toml`. Change `maxStackSize` and restart Minecraft. Back up your world before testing mods that alter inventory behavior.

## Status

Early development. Compilation is automated, but runtime testing in Minecraft and in the target modpack remains outstanding.

## License

No license has been selected yet. All rights reserved by the repository owner.
