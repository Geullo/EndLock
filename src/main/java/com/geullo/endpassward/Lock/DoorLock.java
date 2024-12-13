package com.geullo.endpassward.Lock;

import com.geullo.endpassward.Render.Render;
import com.geullo.endpassward.configuration.GetOneValue;
import com.geullo.endpassward.configuration.SetValue;
import com.geullo.endpassward.util.Sound;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class DoorLock extends GuiScreen {
    private boolean drawscreenBoolean = false,displayed = true;// true = ENG,false=NUM
    public float[]
            LogoPos = new float[6],bodyPos = new float[2],
            numberPosX = new float[13],numberPosY = new float[13],
            LogoSize = new float[2],bodySize = new float[2],
            numberSize = new float[2];
    public float[] numberU = new float[12];
    private int wrong,wrongDisplay=0;
    public float widthSize=0, heightSize = 0,bodyX=0,bodyY=0;
    public int previousWidth = width, previousHeight = height;
    private int displayTime,changeMode=0,mx,my;
    private String passward;
    private String inputPw = "";
    private final String[] alphabetMapping = {"A","B","C","E","I","K","L","M","N","T","O"};
    public String getPassward(){
        return passward;
    }
    public String getUserInputPw() {return inputPw;}
    public DoorLock(String passward){
        passward.toUpperCase();
        passward.replace(" ","");
        this.passward = passward;
        this.mx = MouseInfo.getPointerInfo().getLocation().x;
        this.my = MouseInfo.getPointerInfo().getLocation().y;
    }

    public DoorLock(String passward,int widthSize,int heightSize){
        this.widthSize = widthSize;
        this.heightSize = heightSize;
        passward = passward.toUpperCase();
        passward = passward.replace(" ","");
        this.passward = passward;
        this.mx = MouseInfo.getPointerInfo().getLocation().x;
        this.my = MouseInfo.getPointerInfo().getLocation().y;
    }
    @Override
    public void initGui() {
        mc.mouseHelper.ungrabMouseCursor();
        try {
            Robot robot;
            robot = new Robot();
            robot.mouseMove(mx, my);
            firstset();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public void setSize(){
            bodySize[0] = (int) (width / 4.235);
            bodySize[1] = (int) (height / 1.3);


        numberSize[0] = (int) (bodySize[0]/5.75);
        numberSize[1] = numberSize[0];
        LogoSize[0] = (int) (bodySize[0]/1.428);
        LogoSize[1] = (int) (bodySize[1]/5.84);
    }
    public void setBodyPos(){
            bodyPos[0] = (int) (width - bodySize[0]) >> 1;
            bodyPos[1] = (int) (height - bodySize[1]) >> 1;


        if (bodyPos[0] <= -1) {
            bodyPos[0] = 0;
        }
        if (bodyPos[1] <= -1) {
            bodyPos[1] = 0;
        }
    }
    public void setNumberPos(){
        numberPosX[1] = (int) (bodyPos[0]+(numberSize[0]/0.96));
        numberPosY[1] = (int) (bodyPos[1]+(numberSize[1]*1.165));
        for (int i=2;i<numberPosY.length;i++) {
            if (i == 4||i==7||i==10) {

                numberPosX[i] = numberPosX[1];
                if (i==10){
                    numberPosX[i] = numberPosX[8];
                }
                numberPosY[i] = (int) ((numberPosY[i-1]+numberSize[1])+ (numberSize[1] / 2.5));
            } else {
                numberPosX[i] = (int) ((numberPosX[i - 1] + numberSize[0]) + (numberSize[0] / 2.5));
                numberPosY[i] = numberPosY[i-1];
                if (i==11){
                    numberPosX[i] =numberPosX[3];
                }
                if (i==12){
                    numberPosX[i] = numberPosX[1];
                }
            }

        }
        numberU[0] = 0;
        numberU[1] = 0.0909f;
        for (int i = 2;i<numberU.length;i++){
            numberU[i] = numberU[i-1]+numberU[1];
        }
    }
    public void setLogoPos(){
        LogoPos[0] = numberPosX[12]-(numberSize[0]/6);
        LogoPos[1] = (int) ((numberPosY[12]+numberSize[1])*1.08);
    }
    public void firstset(){
        if (previousWidth!=width||previousHeight!=height) {
            try {
                this.mx = MouseInfo.getPointerInfo().getLocation().x;
                this.my = MouseInfo.getPointerInfo().getLocation().y;
                setSize();
                setBodyPos();
                setNumberPos();
                setLogoPos();
                Robot robot;
                robot = new Robot();
                robot.mouseMove(mx, my);
                this.previousHeight = height;
                this.previousWidth = width;
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void updateScreen() {
        firstset();
    }
    public void drawBg(){
        drawDefaultBackground();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        try {
            if (!this.drawscreenBoolean){
                try {
                    mc.mouseHelper.ungrabMouseCursor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (GetOneValue.GetOneValue("DOOR",passward)&&wrong!=2){
                openDoor();
            }
        }catch (NullPointerException | IOException e) {e.printStackTrace();}
        drawBg();
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();

        GlStateManager.translate(0,0,-2000.0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/body.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(bodyPos[0], bodyPos[1], bodySize[0], bodySize[1]);
        if (changeMode == 0) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/number.png"));
        }else if (changeMode==1){
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/english.png"));
        }
        for (int i=0;i<numberU.length;i++){
            if (Utils.iconClicked(mouseX,mouseY,numberPosX[i],  numberPosY[i],  numberSize[0], numberSize[1])){
                Render.setColor(0xffa4e6ff);
            }else{
                Render.setColor(0xffffffff);
            }
            if (i!=0) {
                Render.drawTexturedRect(numberPosX[i], numberPosY[i], numberSize[0], numberSize[1], numberU[i - 1], 0, numberU[i], 1);
            }else{
                Render.drawTexturedRect(numberPosX[i], numberPosY[i], numberSize[0], numberSize[1], 0, 0, numberU[i], 1);
            }
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/change.png"));
        if (Utils.iconClicked(mouseX,mouseY,numberPosX[12], numberPosY[12], numberSize[0], numberSize[1])){
            Render.setColor(0xffa4e6ff);
        }else{
            Render.setColor(0xffffffff);
        }
        Render.drawTexturedRect(numberPosX[12], numberPosY[12], numberSize[0], numberSize[1], 0, 0, 1, 1);

        if (wrong==0&&wrongDisplay == 0) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/common.png"));
        }
        if (wrong == 1){
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/wrong.png"));
            wrongDisplay++;
            if (wrongDisplay>=110){
                wrongDisplay=0;
                wrong = 0;
            }
        }
        if (wrong == 2){
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/door/correct.png"));
            if (wrongDisplay>=35){
                displayed=false;
                displayTime++;
            }else{
                wrongDisplay++;
            }
        }
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(LogoPos[0], LogoPos[1], LogoSize[0], LogoSize[1], 0, 0, 1, 1);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GlStateManager.popMatrix();
        if (displayTime == 55) {
            try {
                mc.displayGuiScreen(null);
                GlStateManager.disableFog();
                GlStateManager.disableLighting();
                onReward();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!this.drawscreenBoolean) {
            try {
                mc.mouseHelper.ungrabMouseCursor();
                Robot robot;
                robot = new Robot();
                robot.mouseMove(mx, my);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        this.drawscreenBoolean = true;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_ESCAPE){
            mc.displayGuiScreen(null);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
            if (wrong == 2){
                onReward();
            }
        }
    }
    public void onReward() throws IOException {
        SetValue setValue = new SetValue();
        setValue.SetValue("DOOR_"+passward,true);
    }

    protected void sendInputPW(){
        if (inputPw.length()<=0) return;
        if (mc.player!=null) mc.player.sendChatMessage("/so &f&l도어락 LOG :: &6&l%player%&f가 입력한 비밀번호 : &e"+inputPw);
    }
    private void wrongNumber(){
        sendInputPW();
        wrong = 1;
        inputPw = "";
    }
    private void openDoor(){
        wrong = 2;
        displayed=true;
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0)
        {
            if (displayed) {
                for (int i=0;i<numberPosX.length;i++) {
                    l:
                    if (Utils.iconClicked(mouseX, mouseY, numberPosX[i], numberPosY[i], numberSize[0], numberSize[1])) {
                        clickPad();
                        if (i==12){ // changed num and eng
                            if (changeMode==0){
                                changeMode=1;
                            }else if (changeMode==1){
                                changeMode=0;
                            }
                        }else{
                            if (changeMode==0) {
                                if (i==11){
                                    inputPw = inputPw.concat("#");
                                }else if (i==10){
                                    inputPw = inputPw.concat("0");
                                }else {
                                    inputPw = inputPw.concat(String.valueOf(i));
                                }
                            }else if (changeMode==1){
                                inputPw = inputPw.concat(changedNumToEng(i));
                            }
                        }
                    }
                }
                if (Utils.iconClicked(mouseX,mouseY,LogoPos[0],LogoPos[1],LogoSize[0],LogoSize[1])){
                    if (inputPw.length()!=passward.length()) {
                        wrongNumber();return;
                    }
                    if (inputPw.equals(passward)) {
                        openDoor();
                    }else{
                        wrongNumber();
                    }
                }
            }
        }

    }
    public void clickPad(){
    }
    private String changedNumToEng(int n){
        return alphabetMapping[n-1];
    }
}
