package gg.drak.tcpshield.fabriclike;

import gg.drak.tcpshield.eventables.DragonManager;
import net.minecraft.server.MinecraftServer;

public final class TCPShieldFabricLike {
    public static void init() {
        // Run our common setup.
        DragonManager.init();
    }

    public static void onServerStart(MinecraftServer server) {
        DragonManager.onServerStart(server);
    }
}
