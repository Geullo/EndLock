package com.geullo.endpassward.END.Util;

import com.geullo.endpassward.END.Background.Connecting;
import com.geullo.endpassward.END.Background.Options;
import com.geullo.endpassward.Render.Render;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class BackGround extends GuiScreen {
    private boolean drawscreenBoolean = false;
    private int port = 311;//311;
    private String server = "mdmcs.r-e.kr";//"mdmcs.r-e.kr";
    private final int imageCut = 125;
    public boolean iconVisible = true;
    public int hiddenButtonCount = 3,nowImage = 0;
    private int[] bgPos = new int[2], bgSize = new int[2],
                  iconPos = new int[2], iconSize = new int[2],
                  LogoPos = new int[2], LogoSize = new int[2],
                  creditPos = new int[2], creditSize = new int[2];
    public String getServerPort(){
        return server+":"+port;
    }
    public String getServer(){
        return server;
    }
    public int getPort(){
        return port;
    }
    public void setServer(String svr){
        server = svr;
    }
    public void setPort(int prt){
        port = prt;
    }

    public BackGround() {}
    @Override
    public void initGui()
    {
        mc.mouseHelper.ungrabMouseCursor();
        try {
            Robot robot;
            robot = new Robot();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            robot.mouseMove(width * 10, height * 10);
            robot.mouseMove(x, y);
            firstset();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public void firstset()
    {
        if (bgSize[0]!=width||bgSize[1]!=height) {
            bgPos[0] = 0;
            bgPos[1] = 0;
            bgSize[0] = width;
            bgSize[1] = height;
            if (iconVisible) {
                LogoSize[0] = (width / 17) / 2;
                LogoSize[1] = LogoSize[0];
                LogoPos[0] = bgPos[0] + (LogoSize[0] / 5);
                LogoPos[1] = (height - LogoSize[1]) - (LogoSize[0] / 8);
                creditSize[0] = (int) (width / 2.7);
                creditSize[1] = (int) (LogoSize[1] * 2.5);
                creditPos[0] = LogoPos[0];
                creditPos[1] = (height - creditSize[1]) - (LogoSize[0] / 8);
                iconSize[0] = LogoSize[0] + LogoSize[0] / 2;
                iconSize[1] = iconSize[0];
                iconPos[0] = (bgSize[0] - iconSize[0]) - (iconSize[0] / 4);
                iconPos[1] = (bgSize[1] - iconSize[1]) - (iconSize[0] / 4);
            }
        }
    }

    @Override
    public void updateScreen()
    {
        firstset();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        try {
            if (!this.drawscreenBoolean) {
                mc.mouseHelper.ungrabMouseCursor();
            }

            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();

            GlStateManager.translate(0, 0, -2000.0);
            if (nowImage >= imageCut) this.nowImage = 0;
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/" + b(nowImage) + ".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(bgPos[0], bgPos[1], bgSize[0], bgSize[1]);
            this.nowImage++;
            if (iconVisible) {
                if (!Utils.iconClicked(mouseX, mouseY, LogoPos[0], LogoPos[1], LogoSize[0], LogoSize[1])) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/Logo.png"));
                    Render.setColor(0xffffffff);
                    Render.drawTexturedRect(LogoPos[0], LogoPos[1], LogoSize[0], LogoSize[1]);
                } else {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/team.png"));
                    Render.setColor(0xffffffff);
                    Render.drawTexturedRect(creditPos[0], creditPos[1], creditSize[0], creditSize[1]);
                }
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GlStateManager.popMatrix();

                GlStateManager.pushMatrix();
                GlStateManager.loadIdentity();

                GlStateManager.translate(0, 0, -2000.0);
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "background/setting.png"));
                if (Utils.iconClicked(mouseX, mouseY, iconPos[0], iconPos[1], iconSize[0], iconSize[1])) {
                    Render.setColor(0xff999999);
                } else {
                    Render.setColor(0xffffffff);
                }
                Render.drawTexturedRect(iconPos[0], iconPos[1], iconSize[0], iconSize[1]);
            }
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();

            if (!this.drawscreenBoolean)
                mc.mouseHelper.ungrabMouseCursor();
            this.drawscreenBoolean = true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton==0) {
            if (iconVisible) {
                if (Utils.iconClicked(mouseX, mouseY, LogoPos[0], LogoPos[1], LogoSize[0], LogoSize[1])) {
                    if (hiddenButtonCount > 0) {
                        hiddenButtonCount--;
                    }
                    if (hiddenButtonCount == 0) {
                        GlStateManager.disableFog();
                        GlStateManager.disableLighting();
                        mc.displayGuiScreen(new ServerChange(this));
                        hiddenButtonCount = 3;
                    }
                } else if (Utils.iconClicked(mouseX, mouseY, iconPos[0], iconPos[1], iconSize[0], iconSize[1])) {
                    openSettings();
                } else if (Utils.iconClicked(mouseX, mouseY, bgPos[0], bgPos[1], bgSize[0], bgSize[1])) {
                    connectServer();
                }
            }
        }
    }
    private String b(int id)
    {
        String s = "0"+id;
        if (id < 10) s = "000" + id;
        if (10 <= id && id < 100) s = "00" + id;
        return s;
    }
    private void connectServer()
    {
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        mc.displayGuiScreen(new Connecting(this,mc,server,port));
    }
    private void openSettings()
    {
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        mc.displayGuiScreen(new Options(this, mc.gameSettings));
    }
}
