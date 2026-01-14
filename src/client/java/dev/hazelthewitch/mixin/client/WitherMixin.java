package dev.hazelthewitch.mixin.client;

import dev.hazelthewitch.ImpactFramesClient;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherEntity.class)
public class WitherMixin {
    @Inject(
            method = "mobTick(Lnet/minecraft/server/world/ServerWorld;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)V")
    )
    private void impactFrames$mobTick(ServerWorld world, CallbackInfo ci) {
        ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 18);
        ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 13);
    }
}
