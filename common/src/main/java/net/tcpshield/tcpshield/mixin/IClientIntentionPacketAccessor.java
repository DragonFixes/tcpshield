package net.tcpshield.tcpshield.mixin;

import net.minecraft.network.protocol.handshake.ClientIntent;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientIntentionPacket.class)
public interface IClientIntentionPacketAccessor {
    @Accessor("hostName")
    @Final
    String tcpshield$getHostName();

    @Accessor("port")
    @Final
    int tcpshield$getPort();

    @Accessor("protocolVersion")
    @Final
    int tcpshield$getProtocolVersion();

    @Accessor("intention")
    @Final
    ClientIntent tcpshield$getIntention();
}
