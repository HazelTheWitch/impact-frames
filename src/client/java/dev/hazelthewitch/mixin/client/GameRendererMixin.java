package dev.hazelthewitch.mixin.client;

import dev.hazelthewitch.GameRendererPoolAccessor;
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
public class GameRendererMixin implements GameRendererPoolAccessor {
    @Final
    @Shadow
    private Pool pool;

    @Override
    public Pool getPool() {
        return this.pool;
    }
}
