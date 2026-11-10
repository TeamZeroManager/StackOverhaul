package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredLimit(CallbackInfoReturnable<Integer> cir) {
        // Only replace the vanilla default; retain modded per-item stack limits.
        if (cir.getReturnValueI() == 64) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE);
        }
    }

    /**
     * Vanilla 1.20.1 stores Count as a signed byte in item NBT.
     * Keep a safe byte value for compatibility and persist the real amount
     * in a separate integer tag.
     */
    @Inject(
            method = "save(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;",
            at = @At("TAIL")
    )
    private void stackoverhaul$saveExtendedCount(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        int count = ((ItemStack) (Object) this).getCount();
        if (count > Byte.MAX_VALUE) {
            tag.putByte("Count", (byte) Byte.MAX_VALUE);
            tag.putInt("StackOverhaulCount", count);
        } else {
            tag.remove("StackOverhaulCount");
        }
    }

    /**
     * Restore after vanilla has finished reading the byte-sized Count field.
     */
    @Inject(method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void stackoverhaul$loadExtendedCount(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("StackOverhaulCount")) {
            int count = tag.getInt("StackOverhaulCount");
            ((ItemStackAccessor) (Object) this).stackoverhaul$setCount(
                    Math.max(0, Math.min(count, StackOverhaulConfig.MAX_STACK_SIZE))
            );
        }
    }
}
