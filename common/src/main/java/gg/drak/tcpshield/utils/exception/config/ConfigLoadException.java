package gg.drak.tcpshield.utils.exception.config;

import net.tcpshield.tcpshield.utils.exception.phase.ConfigException;

/**
 * An exception thrown during the loading phase of the config process
 */
public class ConfigLoadException extends ConfigException {

	public ConfigLoadException(Throwable throwable) {
		super(throwable);
	}


	public ConfigLoadException(String message) {
		super(message);
	}


	public ConfigLoadException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public ConfigLoadException() {
		super();
	}

}