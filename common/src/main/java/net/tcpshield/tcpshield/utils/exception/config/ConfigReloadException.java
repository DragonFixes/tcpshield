package net.tcpshield.tcpshield.utils.exception.config;

import net.tcpshield.tcpshield.utils.exception.phase.ConfigException;

/**
 * An exception thrown during the reloading phase of the config process
 */
public class ConfigReloadException extends ConfigException {

	public ConfigReloadException(Throwable throwable) {
		super(throwable);
	}


	public ConfigReloadException(String message) {
		super(message);
	}


	public ConfigReloadException(String message, Throwable throwable) {
		super(message, throwable);
	}


	public ConfigReloadException() {
		super();
	}

}