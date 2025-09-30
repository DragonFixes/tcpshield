package gg.drak.tcpshield.mixin;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.handler.HandshakeEvent;
import gg.drak.tcpshield.handler.TCPShieldPacket;
import gg.drak.tcpshield.handler.TCPShieldPlayer;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.server.network.ServerHandshakeNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerHandshakeNetworkHandler.class)
public class ServerHandshakeNetworkHandlerMixin {
    @Inject(method = "onHandshake", at = @At("HEAD"), cancellable = true)
    public void onHandshake(HandshakeC2SPacket packet, CallbackInfo ci) {
        HandshakeEvent event = new HandshakeEvent((IClientIntentionPacketAccessor) packet).fire();
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }

        TCPShieldPacket p = new TCPShieldPacket(event);
        TCPShieldPlayer player = new TCPShieldPlayer(event);

        boolean cancel = TCPShieldMod.INSTANCE.getPacketHandler().handleHandshake(p, player);

        if (event.isCancelled() || cancel || event.isFailed()) {
            ci.cancel();
        }
    }
}
