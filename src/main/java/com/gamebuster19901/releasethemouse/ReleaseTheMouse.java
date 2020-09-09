package com.gamebuster19901.releasethemouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("releasethemouse")
public final class ReleaseTheMouse {

	private static final Logger LOGGER = LogManager.getLogger();
	
	public ReleaseTheMouse() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}
	
	private void setup(FMLClientSetupEvent e) {
		MouseReleaseManager.init();
	}
	
}
