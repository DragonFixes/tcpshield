package net.tcpshield.tcpshield.forge.events;

import net.tcpshield.tcpshield.TCPShieldMod;
import net.tcpshield.tcpshield.eventables.DragonManager;
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
