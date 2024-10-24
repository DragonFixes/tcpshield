package net.tcpshield.tcpshield.handler;

import lombok.Getter;
import lombok.Setter;
import net.tcpshield.tcpshield.mixin.IClientIntentionPacketAccessor;
import net.tcpshield.tcpshield.utils.LoggingUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConnectionHandler {
    @Getter @Setter
    private static ConcurrentSkipListMap<ConnectionSet, String> maskedConnections = new ConcurrentSkipListMap<>(); // ConnectionSet -> masked IP

    public static void addMaskedConnection(ConnectionSet connectionSet, String masked) {
        maskedConnections.put(connectionSet, masked);
    }

    public static void removeMaskedConnection(String fullAddress) {
        maskedConnections.forEach((connection, masked) -> {
            if (connection.fullAddress().equalsIgnoreCase(fullAddress)) {
                maskedConnections.remove(connection);
            }
        });
    }

    public static boolean isMasked(String fullAddress) {
        return getMaskedEntry(fullAddress) != null;
    }

    public static Map.Entry<ConnectionSet, String> getMaskedEntry(String fullAddress) {
        for (Map.Entry<ConnectionSet, String> entry : maskedConnections.entrySet()) {
            if (entry.getKey().fullAddress().equalsIgnoreCase(fullAddress)) {
                return entry;
            }
        }

        return null;
    }

    public static String getMasked(String fullAddress) {
        Map.Entry<ConnectionSet, String> entry = getMaskedEntry(fullAddress);
        return entry != null ? entry.getValue() : null;
    }

    public static String getMasked(ConnectionSet connectionSet) {
        return getMasked(connectionSet.fullAddress());
    }

    public static ConnectionSet getConnectionSet(String fullAddress) {
        Map.Entry<ConnectionSet, String> entry = getMaskedEntry(fullAddress);
        return entry != null ? entry.getKey() : null;
    }

    public static ConnectionSet createConnectionSet(String fullAddress) {
        try {
            String[] split = fullAddress.split(":");
            String ip = split[0];
            int port = Integer.parseInt(split[1]);

            return new ConnectionSet(ip, port);
        } catch (Exception e) {
            LoggingUtils.logError("Failed to create a ConnectionSet for " + fullAddress + ": " + e.getMessage());
            LoggingUtils.logDebug(e);
            return null;
        }
    }

    public static void setMaskedAs(String masked, IClientIntentionPacketAccessor packet) {
        ConnectionSet maskedSet = new ConnectionSet(packet.tcpshield$getHostName(), packet.tcpshield$getPort());
        addMaskedConnection(maskedSet, masked);
    }

    public static String getMaskedAs(IClientIntentionPacketAccessor packet) {
        ConnectionSet maskedSet = new ConnectionSet(packet.tcpshield$getHostName(), packet.tcpshield$getPort());
        return getMasked(maskedSet);
    }
}
