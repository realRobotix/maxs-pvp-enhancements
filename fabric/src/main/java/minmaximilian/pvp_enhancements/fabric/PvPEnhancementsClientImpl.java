package minmaximilian.pvp_enhancements.fabric;

import minmaximilian.pvp_enhancements.PvPEnhancementsClient;
import net.fabricmc.api.ClientModInitializer;

public class PvPEnhancementsClientImpl implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        PvPEnhancementsClient.init();
    }
}
