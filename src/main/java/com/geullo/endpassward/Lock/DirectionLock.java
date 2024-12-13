package com.geullo.endpassward.Lock;

import com.geullo.endpassward.Render.Render;
import com.geullo.endpassward.configuration.GetOneValue;
import com.geullo.endpassward.configuration.SetValue;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class DirectionLock extends GuiScreen {
    private boolean bigTF , drawscreenBoolean = false,displayed = false,imageClicked =  false,check=false
            ,unlocked=false,failed = false;
    private float[] BgPos = new float[4],BgSize = new float[4],
            circlePos = new float[4],circleSize = new float[2],
            ringPos = new float[6],bodyPos = new float[2],
            ringSize = new float[2],bodySize = new float[2],
            circleAllowPos = new float[4], confirm = new float[4];

    private int displayTime,gapX,gapY,unlockTime;
    private int resetcount;
    private File[] modFolder = new File[2];
    private String quiz_num,passward,inputPw="";
    public String getPassward(){
        return passward;
    }
    public enum COORD{
        EAST("0",0),
        WEST("1", 1),
        SOUTH("2", 2),
        NORTH("3", 3);
        public final String label;
        public final Integer coord;

        COORD(String label, Integer coord) {
            this.label = label;
            this.coord = coord;
        }
    }
    public DirectionLock(String passward){
        this.bigTF = true;
        this.modFolder[0] = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/direction/");
        this.passward = passward;
        countReset();
    }
    public DirectionLock(String passward, String QuizNumber, boolean bigTF){
        this.bigTF = bigTF;
        this.quiz_num = QuizNumber;
        this.modFolder[0] = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/direction/");
        this.passward = passward;
        countReset();
    }
    @Override
    public void initGui() {
        mc.mouseHelper.ungrabMouseCursor();
        try {
            Robot robot;
            robot = new Robot();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            robot.mouseMove(width * 10, height * 10);
            robot.mouseMove(x, y);
            firstSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void firstSet(){
        if (BgSize[0]!=width / 6.535||BgSize[1]!=height / 2.567) {
            if (!bigTF) {
                BgSize[0] = (int) (width / 6.535);
                BgSize[1] = (int) (height / 2.567);
                BgSize[2] = (int) (width / 1.956);
                BgSize[3] = (int) (height / 1.856);
            } else {
                BgSize[0] = (int) (width / 4.235);
                BgSize[1] = (int) (height / 1.45);
            }
            if (!bigTF) {
                BgPos[0] = (int) ((width - BgSize[0]) / 1.25);
                BgPos[1] = (int) (height - BgSize[1]) >> 1;
                if (BgPos[0] <= -1) {
                    BgPos[0] = 0;
                }
                if (BgPos[1] <= -1) {
                    BgPos[1] = 0;
                }
                BgPos[2] = (int) ((((width / 2) / 2) / 2) / 2.5);
                BgPos[3] = (int) (height / 4.654);
                if (BgPos[2] <= -1) {
                    BgPos[2] = 0;
                }
                if (BgPos[3] <= -1) {
                    BgPos[3] = 0;
                }
            } else {
                BgPos[0] = (int) ((width - BgSize[0]) / 2);
                BgPos[1] = (int) (height - BgSize[1]) >> 1;
                if (BgPos[0] <= -1) {
                    BgPos[0] = 0;
                }
                if (BgPos[1] <= -1) {
                    BgPos[1] = 0;
                }
            }
            ringSize[0] = (int) (BgSize[0] / 1.54f);
            ringSize[1] = (int) (BgSize[1] / 2.3f);
            ringPos[0] = BgPos[0] * 1.13f;
            ringPos[1] = BgPos[1];
            ringPos[2] = (ringPos[0] + (ringSize[0] / 1.4f));
            ringPos[3] = BgPos[1];
            ringPos[4] = ringPos[0];
            ringPos[5] = ringPos[1] + 8;
            bodySize[0] = BgSize[0] / 1.13f;
            bodySize[1] = (int) (BgSize[1] - (ringSize[1] / 1.16f));
            bodyPos[0] = BgPos[0] * 1.05f;
            bodyPos[1] = (int) (BgPos[1] + (ringSize[1] / 1.15f));
            circleSize[0] = BgSize[0] / 2.5f;
            circleSize[1] = circleSize[0];
            circlePos[0] = (bodyPos[0] + (bodySize[0] / 3.6f));
            circlePos[1] = (int) (bodyPos[1] + (bodySize[1] / 2.55f));
            circleAllowPos[0] = circlePos[0] + (circleSize[0] / 2.1f);
            circleAllowPos[1] = circlePos[0] - (circleSize[0] / 2.1f);
            circleAllowPos[2] = circlePos[1] + (circleSize[1] / 2.1f);
            circleAllowPos[3] = circlePos[1] - (circleSize[1] / 2.1f);
            circlePos[2] = circlePos[0];
            circlePos[3] = circlePos[1];

            confirm[0] = BgPos[0] + (ringSize[0] / 3.1f);
            confirm[1] = BgPos[1] + (ringSize[1] / 2);
            confirm[2] = (float) (ringSize[0] / 5.05);
            confirm[3] = (int) (ringSize[1] / 2.3);
            //BgPos[0] + (ringSize[0] / 10), (int) (BgPos[1] + (ringSize[1] / 2)), (int) (ringSize[0] / 5.85), (int) (ringSize[1] / 2.3))
        }
    }

    @Override
    public void updateScreen() {
        firstSet();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        try {
            if (!this.drawscreenBoolean){
                mc.mouseHelper.ungrabMouseCursor();
            }
            if (GetOneValue.GetOneValue("DIRE",passward)&&!displayed){
                correctLock();
            }

        }catch (NullPointerException e) {e.printStackTrace();} catch (IOException e) {
            e.printStackTrace();
        }
        drawDefaultBackground();
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();

        GlStateManager.translate(0,0,-2000.0);

        if (!bigTF) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/quizs/quiz_" + quiz_num + ".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(BgPos[2], BgPos[3], BgSize[2], BgSize[3]);
        }
        GL11.glPopMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();

        GlStateManager.translate(0,0,-2000.0);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/direction/ring.png"));
        Render.setColor(0xffffffff);
        if (!displayed) {
            if (unlocked) {
                Render.drawTexturedRect(ringPos[4], ringPos[5], ringSize[0], ringSize[1]);
                unlockTime++;
                if (unlockTime==28){
                    unlocked=false;
                    unlockTime = 0;
                }
            }else {
                Render.drawTexturedRect(ringPos[0], ringPos[1], ringSize[0], ringSize[1]);
            }
        }else if (displayed){
            Render.drawTexturedRect(ringPos[2], ringPos[3], ringSize[0], ringSize[1]);
            displayTime++;
        }
        GL11.glPopMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();

        GlStateManager.translate(0,0,-2000.0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/direction/body.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(bodyPos[0], bodyPos[1], bodySize[0], bodySize[1]);

        ot:
        if (imageClicked){
            circlePos[2] = mouseX-gapX;
            circlePos[3] = mouseY-gapY;
            if (!Utils.iconClicked(mouseX,mouseY, (int) circlePos[2],(int) circlePos[3],(int) circleSize[0], (int) circleSize[1])){
                replaceCircle();break ot;
            }
            if (Utils.iconClicked((int) circlePos[2],(int) circlePos[3], (int) circleAllowPos[0]+10, (int) circleAllowPos[3]-50,545,180)){
                insertPassward(COORD.EAST);break ot;
            }else if (Utils.iconClicked((int) circlePos[2], (int) circlePos[3], (int) circleAllowPos[1]-542, (int) circleAllowPos[3]-50,532,180)){
                insertPassward(COORD.WEST);break ot;
            }else if (Utils.iconClicked((int) circlePos[2], (int) circlePos[3], (int) circleAllowPos[1]-45, (int) (circleAllowPos[3]-583),160,573)){
                insertPassward(COORD.NORTH);break ot;
            }else if (Utils.iconClicked((int) circlePos[2], (int) circlePos[3], (int) circleAllowPos[1]-45, (int) circleAllowPos[2]+15,160,500)){
                insertPassward(COORD.SOUTH);break ot;
            }

        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/direction/circle.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(circlePos[2], circlePos[3], circleSize[0], circleSize[1]);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GlStateManager.popMatrix();
        if (displayTime == 100) {
            try {
                mc.displayGuiScreen(null);
                GlStateManager.disableFog();
                GlStateManager.disableLighting();
                onReward();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!this.drawscreenBoolean)
            mc.mouseHelper.ungrabMouseCursor();
        this.drawscreenBoolean = true;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    private void insertPassward(COORD coord){
        replaceCircle();
        if (!check) {
            inputPw(coord);
        }
        replaceCircle();
        countReset();
        check=false;
    }
    public void onReward() throws IOException {
        SetValue setValue = new SetValue();
        setValue.SetValue("DIRE_"+passward,true);
    }
    protected void sendInputPW(){
        if (mc.player!=null) mc.player.sendChatMessage("/so &f&l방향 자물쇠 LOG :: &6&l%player%&f가 입력한 비밀번호 : &e"+translatePW(inputPw));
    }
    protected String translatePW(String pw){
        final String[] pwd = {""};
        Arrays.stream(pw.split("")).forEach(l->{
            pwd[0] = pwd[0].concat(l.equals("0")?"동":l.equals("1")?"서":l.equals("2")?"남":l.equals("3")?"북":"");
        });
        return pwd[0];
    }
    private void countReset(){
        resetcount = 2;
        failed = false;
    }
    private void inputPw(COORD coord){
        inputPw = inputPw.concat(coord.label);
        check = true;
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE){
            mc.displayGuiScreen(null);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
            if (displayed){
                onReward();
            }
        }
        super.keyTyped(typedChar,keyCode);
    }
    private void replaceCircle(){
        imageClicked=false;
        circlePos[2] = circlePos[0];
        circlePos[3] = circlePos[1];
        gapX = 0;gapY=0;
    }
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (imageClicked) {
            replaceCircle();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton==0){
            if (Utils.iconClicked(mouseX,mouseY, (int) circlePos[2], (int) circlePos[3], (int) circleSize[0], (int) circleSize[1])){
                gapX = (int) (mouseX - circlePos[0]);gapY = (int) (mouseY - circlePos[1]);
                countReset();
                imageClicked=true;
            }
            if (Utils.iconClicked(mouseX, mouseY, (int) ringPos[0], (int) ringPos[1], (int) ringSize[0], (int) ringSize[1])) {
                if (!unlocked) {
                    unlocked = true;
                    if (inputPw.length() != passward.length()) {
                        failLock();
                        return;
                    }
                    if (inputPw.equals(passward)) {
                        correctLock();
                    } else {
                        failLock();
                    }
                }
            }
        }
    }
    public void correctLock(){
        if (!displayed) {
            displayed = true;
        }
    }
    public void failLock(){
        if (failed) {
            resetLock();return;
        }
        if (inputPw.length()>0){
            failed=true;
            sendInputPW();
        }
    }
    public void resetLock(){
        resetcount--;
        if (resetcount==0) {inputPw="";if (mc.player!=null) mc.player.sendChatMessage("/so &f&l방향 자물쇠 LOG :: &6&l%player%&f님이 비밀번호를 초기화 했습니다.");countReset();}
    }


}
