package dev.hazelthewitch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.util.Handle;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImpactFramesClient implements ClientModInitializer {
	public static final Identifier IMPACT_FRAME_SHADER_ID = ImpactFrames.id("impact");
	public static int impact_frames = 0;

	private Framebuffer impactBuffer;

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (impact_frames > 0) {
				impact_frames -= 1;
			}
		});

		WorldRenderEvents.END_MAIN.register(this::renderImpactFrame);
	}

	private void renderImpactFrame(WorldRenderContext context) {
		if (impact_frames <= 0) {
			return;
		}

		FrameGraphBuilder builder = new FrameGraphBuilder();

		MinecraftClient client = MinecraftClient.getInstance();
		Framebuffer mainBuffer = client.getFramebuffer();
		int width = mainBuffer.textureWidth;
		int height = mainBuffer.textureHeight;

		PostEffectProcessor.FramebufferSet framebufferSet = new PostEffectProcessor.FramebufferSet() {
			private final Map<Identifier, Handle<Framebuffer>> objectNodeMap = new HashMap<>();

			{
				Handle<Framebuffer> main = builder.createObjectNode("main", mainBuffer);
				objectNodeMap.put(PostEffectProcessor.MAIN, main);

				Handle<Framebuffer> impact = builder.createObjectNode()
			}

			@Override
			public void set(Identifier id, Handle<Framebuffer> framebuffer) {
				objectNodeMap.put(id, framebuffer);
			}

			@Override
			public @Nullable Handle<Framebuffer> get(Identifier id) {
				return objectNodeMap.get(id);
			}
		};

		try (PostEffectProcessor effect = client.getShaderLoader().loadPostEffect(ImpactFramesClient.IMPACT_FRAME_SHADER_ID, DefaultFramebufferSet.MAIN_ONLY)) {
			if (effect != null) {
				effect.render(client.getFramebuffer(), this.pool);
			}
		}
	}

	@NotNull
	public static PostEffectProcessor impactFrameEffect() {
		return Objects.requireNonNull(MinecraftClient.getInstance().getShaderLoader().loadPostEffect(ImpactFramesClient.IMPACT_FRAME_SHADER_ID, DefaultFramebufferSet.MAIN_ONLY));
	}
}
