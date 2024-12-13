package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.Render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.util.ResourceLocation;

public class Connecting extends GuiConnecting {
    public Connecting(GuiScreen parent, Minecraft mcIn, String hostName, int port) {
        super(parent, mcIn, hostName, port);
    }
    @Override
    public void drawBackground(int tint) {
        super.drawBackground(tint);
        if (mc.world==null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/option.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(0, 0, width, height);
        }
    }
}
