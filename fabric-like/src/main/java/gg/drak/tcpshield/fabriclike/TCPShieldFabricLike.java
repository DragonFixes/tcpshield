package gg.drak.tcpshield.fabriclike;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.eventables.DragonManager;
import net.minecraft.server.MinecraftServer;

public class TCPShieldFabricLike extends TCPShieldMod {
    public void init() {
        // Run our common setup.
        set();

        load();

        DragonManager.init();

        initFabricLike();
    }

    public void onServerStart(MinecraftServer server) {
        DragonManager.onServerStart(server);

        onServerStartFabricLike();
    }

    public void initFabricLike() {
        // do more
    }

    public void onServerStartFabricLike() {
        // do more
    }
}
