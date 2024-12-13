package com.geullo.endpassward;

import com.geullo.endpassward.END.SoundEffect;
import com.geullo.endpassward.proxy.CommonProxy;
import com.geullo.endpassward.util.Reference;


import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;

@Mod(modid = Reference.MODID, name = Reference.NAME)
public class Main {
    @Instance
    public static Main instacne;

    @SidedProxy(clientSide = Reference.CLIENTPROXY,serverSide = Reference.COMMONPROXY)
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException, URISyntaxException {
        logger = event.getModLog();
        proxy.preInit(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPreInitializationEvent event) {
        proxy.postInit();
    }


    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItem(RegistryEvent.Register<SoundEvent> event) {
            SoundEffect.registerSounds(event.getRegistry());
        }

    }
}
