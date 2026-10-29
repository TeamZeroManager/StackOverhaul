package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Container.class)
public interface ContainerMixin {
    /**
     * @author StackOverhaul
     * @reason Use the configured capacity for containers that rely on the vanilla default.
     */
    @Overwrite
    default int getMaxStackSize() {
        return StackOverhaulConfig.MAX_STACK_SIZE.get();
    }
}
