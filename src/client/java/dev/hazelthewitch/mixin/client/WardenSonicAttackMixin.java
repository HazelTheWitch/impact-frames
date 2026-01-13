package dev.hazelthewitch.mixin.client;

import dev.hazelthewitch.ImpactFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.SonicBoomTask;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SonicBoomTask.class)
public class WardenSonicAttackMixin {
    @Inject(
            method = "method_43265(Lnet/minecraft/entity/mob/WardenEntity;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;)V",
            at = @At("HEAD")
    )
    private static void impactFrames$sonicAttack(WardenEntity wardenEntity, ServerWorld serverWorld, LivingEntity target, CallbackInfo ci) {
        ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 18);
        ImpactFramesClient.addImpactFrame(2 * ImpactFramesClient.TICK_DURATION, 13);
    }
}
