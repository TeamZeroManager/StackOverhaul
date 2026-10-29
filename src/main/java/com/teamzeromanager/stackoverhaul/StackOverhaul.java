package com.teamzeromanager.stackoverhaul;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(StackOverhaul.MOD_ID)
public final class StackOverhaul {
    public static final String MOD_ID = "stackoverhaul";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public StackOverhaul() {
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                StackOverhaulConfig.SPEC,
                MOD_ID + "-common.toml"
        );

        LOGGER.info("StackOverhaul initialized. Configured maximum stack size: {}.",
                StackOverhaulConfig.MAX_STACK_SIZE.get());
        LOGGER.warn("StackOverhaul is an early build. Test inventory, save/load, and multiplayer behavior before using it in an important world.");
    }
}
