package dev.hazelthewitch;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactFrames implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("impact-frames");
	public static final String MOD_ID = "impact-frames";

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized.");
	}
}
