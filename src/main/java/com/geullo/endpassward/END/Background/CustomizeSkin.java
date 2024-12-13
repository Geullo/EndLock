package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.Render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class CustomizeSkin extends GuiCustomizeSkin {
    public CustomizeSkin(GuiScreen parentScreenIn) {
        super(parentScreenIn);
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
