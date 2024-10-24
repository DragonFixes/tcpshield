package net.tcpshield.tcpshield.utils.exception.parse;

import net.tcpshield.tcpshield.utils.exception.phase.HandshakeException;

/**
 * An exception thrown when a handshake packet has invalid payload data
 */
public class InvalidPayloadException extends HandshakeException {

	public InvalidPayloadException(Throwable throwable) {
		super(throwable);
	}


	public InvalidPayloadException(String message) {
		super(message);
	}


	public InvalidPayloadException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public InvalidPayloadException() {
		super();
	}

}