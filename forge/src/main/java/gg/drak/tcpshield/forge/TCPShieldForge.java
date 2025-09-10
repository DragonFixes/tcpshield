package gg.drak.tcpshield.forge;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.eventables.DragonManager;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;

@Getter @Setter
@Mod(TCPShieldMod.MOD_ID)
public final class TCPShieldForge extends TCPShieldMod {
    public TCPShieldForge() {
        // Run our common setup.
        set();

        init();
    }

    public void init() {
        // Run our common setup.
        load();

        DragonManager.init();
    }
}
