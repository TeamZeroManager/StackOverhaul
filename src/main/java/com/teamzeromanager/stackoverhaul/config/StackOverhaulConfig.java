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
                        "The initial implementation is capped at 127 to stay within vanilla's signed-byte item-count constraints."
                )
                .defineInRange("maxStackSize", 127, 2, 127);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
