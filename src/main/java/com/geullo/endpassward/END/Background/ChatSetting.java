package com.geullo.endpassward.END.Background;

import com.geullo.endpassward.Render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class ChatSetting extends ScreenChatOptions {
    public ChatSetting(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn);
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
