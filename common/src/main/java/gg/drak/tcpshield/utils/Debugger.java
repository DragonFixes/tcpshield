package gg.drak.tcpshield.utils;

import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.TCPShieldPlugin;
import org.slf4j.Logger;

/**
 * A util for debugging
 */
public abstract class Debugger {
	/**
	 * Creates a debugger instance according to
	 * the plugin's configuration options
	 * @return A debugger instance from plugin's configuration
	 */
	public static Debugger createDebugger() {
		if (TCPShieldMod.INSTANCE.getConfigProvider().doDebug())
			/*
			 * A non-empty debugger using the plugin's logger
			 */
			return new Debugger(TCPShieldMod.INSTANCE.getLogger()) {

				@Override
				public void info(String format, Object... formats) {
                    this.logger.info("Debug : {}", String.format(format, formats));
				}

				@Override
				public void warn(String format, Object... formats) {
                    this.logger.warn("Debug : {}", String.format(format, formats));
				}

				@Override
				public void error(String format, Object... formats) {
                    this.logger.error("Debug : {}", String.format(format, formats));
				}

				@Override
				public void exception(Exception exception) {
					exception.printStackTrace();
				}

			};
		else
			/*
			 * An empty debugger
			 */
			return new Debugger(null) {

				@Override
				public void info(String format, Object... formats) {

				}

				@Override
				public void warn(String format, Object... formats) {

				}

				@Override
				public void error(String format, Object... formats) {

				}

				@Override
				public void exception(Exception exception) {

				}

			};
	}


	protected final Logger logger;

	/**
	 * Non-accessable constructor for creating a debugger
	 * @param logger The plugin's logger
	 */
	private Debugger(Logger logger) {
		this.logger = logger;
	}


	/**
	 * Outputs debug information with log level "INFO"
	 * @param format The output string to be formatted
	 * @param formats The formarts for the string
	 */
	public abstract void info(String format, Object... formats);

	/**
	 * Outputs debug information with log level "WARNING"
	 * @param format The output string to be formatted
	 * @param formats The formarts for the string
	 */
	public abstract void warn(String format, Object... formats);

	/**
	 * Outputs debug information with log level "SEVERE"
	 * @param format The output string to be formatted
	 * @param formats The formarts for the string
	 */
	public abstract void error(String format, Object... formats);

	/**
	 * Outputs the exception through the debugger
	 * @param exception The exception to be outputted
	 */
	public abstract void exception(Exception exception);
}