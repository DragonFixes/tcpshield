package gg.drak.tcpshield.provider;

import gg.drak.thebase.objects.handling.derived.ModEventable;
import gg.drak.thebase.storage.resources.flat.simple.SimpleConfiguration;
import gg.drak.tcpshield.utils.exception.config.ConfigLoadException;
import gg.drak.tcpshield.utils.exception.config.ConfigReloadException;
import gg.drak.tcpshield.utils.exception.config.ConfigResetException;
import gg.drak.tcpshield.utils.exception.phase.ConfigException;
import lombok.Getter;

import java.io.File;

/**
 * An abstract provider for TCPShield's options.
 */
@Getter
public abstract class ConfigProvider extends SimpleConfiguration {
	/*
	 * Configuration options
	 */
    protected boolean onlyProxy = true;
    protected String timestampValidationMode = "htpdate";
	protected boolean doDebug = true; // Fail-safe default set to true
    protected boolean geyser = false;

    protected File dataFolder;
    protected File configFile;

	public ConfigProvider(String fileName, ModEventable eventable, boolean selfContained) {
		super(fileName, eventable, selfContained);
	}

    public boolean doDebug() {
		return this.doDebug;
	}

    /*
	 * Plugin Constants
	 */

	@Getter
    protected final long maxTimestampDifference = 3; // In Unix Timesteps (Seconds)

    /*
	 * Required methods
	 */

	/**
	 * Trys to load the options from the config, if failed, throws ConfigLoadException
	 * @throws ConfigLoadException Thrown if loading fails, reset should be called if thrown
	 */
	protected abstract void load() throws ConfigLoadException;

	/**
	 * Trys to reload the config, if failed, throws ConfigReloadException
	 * @throws ConfigReloadException Thrown if reloading fails
	 */
	public abstract void reload() throws ConfigReloadException;
}