package net.tcpshield.tcpshield.mixin;

import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.server.network.ServerHandshakePacketListenerImpl;
import net.tcpshield.tcpshield.TCPShieldMod;
import net.tcpshield.tcpshield.handler.HandshakeEvent;
import net.tcpshield.tcpshield.handler.TCPShieldPacket;
import net.tcpshield.tcpshield.handler.TCPShieldPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerHandshakePacketListenerImpl.class)
public class ServerHandshakePacketListenerMixin {
    @Inject(method = "handleIntention", at = @At("HEAD"), cancellable = true)
    public void onHandshake(ClientIntentionPacket packet, CallbackInfo ci) {
        HandshakeEvent event = new HandshakeEvent((IClientIntentionPacketAccessor) (Object) packet).fire();
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }

        TCPShieldPacket p = new TCPShieldPacket(event);
        TCPShieldPlayer player = new TCPShieldPlayer(event);

        TCPShieldMod.INSTANCE.getPacketHandler().handleHandshake(p, player);

        if (event.isCancelled()) {
            ci.cancel();
        }

        if (event.isFailed()) {
            ci.cancel();
        }
    }
}
