package com.teamzeromanager.stackoverhaul.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamzeromanager.stackoverhaul.StackOverhaul;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class StackOverhaulConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int DEFAULT_MAX_STACK_SIZE = 1500;
    private static final int MIN_MAX_STACK_SIZE = 2;
    private static final int MAX_MAX_STACK_SIZE = 1500;

    public static volatile int MAX_STACK_SIZE = DEFAULT_MAX_STACK_SIZE;

    private StackOverhaulConfig() {
    }

    public static void load() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("stackoverhaul.json");

        try {
            if (Files.notExists(configPath)) {
                saveDefault(configPath);
                return;
            }

            JsonObject json = JsonParser.parseString(Files.readString(configPath)).getAsJsonObject();
            int configuredValue = json.has("maxStackSize")
                    ? json.get("maxStackSize").getAsInt()
                    : DEFAULT_MAX_STACK_SIZE;

            MAX_STACK_SIZE = clamp(configuredValue);
            if (MAX_STACK_SIZE != configuredValue) {
                StackOverhaul.LOGGER.warn("maxStackSize was outside the supported range (2-1500); using {}.",
                        MAX_STACK_SIZE);
                saveDefault(configPath);
            }
        } catch (Exception exception) {
            MAX_STACK_SIZE = DEFAULT_MAX_STACK_SIZE;
            StackOverhaul.LOGGER.error("Could not read StackOverhaul configuration. Using the default value of {}.",
                    DEFAULT_MAX_STACK_SIZE, exception);
            try {
                saveDefault(configPath);
            } catch (IOException writeException) {
                StackOverhaul.LOGGER.error("Could not create the default StackOverhaul configuration.",
                        writeException);
            }
        }
    }

    private static int clamp(int value) {
        return Math.max(MIN_MAX_STACK_SIZE, Math.min(MAX_MAX_STACK_SIZE, value));
    }

    private static void saveDefault(Path configPath) throws IOException {
        Files.createDirectories(configPath.getParent());
        JsonObject json = new JsonObject();
        json.addProperty("maxStackSize", MAX_STACK_SIZE);
        json.addProperty("_comment", "Maximum size for normally stackable items. Valid range: 2-1500. Restart Minecraft after editing.");
        Files.writeString(configPath, GSON.toJson(json));
    }
}
