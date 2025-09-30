package gg.drak.tcpshield;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class IPManager {
    public static final ConcurrentSkipListSet<MaskedAddress> CACHED_IPS = new ConcurrentSkipListSet<>();
    public static final ConcurrentSkipListSet<String> ALLOWED_IP_RANGES = new ConcurrentSkipListSet<>();

    public static void init() {
        allowedInit();
        cacheInit();
    }

    public static void allowedInit() {
        ConcurrentSkipListSet<String> ipRanges = fetchIpRanges("https://tcpshield.com/v4/");
        ipRanges.addAll(fetchIpRanges("https://tcpshield.com/v4-cf/"));

        ALLOWED_IP_RANGES.addAll(ipRanges);

        TCPShieldMod.LOGGER.info("Successfully updated TCPShield IP ranges.");
    }

    public static void cacheInit() {
        ALLOWED_IP_RANGES.forEach(cidr -> {
            try {
                CACHED_IPS.add(new MaskedAddress(cidr));

                TCPShieldMod.LOGGER.info("Cached IP range: {}", cidr);
            } catch (Throwable e) {
                TCPShieldMod.LOGGER.error("Failed to cache IP range: {}", cidr);
                TCPShieldMod.LOGGER.error(e.getMessage());
                Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> TCPShieldMod.LOGGER.error(stackTraceElement.toString()));
            }
        });

        TCPShieldMod.LOGGER.info("Successfully cached TCPShield IP ranges.");
    }

    private static ConcurrentSkipListSet<String> fetchIpRanges(String urlString) {
        ConcurrentSkipListSet<String> ipRanges = new ConcurrentSkipListSet<>();
        try {
            URL url = new URI(urlString).toURL();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                ipRanges = reader.lines().collect(Collectors.toCollection(ConcurrentSkipListSet::new));
            }
        } catch (Exception e) {
            TCPShieldMod.LOGGER.error("Failed to fetch IP ranges from {}: {}", urlString, e.getMessage());
        }
        return ipRanges;
    }

    public static boolean isAllowed(MaskedAddress clientIp) {
        return CACHED_IPS.stream().anyMatch(clientIp::compare);
    }

    public static boolean isAllowed(String clientIp) {
        try {
            return isAllowed(new MaskedAddress(clientIp));
        } catch (Throwable e) {
            TCPShieldMod.LOGGER.error("Failed to check if {} is allowed: {}", clientIp, e.getMessage());
            TCPShieldMod.LOGGER.error(e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> TCPShieldMod.LOGGER.error(stackTraceElement.toString()));
            return false;
        }
    }
}
