# StackOverhaul

A Minecraft Java Edition 1.20.1 Forge mod focused on configurable stack sizes for items that are normally stackable, with broad compatibility for items added by other mods.

## Project status

Early development. The repository is being set up; builds and in-game compatibility have not yet been verified.

## Goals

- Target Minecraft 1.20.1 and Minecraft Forge 47.x.
- Let players configure a stack-size limit.
- Apply the configured limit automatically to normally stackable items, including items registered by other mods.
- Preserve non-stackable items such as tools and armor by default.
- Document known compatibility limitations and test them instead of claiming universal compatibility.

## Compatibility approach

The intended implementation changes the shared item stack-size behavior rather than maintaining a hand-written list of other mods. Some mods may rely on custom inventory rules, item-specific limits, or assumptions about 64-item stacks; those cases will require testing and may need explicit exclusions or compatibility fixes.

## Development environment

- Java 17 JDK
- Minecraft Forge MDK for 1.20.1
- Gradle / ForgeGradle
- IntelliJ IDEA (recommended)

Use a Forge 1.20.1 MDK matching the Forge version selected in `build.gradle`. The Gradle wrapper files are not included yet; add them from the matching MDK before building locally.

## Configuration target

A common Forge config file will expose a configurable maximum stack size. The initial implementation will support normal stackable items and keep items whose vanilla maximum stack size is 1 unchanged.

## License

No license has been selected yet. Until one is added, all rights are reserved by the repository owner.