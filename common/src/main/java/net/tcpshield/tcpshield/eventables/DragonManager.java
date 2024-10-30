package net.tcpshield.tcpshield.eventables;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.tcpshield.tcpshield.TCPShieldMod;
import net.tcpshield.tcpshield.configs.LoggingConfig;
import net.tcpshield.tcpshield.utils.LoggingUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class DragonManager {
    @Getter @Setter
    private static MinecraftServer server;

    @Getter @Setter
    private static ConcurrentSkipListSet<DragonMod> loadedMods = new ConcurrentSkipListSet<>();

    public static void loadMod(DragonMod mod) {
        loadedMods.add(mod);
    }

    public static void unloadMod(String identifier) {
        loadedMods.forEach(mod -> {
            if (mod.getIdentifier().equals(identifier)) {
                loadedMods.remove(mod);
            }
        });
    }

    public static void unloadMod(DragonMod mod) {
        unloadMod(mod.getIdentifier());
    }

    public static Optional<DragonMod> getMod(String identifier) {
        for (DragonMod mod : loadedMods) {
            if (mod.getIdentifier().equals(identifier)) {
                return Optional.of(mod);
            }
        }

        return Optional.empty();
    }

    public static boolean isModLoaded(String identifier) {
        return getMod(identifier).isPresent();
    }

    public static File getServerDirectory() {
        File dir = System.getProperty("user.dir") != null ? new File(System.getProperty("user.dir")) : new File(".");

        if (! dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    public static File getNodsDirectory() {
        File serverDir = getServerDirectory();
        File modsDir = new File(serverDir, "mods");

        if (! modsDir.exists()) {
            modsDir.mkdirs();
        }

        return modsDir;
    }

    public static void doServerStartMods() {
        loadedMods.forEach(DragonMod::onServerStart);
    }

    // On Base init. This is called before the server starts.
    public static void init() {
        try {
            TCPShieldMod mod = new TCPShieldMod();
            loadMod(mod);
        } catch (Exception e) {
            LoggingUtils.logSystem("Failed to initialize TCPShield Mod.");
            LoggingUtils.logSystem(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                LoggingUtils.logSystem(element.toString());
            }
        }
    }

    public static void onServerStart(MinecraftServer server) {
        try {
            setServer(server); // has to be set before anything else

            getLoadedMods().forEach(DragonMod::onServerStart);

            LoggingConfig.load(); // Has to be loaded after MainConfig
            LoggingConfig.save();
        } catch (Exception e) {
            LoggingUtils.logSystem("Failed to start TCPShield Mod.");
            LoggingUtils.logSystem(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                LoggingUtils.logSystem(element.toString());
            }
        }
    }

    public static List<ServerPlayer> getOnlinePlayers() {
        return server.getPlayerList().getPlayers();
    }

    public static ConcurrentSkipListMap<String, ServerPlayer> getOnlinePlayersByUUID() {
        ConcurrentSkipListMap<String, ServerPlayer> players = new ConcurrentSkipListMap<>();

        getOnlinePlayers().forEach(player -> {
            players.put(player.getUUID().toString(), player);
        });

        return players;
    }
}
