package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.END.Util.BackGround;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class Event {
    public Minecraft Gc4 = Minecraft.getMinecraft();
    private static Event GjhAw0h41;
    private final Options options = new Options(new GuiMainMenu(),Minecraft.getMinecraft().gameSettings);
    public static Event getInstance() {
        if (GjhAw0h41 == null) {
            GjhAw0h41 = new Event();
        }
        return GjhAw0h41;
    }

    public Event() {
    }

    @SubscribeEvent
    public void onMainMenuOpen(GuiOpenEvent event) {
        System.out.println(event.getGui());
        if (event.getGui() instanceof GuiMultiplayer) {
            event.setGui(new BackGround());
        }
        if (event.getGui() instanceof GuiMainMenu) {
            event.setGui(new BackGround());
        }else if (event.getGui() instanceof ScreenChatOptions){
            if (Gc4.player==null) {event.setGui(new ChatSetting(options,Minecraft.getMinecraft().gameSettings));}
        }else if (event.getGui() instanceof GuiCustomizeSkin){
            if (Gc4.player==null) {event.setGui(new CustomizeSkin(options));}
        }else if (event.getGui() instanceof GuiDownloadTerrain){
            if (Gc4.player==null) {event.setGui(new DownloadTerrain());}
        }else if (event.getGui() instanceof GuiScreenOptionsSounds){
            if (Gc4.player==null) {event.setGui(new MusicOption(options,Minecraft.getMinecraft().gameSettings));}
        }else if (event.getGui() instanceof GuiOptions) {
            if (Gc4.player == null) {
                event.setGui(options);
            }
        }

    }
    public void changeBgm(int idx) {}
}
