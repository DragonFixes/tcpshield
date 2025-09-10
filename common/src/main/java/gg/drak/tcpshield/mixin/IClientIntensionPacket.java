package gg.drak.tcpshield.mixin;

import net.minecraft.network.ConnectionProtocol;

public interface IClientIntensionPacket {
    String tcpshield$getHostName();

    int tcpshield$getPort();

    int tcpshield$getProtocolVersion();

    ConnectionProtocol tcpshield$getIntention();
}
