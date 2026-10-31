package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Inject(method = "getMaxStackSize()I", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredSlotLimit(CallbackInfoReturnable<Integer> cir) {
        // Preserve deliberately restricted modded slots (for example, 1, 16 or 32).
        if (cir.getReturnValueI() == 64) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE.get());
        }
    }

    @Inject(method = "getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredItemSlotLimit(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (!stack.isEmpty() && stack.getMaxStackSize() > 1 && cir.getReturnValueI() == 64) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE.get());
        }
    }
}
