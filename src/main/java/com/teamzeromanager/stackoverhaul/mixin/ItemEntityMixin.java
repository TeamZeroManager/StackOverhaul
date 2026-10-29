package com.teamzeromanager.stackoverhaul.mixin;

import com.teamzeromanager.stackoverhaul.config.StackOverhaulConfig;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @ModifyConstant(
            method = "merge(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V",
            constant = @Constant(intValue = 64)
    )
    private static int stackoverhaul$extendDroppedStackLimit(int originalLimit) {
        return Math.max(originalLimit, StackOverhaulConfig.MAX_STACK_SIZE.get());
    }
}
