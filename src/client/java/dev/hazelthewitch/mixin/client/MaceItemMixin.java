package dev.hazelthewitch.mixin.client;

import dev.hazelthewitch.ImpactFramesClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MaceItem.class)
public abstract class MaceItemMixin {
    @Shadow
    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return false;
    }

    @Inject(
            method = "postHit(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/LivingEntity;)V",
            at = @At("HEAD")
    )
    private void impactFrames$hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfo ci) {
        if (shouldDealAdditionalDamage(attacker)) {
            ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 18);
            ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 13);
        }
    }
}
