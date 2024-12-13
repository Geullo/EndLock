package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.Render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.util.ResourceLocation;

public class DownloadTerrain extends GuiDownloadTerrain {
    public DownloadTerrain() {
        super();
    }
    @Override
    public void drawBackground(int tint) {
        super.drawBackground(tint);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/option.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(0, 0, width, height);
    }

}
