package net.tcpshield.tcpshield.handler;

import net.tcpshield.tcpshield.provider.PacketProvider;
import net.tcpshield.tcpshield.utils.exception.manipulate.PacketManipulationException;

/**
 * A packet wrapper for PaperSpigot
 */
public class TCPShieldPacket implements PacketProvider {

	private final HandshakeEvent event;

	public TCPShieldPacket(HandshakeEvent event) {
		this.event = event;
	}

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