package gg.drak.tcpshield.fabric;

import gg.drak.tcpshield.eventables.DragonManager;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

@Getter @Setter
public final class TCPShieldFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        DragonManager.init();

        ServerLifecycleEvents.SERVER_STARTING.register(DragonManager::onServerStart);
    }
}
