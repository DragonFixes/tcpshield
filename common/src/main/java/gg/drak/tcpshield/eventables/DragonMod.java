package gg.drak.tcpshield.eventables;

import gg.drak.thebase.objects.handling.derived.IModEventable;
import gg.drak.thebase.objects.handling.derived.ModEventable;
import gg.drak.thebase.storage.resources.flat.simple.SimpleConfiguration;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.File;

@Setter
public abstract class DragonMod extends ModEventable implements IModEventable, Comparable<DragonMod> {
    private String identifier;

    public DragonMod(String identifier, boolean load) {
        super(identifier, load);
    }

    public DragonMod(String identifier) {
        this(identifier, true);
    }

    public abstract String fallbackIdentifier();

    public String getIdentifier() {
        if (identifier == null || identifier.isEmpty()) {
            identifier = fallbackIdentifier();
        }
        return identifier;
    }

    @Override
    public int compareTo(@NotNull DragonMod o) {
        return this.getIdentifier().compareTo(o.getIdentifier());
    }

    public void load() {
        DragonManager.loadMod(this);
    }

    public void unload() {
        DragonManager.unloadMod(this);
    }

    public boolean isMainMod() {
        return false;
    }

    public File getModDirectory() {
        File modsDir = DragonManager.getModsDirectory();
        File modDir = new File(modsDir, getIdentifier());

        if (! modDir.exists()) {
            modDir.mkdirs();
        }

        return modDir;
    }

    @Override
    public File getDataFolder() {
        return getModDirectory();
    }

    public abstract void onServerStart(); // Do something when the server starts.

    public static class EmptyConfig extends SimpleConfiguration {
        public EmptyConfig(DragonMod mod, String fileName) {
            super(fileName.endsWith(".yml") ? fileName : fileName + ".yml", mod, false);
        }

        @Override
        public void init() {
            // do nothing
        }
    }
}
