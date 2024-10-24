package net.tcpshield.tcpshield;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MaskedAddress implements Comparable<MaskedAddress> {
    private final String address;
    private final String[] parts;
    private final byte[] bytes;
    private final long ipLong;
    private final Optional<Long> mask;

    public MaskedAddress(String address) throws Throwable {
        this.address = address;
        this.parts = address.split("/");
        this.bytes = InetAddress.getByName(parts[0]).getAddress();
        this.ipLong = ipToLong(bytes);
        this.mask = createMask(parts);
    }

    public Optional<Long> createMask(String[] parts) {
        if (parts.length <= 1) {
            return Optional.empty();
        }
        return Optional.of(createMask(Integer.parseInt(parts[1])));
    }

    public String getAddress() {
        return address;
    }

    public String[] getParts() {
        return parts;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public long getIpLong() {
        return ipLong;
    }

    public Optional<Long> getMask() {
        return mask;
    }

    public boolean compare(String otherIp) throws Throwable {
        return compare(new MaskedAddress(otherIp));
    }

    public boolean compare(MaskedAddress other) {
        return compare(this, other);
    }

    @Override
    public int compareTo(@NotNull MaskedAddress o) {
        AtomicInteger r = new AtomicInteger(1000);

        if (getMask().isPresent()) {
            if (o.getMask().isPresent()) {
                r.set(getMask().orElse(0L).compareTo(o.getMask().orElse(0L)));
            } else {
                r.set(1);
            }
        } else {
            if (o.getMask().isPresent()) {
                r.set(-1);
            } else {
                r.set(Long.compare(getIpLong(), o.getIpLong()));
            }
        }

        return r.get();
    }

    private static long ipToLong(byte[] ip) {
        long result = 0;
        for (byte b : ip) {
            result <<= 8;
            result |= b & 0xFF;
        }
        return result;
    }

    private static long createMask(int prefixLength) {
        return (0xFFFFFFFFL << (32 - prefixLength)) & 0xFFFFFFFFL;
    }

    private static boolean compare(MaskedAddress cidr, String clientIp) throws Throwable {
        return compare(cidr, new MaskedAddress(clientIp));
    }

    private static boolean compare(MaskedAddress one, MaskedAddress two) {
        try {
            if (one.getBytes().length != two.getBytes().length) return false; // IPv4 vs IPv6 mismatch

            if (one.getMask().isPresent() && two.getMask().isPresent()) {
                return (one.getIpLong() & one.getMask().orElse(0L)) == (two.getIpLong() & two.getMask().orElse(0L));

                // Commented out because we don't need to check for these cases (I think)
//            } else if (one.getMask().isPresent()) {
//                return (one.getIpLong() & one.getMask().orElse(0L)) == two.getIpLong();
//            } else if (two.getMask().isPresent()) {
//                return one.getIpLong() == (two.getIpLong() & two.getMask().orElse(0L));
            } else {
                return one.getIpLong() == two.getIpLong();
            }
        } catch (Throwable e) {
            TCPShieldMod.LOGGER.error("Failed to check if {} is in range {}: {}", two, one.getAddress(), e.getMessage());
            TCPShieldMod.LOGGER.error(e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> TCPShieldMod.LOGGER.error(stackTraceElement.toString()));
            return false;
        }
    }
}
