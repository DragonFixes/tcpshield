package net.tcpshield.tcpshield.utils.exception.phase;

import net.tcpshield.tcpshield.utils.exception.TCPShieldException;

public class InvalidSecretException extends TCPShieldException {

	public InvalidSecretException(Throwable throwable) {
		super("An invalid secret was provided during the geyser handshake process", throwable);
	}


	public InvalidSecretException(String message) {
		super(message);
	}


	public InvalidSecretException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public InvalidSecretException() {
		super();
	}

}