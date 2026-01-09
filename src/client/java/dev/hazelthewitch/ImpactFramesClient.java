package dev.hazelthewitch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ImpactFramesClient implements ClientModInitializer {
	public static final Identifier IMPACT_FRAME_SHADER_ID = ImpactFrames.id("impact");
	public static int impact_frames = 0;

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (impact_frames > 0) {
				impact_frames -= 1;
			}
		});
	}

	@NotNull
	public static PostEffectProcessor impactFrameEffect() {
		return Objects.requireNonNull(MinecraftClient.getInstance().getShaderLoader().loadPostEffect(ImpactFramesClient.IMPACT_FRAME_SHADER_ID, DefaultFramebufferSet.MAIN_ONLY));
	}
}
