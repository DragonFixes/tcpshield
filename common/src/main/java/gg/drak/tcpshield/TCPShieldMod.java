package gg.drak.tcpshield;

import gg.drak.tcpshield.configs.MainConfig;
import gg.drak.tcpshield.eventables.DragonMod;
import gg.drak.tcpshield.geyser.GeyserUtils;
import gg.drak.tcpshield.handler.TCPShieldPacketHandler;
import gg.drak.tcpshield.text.HexInit;
import gg.drak.tcpshield.utils.Debugger;
import gg.drak.tcpshield.utils.exception.phase.InitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPShieldMod extends DragonMod implements TCPShieldPlugin {
    public static TCPShieldMod INSTANCE;

    public static final Logger LOGGER = LoggerFactory.getLogger("TCPShield");
    public static final String MOD_ID = "assets/tcpshield";

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
        try {
            CONFIG = new MainConfig();
            DEBUGGER = Debugger.createDebugger();
            PACKET_HANDLER = new TCPShieldPacketHandler();

            HexInit.init();

//            bukkitImpl.load(); // Not needed for Fabric

            GeyserUtils.initGeyser(CONFIG);

            initialization();
        } catch (Exception e) {
            throw new InitializationException(e);
        }
    }

    public void set() {
        INSTANCE = this;
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

    @Override
    public String fallbackIdentifier() {
        return "tcpshield";
    }
}