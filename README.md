# StackOverhaul — Fabric

Minecraft Java Edition **1.20.1 / Fabric** port of StackOverhaul. This edition is intended to behave like the Forge edition while using Fabric Loader and Fabric's Mixin environment.

## Features

- Raises the maximum stack size of items that are normally stackable.
- Default maximum: **1500** (configurable from 2 to 1500).
- Items whose original maximum stack size is 1 remain non-stackable.
- Preserves custom per-item and per-slot limits when they are lower than the vanilla default.
- Stores counts above 127 in the `StackOverhaulCount` NBT integer tag while keeping the vanilla byte count clamped for compatibility.
- Applies the configured limit to vanilla item definitions, item stacks, slots, containers, and dropped-item merging.

## Configuration

Run Minecraft once to generate:

```
.minecraft/config/stackoverhaul.json
```

Example:

```json
{
  "maxStackSize": 1500
}
```

The supported range is 2–1500. Restart Minecraft after changing the value.

## Build

Requirements: JDK 25 and Gradle 9.9.0 to run the current Fabric Loom build. The mod itself is compiled for Java 17 and runs on Minecraft 1.20.1's Java runtime.

```sh
gradle build
```

The Fabric JAR is generated in `build/libs/` with the name `StackOverhaul-Fabric-1.20.1-0.1.0-fabric.1.20.1.jar`. GitHub Actions uploads it as a workflow artifact after a successful build.

## Compatibility and testing

This port keeps the original extended-count NBT approach and avoids injecting into `ServerGamePacketListenerImpl`, the point associated with the Stacc API conflict described by the Forge project.

Fabric does not provide Forge's `ItemStackHandler`; therefore, the Forge-specific handler mixin is intentionally not included. Fabric mods that implement their own inventory limits may require separate compatibility work. A successful CI build confirms compilation, not runtime compatibility.

Test item pickup, dropped-item merging, player inventory, vanilla containers, modded inventories, save/reload, and multiplayer synchronization. Back up worlds before testing. The original Forge edition remains on the `main` branch.

## License

No license has been selected yet. All rights reserved by the repository owner.
