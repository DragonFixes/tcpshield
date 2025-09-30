package gg.drak.tcpshield.mixin;

import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import gg.drak.tcpshield.handler.ConnectionHandler;

@Mixin(HandshakeC2SPacket.class)
public class HandshakeC2SPacketMixin {
    @Inject(method = "getAddress", at = @At("HEAD"), cancellable = true)
    public void onGetHostName(CallbackInfoReturnable<String> cir) {
        String masked = ConnectionHandler.getMaskedAs((IClientIntentionPacketAccessor) this);
        if (masked != null) {
            cir.setReturnValue(masked);
        }
    }
}
