package gg.drak.tcpshield.configs;

import gg.drak.thebase.storage.StorageUtils;
import lombok.Getter;
import lombok.Setter;
import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.provider.ConfigProvider;
import gg.drak.tcpshield.utils.exception.config.ConfigLoadException;
import gg.drak.tcpshield.utils.exception.config.ConfigReloadException;
import gg.drak.tcpshield.utils.exception.config.ConfigResetException;
import gg.drak.tcpshield.utils.exception.phase.ConfigException;

@Getter @Setter
public class MainConfig extends ConfigProvider {
    private TCPShieldMod plugin;

    public MainConfig(TCPShieldMod plugin) {
        super("config.yml", plugin, false);
    }

    @Override
    public void init() {
        reload();
    }

    @Override
    protected void checkNodes(String... nodes) {
        for (String node : nodes) {
            if (! getResource().contains(node))
                throw new ConfigException("The node \"" + node + "\" does not exist in the config.");
        }
    }

    @Override
    protected void reset() throws ConfigResetException {
        try {
            try {
                configFile.delete();
            } catch (Exception ignored) {
                // Just ignore since it either does not exist, or we can overwrite
            }

            StorageUtils.ensureFileFromSelf(this.getClass().getClassLoader(), getDataFolder(), getConfigFile(), "config.yml");
        } catch (Exception e) {
            throw new ConfigResetException(e);
        }
    }

    @Override
    protected void load() throws ConfigLoadException {
        try {
            checkNodes("only-allow-proxy-connections", "timestamp-validation", "debug-mode", "enable-geyser-support", "prefer-protocollib");

            this.onlyProxy = getResource().getBoolean("only-allow-proxy-connections");
            this.timestampValidationMode = getResource().getString("timestamp-validation");
            this.doDebug = getResource().getBoolean("debug-mode");
            this.geyser = getResource().getBoolean("enable-geyser-support");
        } catch (Exception e) {
            throw new ConfigLoadException(e);
        }
    }

    @Override
    public void reload() throws ConfigReloadException {
        try {
            if (! dataFolder.exists())
                dataFolder.mkdir();

            if (!configFile.exists())
                reset();

            try {
                load();
            } catch (ConfigLoadException exception) {
                plugin.getLogger().warn("Config loading failed, resetting to default config. (This can be ignored if you just switched builds of TCPShield)");
                reset();
                reload(); // Redo cycle, possible StackOverFlow, but realistically only happens if reset fails
            }
        } catch (Exception e) {
            throw new ConfigReloadException(e);
        }
    }
}
