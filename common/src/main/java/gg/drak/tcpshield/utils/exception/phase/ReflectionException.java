package gg.drak.tcpshield.utils.exception.phase;

import net.tcpshield.tcpshield.utils.exception.TCPShieldException;

/**
 * An exception thrown during reflection operations for TCPShield
 */
public class ReflectionException extends TCPShieldException {

	public ReflectionException(Throwable throwable) {
		super("An exception occured during the reflection process", throwable);
	}


	public ReflectionException(String message) {
		super(message);
	}


	public ReflectionException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public ReflectionException() {
		super();
	}

}