package gg.drak.tcpshield.mixin;

import net.minecraft.network.NetworkState;

public interface IClientIntensionPacket {
    String tcpshield$getHostName();

    int tcpshield$getPort();

    int tcpshield$getProtocolVersion();

    NetworkState tcpshield$getIntention();
}
