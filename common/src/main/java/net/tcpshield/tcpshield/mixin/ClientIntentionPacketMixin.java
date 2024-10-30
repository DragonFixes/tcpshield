package net.tcpshield.tcpshield.mixin;

import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.tcpshield.tcpshield.handler.ConnectionHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientIntentionPacket.class)
public class ClientIntentionPacketMixin {
    @Inject(method = "hostName", at = @At("HEAD"), cancellable = true)
    public void onGetHostName(CallbackInfoReturnable<String> cir) {
        String masked = ConnectionHandler.getMaskedAs((IClientIntentionPacketAccessor) this);
        if (masked != null) {
            cir.setReturnValue(masked);
        }
    }
}
