package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void stackoverhaul$applyConfiguredLimit(CallbackInfoReturnable<Integer> cir) {
        // Only replace the vanilla default; retain modded per-item stack limits.
        if (cir.getReturnValueI() == 64) {
            cir.setReturnValue(StackOverhaulConfig.MAX_STACK_SIZE.get());
        }
    }

    /**
     * Vanilla stores Count as a signed byte. Keep a clamped Count for older
     * readers and store the real amount in a separate integer tag.
     */
    @Redirect(
            method = "save",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/CompoundTag;putByte(Ljava/lang/String;B)V"
            )
    )
    private void stackoverhaul$saveExtendedCount(CompoundTag tag, String key, byte ignoredCount) {
        int count = ((ItemStack) (Object) this).getCount();
        tag.putByte(key, (byte) Math.min(count, Byte.MAX_VALUE));

        if (count > Byte.MAX_VALUE) {
            tag.putInt("StackOverhaulCount", count);
        } else {
            tag.remove("StackOverhaulCount");
        }
    }

    /**
     * Restore the integer count after vanilla's constructor assigns the
     * byte-sized Count value.
     */
    @Redirect(
            method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/ItemStack;count:I",
                    opcode = org.objectweb.asm.Opcodes.PUTFIELD
            )
    )
    private void stackoverhaul$loadExtendedCount(ItemStack stack, int vanillaCount, CompoundTag tag) {
        int count = tag.contains("StackOverhaulCount")
                ? tag.getInt("StackOverhaulCount")
                : vanillaCount;
        ((ItemStackAccessor) (Object) stack).stackoverhaul$setCount(count);
    }
}
