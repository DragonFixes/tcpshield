package gg.drak.tcpshield.forge.events;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.eventables.DragonManager;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TCPShieldMod.MOD_ID)
public class ServerEvents {
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        DragonManager.onServerStart(event.getServer());
    }
}
