package gg.drak.tcpshield.configs;

import lombok.Getter;
import lombok.Setter;
import gg.drak.tcpshield.TCPShieldMod;
import gg.drak.tcpshield.provider.ConfigProvider;
import gg.drak.tcpshield.utils.exception.config.ConfigLoadException;
import gg.drak.tcpshield.utils.exception.config.ConfigReloadException;

@Getter @Setter
public class MainConfig extends ConfigProvider {
    public MainConfig() {
        super("config.yml", TCPShieldMod.INSTANCE, true);
    }

    @Override
    public void init() {
        reload();
    }

    @Override
    protected void load() throws ConfigLoadException {
        try {
            this.onlyProxy = getOrSetDefault("only-allow-proxy-connections", true);
            this.timestampValidationMode = getOrSetDefault("timestamp-validation", "htpdate");
            this.doDebug = getOrSetDefault("debug-mode", false);
            this.geyser = getOrSetDefault("enable-geyser-support", false);
        } catch (Exception e) {
            throw new ConfigLoadException(e);
        }
    }

    @Override
    public void reload() throws ConfigReloadException {
        try {
            reloadResource();
            load();
        } catch (Exception e) {
            throw new ConfigReloadException(e);
        }
    }
}
