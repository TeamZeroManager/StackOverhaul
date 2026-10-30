# StackOverhaul

Minecraft Java Edition **1.20.1 / Forge 47.2.0** mod that raises the maximum stack size of items which are already stackable. The goal is to avoid the known Bigger Stacks mixin collision with Stacc API in the target modpack.

## Current implementation

- Registers a Forge common configuration file named `stackoverhaul-common.toml`.
- The current default and configurable ceiling are **1500** (minimum 2).
- Items that are non-stackable by default remain non-stackable.
- Extended counts are stored in the `StackOverhaulCount` NBT tag when they exceed 127.
- The Mixin does **not** target the `ServerGamePacketListenerImpl` constant that conflicts with Stacc's `CreativeDesyncFixin`.
- GitHub Actions builds the JAR and uploads it as an artifact for 14 days after each successful run.

## Testing status

- **127 items per stack:** previously tested successfully.
- **500 items per stack:** tested successfully in the Otherworld single-player instance, including chest storage and reopening the world without losing the count.
- **1500 items per stack:** newly configured; runtime testing is still required.
- Block placement, item pickup, every modded inventory, multiplayer synchronization, and compatibility with all installed mods should be tested before relying on the new limit in an important world.

A successful CI build confirms compilation only; it does not confirm runtime mixin loading or every inventory and network path.

## Compatibility notes

The mod is designed to coexist with Stacc API by avoiding the specific conflicting injection point used by Bigger Stacks. The current successful tests are limited to the reported single-player Otherworld setup; broader compatibility is not guaranteed.

## Build

Requirements: Java 17 and Gradle 8.8 (or the compatible Gradle 8.x release used by the CI workflow).

Run:

```sh
gradle build
```

The output JAR is in `build/libs/`. GitHub Actions also publishes a downloadable build artifact on each successful workflow run.

## Configuration

Launch Minecraft once, then edit `config/stackoverhaul-common.toml`. The default is now `maxStackSize = 1500`, and the allowed range is 2–1500. Restart Minecraft after changing the value. Back up your world before testing mods that alter inventory behavior.

## Status

Early development. The 500-item limit has been tested in the target single-player modpack; the new 1500-item ceiling and broader behavior still need in-game validation.

## License

No license has been selected yet. All rights reserved by the repository owner.
