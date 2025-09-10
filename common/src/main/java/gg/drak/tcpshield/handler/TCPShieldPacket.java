package gg.drak.tcpshield.handler;

import gg.drak.tcpshield.provider.PacketProvider;
import gg.drak.tcpshield.utils.exception.manipulate.PacketManipulationException;

/**
 * A packet wrapper for PaperSpigot
 */
public record TCPShieldPacket(HandshakeEvent event) implements PacketProvider {
    @Override
    public String getPayloadString() {
        return event.getHostname();
    }

    @Override
    public void setPacketHostname(String hostname) throws PacketManipulationException {
        try {
            event.setCancelled(false);
            event.setServerHostname(hostname);
        } catch (Exception e) {
            throw new PacketManipulationException(e);
        }
    }
}