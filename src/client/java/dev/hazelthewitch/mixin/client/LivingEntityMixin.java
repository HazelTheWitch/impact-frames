package dev.hazelthewitch.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.hazelthewitch.ImpactFramesClient;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("RETURN"), method = "tryUseDeathProtector(Lnet/minecraft/entity/damage/DamageSource;)Z")
    private void injected(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            ImpactFramesClient.impactFrames = 4;
        }
    }
}
