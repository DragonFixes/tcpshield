package net.tcpshield.tcpshield.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import net.tcpshield.tcpshield.fabriclike.TCPShieldFabricLike;
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents;

public final class TCPShieldQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        TCPShieldFabricLike.init();

        ServerLifecycleEvents.STARTING.register(TCPShieldFabricLike::onServerStart);
    }
}
