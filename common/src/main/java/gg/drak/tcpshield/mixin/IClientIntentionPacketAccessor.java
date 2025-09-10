package gg.drak.tcpshield.mixin;

import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandshakeC2SPacket.class)
public interface IClientIntentionPacketAccessor extends IClientIntensionPacket {
    @Accessor("address")
    @Final
    String tcpshield$getHostName();

    @Accessor("port")
    @Final
    int tcpshield$getPort();

    @Accessor("protocolVersion")
    @Final
    int tcpshield$getProtocolVersion();

    @Accessor("intendedState")
    @Final
    NetworkState tcpshield$getIntention();
}
