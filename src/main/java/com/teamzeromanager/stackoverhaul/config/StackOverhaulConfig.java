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
                        "Counts above 127 use StackOverhaul's extended NBT count handling.",
                        "The 1500 limit is the current configured ceiling; larger limits may be considered in future updates."
                )
                .defineInRange("maxStackSize", 1500, 2, 1500);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
