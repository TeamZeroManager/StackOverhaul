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
                        "Items whose original maximum stack size is 1 remain non-stackable.",
                        "Counts above 127 require extended ItemStack serialization support.",
                        "StackOverhaul stores the extended count in NBT; network compatibility still depends on installed mods."
                )
                .defineInRange("maxStackSize", 500, 2, 500);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
