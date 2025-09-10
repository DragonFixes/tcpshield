package gg.drak.tcpshield.quilt;

import gg.drak.tcpshield.fabriclike.TCPShieldFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;

public final class TCPShieldQuilt extends TCPShieldFabricLike implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        init();

        ServerLifecycleEvents.STARTING.register(this::onServerStart);
    }
}
