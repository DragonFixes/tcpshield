package net.tcpshield.tcpshield.text;

import net.tcpshield.tcpshield.utils.LoggingUtils;

public class HexInit {
    public static void init() {
        TextManager.registerHexPolicy(new HexPolicy("{#", "}"));
        TextManager.registerHexPolicy(new HexPolicy("<#", ">"));
        TextManager.registerHexPolicy(new HexPolicy("&#", ""));

        LoggingUtils.logInfo("Registered " + TextManager.getHexPolicies().size() + " hex policies.");
    }
}
