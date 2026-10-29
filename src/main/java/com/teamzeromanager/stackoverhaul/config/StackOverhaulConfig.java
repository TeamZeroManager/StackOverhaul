package com.teamzeromanager.stackoverhaul.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class StackOverhaulConfig {
    private StackOverhaulConfig() {
    }

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue MAX_STACK_SIZE;

    static {
        BUILDER.push("general");

        MAX_STACK_SIZE = BUILDER
                .comment(
                        "Maximum stack size for items that are normally stackable.",
                        "Items whose default maximum stack size is 1 are intended to remain non-stackable.",
                        "The effective supported limit still depends on Minecraft and other mods."
                )
                .defineInRange("maxStackSize", 256, 2, 9999);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
