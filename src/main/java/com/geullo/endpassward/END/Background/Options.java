package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.Render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class Options extends GuiOptions {
    private GuiScreen la;

    public Options(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
        super(p_i1046_1_, p_i1046_2_);
        this.la = p_i1046_1_;
        System.out.println(p_i1046_1_);
    }

    @Override
    public void drawBackground(int tint) {
        super.drawBackground(tint);
        if (mc.world == null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/option.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(0, 0, width, height);
        }
    }
}
