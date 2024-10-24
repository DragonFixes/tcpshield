package net.tcpshield.tcpshield.configs;

import lombok.Getter;
import lombok.Setter;
import net.tcpshield.tcpshield.TCPShieldMod;
import tv.quaint.thebase.lib.leonhard.storage.sections.FlatFileSection;

@Getter @Setter
public class LoggingConfig {
    public static LoggingConfig INSTANCE;

    private boolean info;
    private boolean debug;
    private boolean error;
    private boolean warn;

    private String infoPre;
    private String debugPre;
    private String errorPre;
    private String warnPre;

    public LoggingConfig() {
        this(false);
    }

    public LoggingConfig(boolean load) {
        if (load) INSTANCE = this;

        this.info = true;
        this.debug = true;
        this.error = true;
        this.warn = true;

        this.infoPre = "&c[&3INFO&c] &f";
        this.debugPre = "&c[&3DEBUG&c] &7";
        this.errorPre = "&c[&4&lERROR&c] &c";
        this.warnPre = "&c[&6&lWARN&c] &e";
    }

    public static void load() {
        LoggingConfig config = get();

        MainConfig mainConfig = TCPShieldMod.INSTANCE.getConfigProvider();
        FlatFileSection section = mainConfig.getResource().getSection("logging");

        config.setInfo(section.getOrSetDefault("info.enabled", config.isInfo()));
        config.setDebug(section.getOrSetDefault("debug.enabled", config.isDebug()));
        config.setError(section.getOrSetDefault("error.enabled", config.isError()));
        config.setWarn(section.getOrSetDefault("warn.enabled", config.isWarn()));

        config.setInfoPre(section.getOrSetDefault("info.prefix", config.getInfoPre()));
        config.setDebugPre(section.getOrSetDefault("debug.prefix", config.getDebugPre()));
        config.setErrorPre(section.getOrSetDefault("error.prefix", config.getErrorPre()));
        config.setWarnPre(section.getOrSetDefault("warn.prefix", config.getWarnPre()));

        INSTANCE = config;
    }

    public static void save() {
        MainConfig mainConfig = TCPShieldMod.INSTANCE.getConfigProvider();
        FlatFileSection section = mainConfig.getResource().getSection("logging");

        section.set("info.enabled", isInfoEnabled());
        section.set("debug.enabled", isDebugEnabled());
        section.set("error.enabled", isErrorEnabled());
        section.set("warn.enabled", isWarnEnabled());

        section.set("info.prefix", getInfoPrefix());
        section.set("debug.prefix", getDebugPrefix());
        section.set("error.prefix", getErrorPrefix());
        section.set("warn.prefix", getWarnPrefix());
    }

    public static LoggingConfig get() {
        if (INSTANCE == null) {
            INSTANCE = new LoggingConfig();
        }

        return INSTANCE;
    }

    public static boolean isInfoEnabled() {
        return get().isInfo();
    }

    public static boolean isDebugEnabled() {
        return get().isDebug();
    }

    public static boolean isErrorEnabled() {
        return get().isError();
    }

    public static boolean isWarnEnabled() {
        return get().isWarn();
    }

    public static String getInfoPrefix() {
        return get().getInfoPre();
    }

    public static String getDebugPrefix() {
        return get().getDebugPre();
    }

    public static String getErrorPrefix() {
        return get().getErrorPre();
    }

    public static String getWarnPrefix() {
        return get().getWarnPre();
    }
}
