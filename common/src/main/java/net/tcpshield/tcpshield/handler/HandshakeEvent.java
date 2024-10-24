package net.tcpshield.tcpshield.handler;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.ConnectionProtocol;
import net.tcpshield.tcpshield.mixin.IClientIntentionPacketAccessor;
import tv.quaint.events.components.BaseEvent;

@Getter @Setter
public class HandshakeEvent extends BaseEvent {
    public static final String DEFAULT_FAIL_REASON = "Connection failed. Please try again or contact an administrator.";

    private IClientIntentionPacketAccessor packet;
    private String failReason;
    private boolean failed;

    public HandshakeEvent(IClientIntentionPacketAccessor packet) {
        super();

        this.packet = packet;
        this.failReason = DEFAULT_FAIL_REASON;
        this.failed = false;
    }

    public String getHostname() {
        return packet.tcpshield$getHostName();
    }

    public int getPort() {
        return packet.tcpshield$getPort();
    }

    public int getProtocolVersion() {
        return packet.tcpshield$getProtocolVersion();
    }

    public ConnectionProtocol getProtocol() {
        return packet.tcpshield$getIntention();
    }

    public void setServerHostname(String hostname) {
        ConnectionHandler.setMaskedAs(hostname, packet);
    }
}
