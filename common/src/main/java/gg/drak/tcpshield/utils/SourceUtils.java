package gg.drak.tcpshield.utils;

import gg.drak.tcpshield.eventables.DragonManager;
import net.minecraft.command.CommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;
import java.util.UUID;

public class SourceUtils {
    public static CommandSource getConsoleSource() {
        return DragonManager.getServer().getCommandSource();
    }

    public static boolean isValidUuid(String possibleUUID) {
        try {
            UUID.fromString(possibleUUID);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Optional<ServerPlayerEntity> getSenderByName(String thing) {
        return Optional.ofNullable(DragonManager.getServer().getPlayerManager().getPlayer(thing));
    }

    public static Optional<ServerPlayerEntity> getSenderByUuid(String thing) {
        return Optional.ofNullable(DragonManager.getServer().getPlayerManager().getPlayer(UUID.fromString(thing)));
    }

    public static String getAbsolute(CommandSource user) {
        if (user == null) return "";
        if (! (user instanceof ServerPlayerEntity)) return "CONSOLE";

        return ((ServerPlayerEntity) user).getName().getString();
    }
}
