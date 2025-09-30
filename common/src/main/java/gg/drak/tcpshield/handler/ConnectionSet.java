package gg.drak.tcpshield.handler;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter @Setter
public class ConnectionSet implements Comparable<ConnectionSet> {
    private String address;
    private int port;

    public ConnectionSet(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String fullAddress() {
        return address + ":" + port;
    }

    @Override
    public int compareTo(@NotNull ConnectionSet o) {
        return fullAddress().compareTo(o.fullAddress());
    }
}
