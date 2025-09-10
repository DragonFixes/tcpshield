package gg.drak.tcpshield.geyser;

import gg.drak.tcpshield.provider.ConfigProvider;

import java.util.UUID;

public class GeyserUtils {
	public static final String SESSION_SECRET = UUID.randomUUID().toString();
	public static boolean GEYSER_SUPPORT_ENABLED = false;

	public static void initGeyser(ConfigProvider config) {
		if (! (GEYSER_SUPPORT_ENABLED = useGeyser(config))) {
			return;
		}

		GeyserHandshakeHandler geyserHandler = new GeyserHandshakeHandler();
		geyserHandler.init();
	}

	private static boolean useGeyser(ConfigProvider config) {
		if (!config.isGeyser()) return false;

		try {
			Class.forName("org.geysermc.floodgate.api.InstanceHolder");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}