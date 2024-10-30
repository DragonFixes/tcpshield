package net.tcpshield.tcpshield.configs;

import lombok.Getter;
import lombok.Setter;
import net.tcpshield.tcpshield.TCPShieldMod;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.io.File;

@Getter @Setter
public class MainConfig extends SimpleConfiguration {
    private TCPShieldMod plugin;

    public MainConfig(TCPShieldMod plugin) {
        super("config.yml", plugin, true);
    }

    @Override
    public void init() {
        isOnlyProxy();
        getTimestampValidationMode();
        doDebug();
        isGeyser();
    }

    public boolean isOnlyProxy() {
        reloadResource();

        return getOrSetDefault("only-allow-proxy-connections", true);
    }

    public String getTimestampValidationMode() {
        reloadResource();

        return getOrSetDefault("timestamp-validation", "htpdate");
    }

    public boolean doDebug() {
        reloadResource();

        return getOrSetDefault("debug-mode", false);
    }

    public boolean isGeyser() {
        reloadResource();

        return getOrSetDefault("enable-geyser-support", false);
    }

    public int getMaxTimestampDifference() {
        reloadResource();

        return getOrSetDefault("max-timestamp-difference", 3); // 3 seconds
    }

    public File getDataFolder() {
        return getSelfFile().getParentFile();
    }
}
