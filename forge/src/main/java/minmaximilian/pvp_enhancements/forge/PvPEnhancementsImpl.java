package minmaximilian.pvp_enhancements.forge;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.config.PvPEnhancementsConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PvPEnhancements.MOD_ID)
public class PvPEnhancementsImpl {
    public PvPEnhancementsImpl() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
            .getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        ModLoadingContext mlContext = ModLoadingContext.get();

        PvPEnhancementsConfig.registerConfigs(mlContext::registerConfig);

        modEventBus.addListener(this::onLoadConfig);
        modEventBus.addListener(this::onReloadConfig);

        PvPEnhancementsCommonEventsImpl.register(forgeEventBus);

        PvPEnhancements.REGISTRATE.registerEventListeners(modEventBus);
        PvPEnhancements.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> PvPEnhancementsClientImpl.prepareClient(modEventBus, forgeEventBus));
    }

    private void onLoadConfig(ModConfigEvent.Loading event) {
        PvPEnhancementsConfig.onLoad(event.getConfig());
    }

    private void onReloadConfig(ModConfigEvent.Reloading event) {
        PvPEnhancementsConfig.onReload(event.getConfig());
    }
}
