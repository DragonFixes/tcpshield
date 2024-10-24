package net.tcpshield.tcpshield.utils.exception.manipulate;

import net.tcpshield.tcpshield.utils.exception.phase.HandshakeException;

/**
 * An exception thrown during packet manipulation
 */
public class PacketManipulationException extends HandshakeException {

	public PacketManipulationException(Throwable throwable) {
		super(throwable);
	}


	public PacketManipulationException(String message) {
		super(message);
	}


	public PacketManipulationException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public PacketManipulationException() {
		super();
	}

}