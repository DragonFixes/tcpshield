package net.tcpshield.tcpshield.utils;

import net.minecraft.commands.CommandSource;
import net.minecraft.server.level.ServerPlayer;
import net.tcpshield.tcpshield.eventables.DragonManager;

import java.util.Optional;
import java.util.UUID;

public class SourceUtils {
    public static CommandSource getConsoleSource() {
        return DragonManager.getServer();
    }

    public static boolean isValidUuid(String possibleUUID) {
        try {
            UUID.fromString(possibleUUID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Optional<CommandSource> getSenderByName(String thing) {
        return Optional.ofNullable(DragonManager.getServer().getPlayerList().getPlayerByName(thing));
    }

    public static Optional<CommandSource> getSenderByUuid(String thing) {
        return Optional.ofNullable(DragonManager.getServer().getPlayerList().getPlayer(UUID.fromString(thing)));
    }

    public static String getAbsolute(CommandSource user) {
        if (user == null) return "";
        if (! (user instanceof ServerPlayer)) return "CONSOLE";

        return ((ServerPlayer) user).getName().getString();
    }
}
