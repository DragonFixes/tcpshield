package net.tcpshield.tcpshield.forge;

import net.tcpshield.tcpshield.TCPShieldMod;
import net.tcpshield.tcpshield.eventables.DragonManager;
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
