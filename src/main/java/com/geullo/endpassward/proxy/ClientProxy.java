package com.geullo.endpassward.proxy;




import com.geullo.endpassward.END.Background.Event;
import com.geullo.endpassward.util.ModResourcePack;
import com.geullo.endpassward.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.*;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraftforge.fml.common.network.NetworkRegistry;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

public class ClientProxy extends CommonProxy{
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("epw");

    @Override
    @SideOnly(Side.CLIENT)
    public void preInit(File configFile) throws IOException, URISyntaxException {
        File modFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/"),
                lockFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/"),
                spinFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/spin"),
                directionFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/direction/"),
                doorFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/door/"),
                quizFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/quizs/"),
                backgroundFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/background/");
        if (modFolder.exists()){
            modFolder.delete();
        }
        modFolder.mkdirs();
        lockFolder.mkdirs();
        spinFolder.mkdirs();
        directionFolder.mkdirs();
        doorFolder.mkdirs();
        quizFolder.mkdirs();
        backgroundFolder.mkdirs();
        String[] textureArr = {
                "lock/spin/arrow_0.png","lock/spin/arrow_1.png","lock/spin/number_0.png","lock/spin/number_1.png","lock/spin/number_2.png","lock/spin/number_3.png","lock/spin/number_4.png",
                "lock/spin/number_5.png","lock/spin/number_6.png","lock/spin/number_7.png","lock/spin/number_8.png","lock/spin/number_9.png","lock/spin/spin_lock_ring.png","lock/spin/spin_lock_body.png",
                "lock/direction/body.png","lock/direction/ring.png","lock/direction/circle.png","lock/door/number.png","lock/door/english.png","lock/door/body.png","lock/door/change.png",
                "lock/door/wrong.png","lock/door/correct.png","lock/door/common.png","background/Logo.png","background/team.png","background/setting.png","background/option.png"
        };
        File configFolder = new File(Minecraft.getMinecraft().mcDataDir, "/config/ESCAPEROOM/");
        configFolder.mkdirs();
        File file3 = new File(configFolder, "passward.properties");
        try {
            if (textureArr.length != 0) {
                for (int i = 0; i < textureArr.length; i++) {
                    if (new File(modFolder, textureArr[i]).exists()) {
                            Files.delete(new File(modFolder, textureArr[i]).toPath());
                    }
                    if (!new File(modFolder, textureArr[i]).exists()) {
                        Files.copy(
                                getClass().getResourceAsStream("/assets/endpassward/textures/" + textureArr[i]),
                                new File(modFolder, textureArr[i]).toPath()
                        );
                    }
                }
            }
            for (int i = 0; i < 125; i++) {
                String s = "0" + i;
                if (i < 10) {
                    s = "000" + i;
                }
                if (10 <= i && i < 100) {
                    s = "00" + i;
                }
                if (new File(modFolder, "background/" + s + ".png").exists()) {
                    Files.delete(new File(modFolder, "background/" + s + ".png").toPath());
                }
                Files.copy(
                        getClass().getResourceAsStream("/assets/endpassward/textures/background/" + s + ".png"),
                        new File(modFolder, "background/" + s + ".png").toPath());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (!file3.exists()) {
            Files.copy(getClass().getResourceAsStream("/assets/endpassward/configs/passward.properties"), new File(configFolder, "passward.properties").toPath());
        }

    }
    @Override
    public void init() {
        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(FMLClientHandler.class, FMLClientHandler.instance(), "resourcePackList");

        IResourcePack pack = new ModResourcePack(new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward"));
        defaultResourcePacks.add(pack);

        ((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).reloadResourcePack(pack);
        FMLCommonHandler.instance().bus().register(Event.getInstance());
    }

    @Override
    public void postInit() {
    }




    @Override
    public void registerItemRenderer(Item item,int metadataValue,String itemId){
        ModelLoader.setCustomModelResourceLocation(item,metadataValue,new ModelResourceLocation(Reference.MODID+":"+itemId,"inventory"));
    }
}
