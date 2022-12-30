package com.dm.earth.cabricality.server;

import com.dm.earth.cabricality.Cabricality;

import com.dm.earth.cabricality.util.debug.CabfLogger;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

import com.dm.earth.cabricality.util.mod.CabfModDeps;

public class CabricalityServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		if (CabfModDeps.isLoaded(true, true))
			throw new RuntimeException(CabfModDeps.asString(true, true) + " is missing for " + Cabricality.NAME + "!");
		else if (CabfModDeps.isLoaded(false, true))
			CabfLogger.logWarn("Recommended mods " + CabfModDeps.asString(false, true) + " is missing for " + Cabricality.NAME + "!");
	}
}
