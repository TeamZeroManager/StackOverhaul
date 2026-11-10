package com.teamzeromanager.stackoverhaul;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StackOverhaul implements ModInitializer {
    public static final String MOD_ID = "stackoverhaul";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        StackOverhaulConfig.load();

        LOGGER.info("StackOverhaul (Fabric) initialized. Configured maximum stack size: {}.",
                StackOverhaulConfig.MAX_STACK_SIZE);
        LOGGER.warn("StackOverhaul changes vanilla item stack internals. Back up worlds and test modded inventories and multiplayer before relying on it.");
    }
}
