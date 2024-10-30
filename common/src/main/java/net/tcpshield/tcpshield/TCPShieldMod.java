package net.tcpshield.tcpshield;

import net.tcpshield.tcpshield.configs.MainConfig;
import net.tcpshield.tcpshield.eventables.DragonMod;
import net.tcpshield.tcpshield.geyser.GeyserUtils;
import net.tcpshield.tcpshield.handler.TCPShieldPacketHandler;
import net.tcpshield.tcpshield.text.HexInit;
import net.tcpshield.tcpshield.utils.Debugger;
import net.tcpshield.tcpshield.utils.LoggingUtils;
import net.tcpshield.tcpshield.utils.exception.phase.InitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPShieldMod extends DragonMod implements TCPShieldPlugin {
    public static TCPShieldMod INSTANCE;

    public static final Logger LOGGER = LoggerFactory.getLogger("TCPShield");
    public static final String MOD_ID = "tcpshield";

    private static MainConfig CONFIG;

    private static TCPShieldPacketHandler PACKET_HANDLER;
    private static Debugger DEBUGGER;

    public TCPShieldMod() {
        super(MOD_ID, true);

        INSTANCE = this;
    }

    @Override
    public boolean isMainMod() {
        return true;
    }

    @Override
    public void onServerStart() {
        INSTANCE = this;

        try {
            CONFIG = new MainConfig(this);
            DEBUGGER = Debugger.createDebugger(this);
            PACKET_HANDLER = new TCPShieldPacketHandler(this);

            HexInit.init();

//            bukkitImpl.load(); // Not needed for Fabric

            GeyserUtils.initGeyser(this, CONFIG);

            initialization();
        } catch (Exception e) {
            LoggingUtils.logSystem("Server start failed for TCPShield Mod.");
            LoggingUtils.logSystem(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                LoggingUtils.logSystem(element.toString());
            }
        }
    }

    @Override
    public MainConfig getConfigProvider() {
        return CONFIG;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public TCPShieldPacketHandler getPacketHandler() {
        return PACKET_HANDLER;
    }

    @Override
    public Debugger getDebugger() {
        return DEBUGGER;
    }
}