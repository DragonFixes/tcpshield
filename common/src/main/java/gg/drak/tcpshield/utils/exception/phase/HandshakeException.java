package gg.drak.tcpshield.utils.exception.phase;

import gg.drak.tcpshield.utils.exception.TCPShieldException;

/**
 * An exception thrown during the handshake handling of TCPShield
 */
public class HandshakeException extends TCPShieldException {
	public HandshakeException(Throwable throwable) {
		super("An exception occured during the handshake process", throwable);
	}


	public HandshakeException(String message) {
		super(message);
	}


	public HandshakeException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public HandshakeException() {
		super();
	}
}