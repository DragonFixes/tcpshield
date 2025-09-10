package gg.drak.tcpshield.forge;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.eventables.DragonManager;
import lombok.Getter;
import lombok.Setter;
import net.minecraftforge.fml.common.Mod;

@Getter @Setter
@Mod(TCPShieldMod.MOD_ID)
public final class TCPShieldForge {
    public TCPShieldForge() {
        // Run our common setup.
        DragonManager.init();
    }
}
