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
    @Inject(method = "getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredSlotLimit(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        // Respect items that are non-stackable by default.
        if (!stack.isEmpty() && stack.getMaxStackSize() > 1) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE.get());
        }
    }
}
