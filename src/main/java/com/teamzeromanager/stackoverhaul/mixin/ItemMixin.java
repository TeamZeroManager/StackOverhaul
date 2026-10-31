package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredLimit(CallbackInfoReturnable<Integer> cir) {
        // Expand the vanilla default only. Preserve custom limits assigned by other mods.
        if (cir.getReturnValueI() == 64) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE.get());
        }
    }
}
