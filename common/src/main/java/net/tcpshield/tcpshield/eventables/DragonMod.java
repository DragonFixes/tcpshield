package net.tcpshield.tcpshield.eventables;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tv.quaint.events.BaseEventListener;
import tv.quaint.objects.handling.derived.IModEventable;
import tv.quaint.objects.handling.derived.ModEventable;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.io.File;
import java.util.Optional;

@Getter @Setter
public abstract class DragonMod extends ModEventable implements IModEventable, Comparable<DragonMod> {
    @Getter @Setter
    private String identifier;

    public DragonMod(String identifier, boolean load) {
        super(identifier, load);
    }

    public DragonMod(String identifier) {
        this(identifier, true);
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
        File modsDir = DragonManager.getNodsDirectory();
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
