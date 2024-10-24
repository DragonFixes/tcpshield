package net.tcpshield.tcpshield.utils.exception.parse;

import net.tcpshield.tcpshield.utils.exception.phase.HandshakeException;

/**
 * An exception thrown when a handshake packet failed signature validation
 */
public class SignatureValidationException extends HandshakeException {

	public SignatureValidationException() {
		super();
	}

}