package com.teamzeromanager.stackoverhaul.mixin;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 1.20.1's item-stack network format writes Count as a signed byte.
 * Add a VarInt after each encoded stack so counts above 127 survive
 * client/server inventory synchronization. Both sides must run this mod.
 */
@Mixin(FriendlyByteBuf.class)
public abstract class FriendlyByteBufMixin {
    @Shadow public abstract FriendlyByteBuf writeVarInt(int value);
    @Shadow public abstract int readVarInt();

    @Inject(method = "writeItem", at = @At("TAIL"))
    private void stackoverhaul$writeExtendedNetworkCount(ItemStack stack, CallbackInfo ci) {
        writeVarInt(stack.isEmpty() ? 0 : stack.getCount());
    }

    @Inject(method = "readItem", at = @At("TAIL"))
    private void stackoverhaul$readExtendedNetworkCount(CallbackInfoReturnable<ItemStack> cir) {
        int count = readVarInt();
        ItemStack stack = cir.getReturnValue();
        if (stack != null && !stack.isEmpty() && count > 0) {
            stack.setCount(count);
        }
    }
}
