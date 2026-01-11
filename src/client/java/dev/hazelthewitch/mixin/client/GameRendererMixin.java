package dev.hazelthewitch.mixin.client;

import dev.hazelthewitch.ImpactFrames;
import dev.hazelthewitch.ImpactFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.*;
import net.minecraft.client.util.Pool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Final
    @Shadow
    private Pool pool;

    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawEntityOutlinesFramebuffer()V", shift = At.Shift.AFTER), method = "render")
    private void injected(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        try (PostEffectProcessor effect = this.client.getShaderLoader().loadPostEffect(ImpactFramesClient.IMPACT_FRAME_SHADER_ID, DefaultFramebufferSet.MAIN_ONLY)) {
            if (effect != null) {
                effect.render(this.client.getFramebuffer(), this.pool);
            }
        }
    }
}
