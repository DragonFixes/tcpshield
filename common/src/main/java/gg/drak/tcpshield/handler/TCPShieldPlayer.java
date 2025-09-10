package gg.drak.tcpshield.handler;

import gg.drak.tcpshield.provider.PlayerProvider;
import gg.drak.tcpshield.utils.exception.manipulate.PlayerManipulationException;

import java.net.InetSocketAddress;
import java.util.UUID;

public record TCPShieldPlayer(HandshakeEvent event) implements PlayerProvider {
    /**
     * Trys to grab the UUID of the handshake
     *
     * @return If found, the corrosponding uuid, if not, unknown
     */
    @Override
    public String getUUID() {
//        UUID uuid = event.getUniqueId();
        UUID uuid = null;
        if (uuid == null)
            return "unknown";

        return uuid.toString();
    }

    /**
     * Unsupported with Paper handshakes
     *
     * @return unknown
     */
    @Override
    public String getName() {
        return "unknown";
    }

    @Override
    public String getIP() {
        return event.getHostname();
    }

    @Override
    public void setIP(InetSocketAddress ip) throws PlayerManipulationException {
        try {
            event.setServerHostname(ip.getAddress().getHostAddress());
        } catch (Exception e) {
            throw new PlayerManipulationException(e);
        }
    }

    @Override
    public void disconnect() {
        event.setCancelled(false); // Caused issues with newer versions of Paper (Thanks https://github.com/realDragonium)
        event.setFailReason("Connection failed. Please try again or contact an administrator.");
        event.setFailed(true);
    }
}
