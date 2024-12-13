package com.geullo.endpassward.Lock;

import com.geullo.endpassward.Render.Render;
import com.geullo.endpassward.configuration.GetOneValue;
import com.geullo.endpassward.configuration.SetValue;
import com.geullo.endpassward.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class SpinLock extends GuiScreen {
    private boolean bigTF , drawscreenBoolean = false,displayed = false,unlocked=false;
    private int previousWidth = 0, previousHeight = 0,displayTime=0,unlockTime=0;

    private int[] BgPos = new int[4],BgSize = new int[4],upArrowPos = new int[8],
    ringPos = new int[4],bodyPos = new int[2],ringSize = new int[2],bodySize = new int[2],
    downArrowPos = new int[8],ArrowSize = new int[2],
    LNSize = new int[2],LNPos = new int[8];//0,1,2,3,4,5,6,7,8,9

    private String QuizNumber,passward;
    private String[] LNPath = new String[10],
    userInputNum = {"0","0","0","0"}, numbers = { "0","1","2","3","4","5","6","7","8","9" };


    private File modFolder; //= new File(Minecraft.getMinecraft().mcDataDir, "resources/EscapeRoom/" + this.entityName + "/laptop/");
    //private ResourceLocation[] textureArr;
    public String getPassward(){
        return passward;
    }
    public SpinLock(String passward){
        this.bigTF = true;
        this.modFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/spin/");
        this.passward = passward;
    }
    public SpinLock(String passward, String QuizNumber, boolean bigTF){
        this.bigTF = bigTF;
        this.QuizNumber = QuizNumber;
        this.modFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/endpassward/lock/spin/");
        this.passward = passward;

        for (int i = 0;i<numbers.length;i+=2){
            this.LNPath[i] = "lock/spin/number_"+i+".png";
            this.LNPath[i+1] = "lock/spin/number_"+(int) (i+1)+".png";
        }

    }
    @Override
    public void initGui(){
        mc.mouseHelper.ungrabMouseCursor();
        try {
            Robot robot;
            robot = new Robot();
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            robot.mouseMove(width * 10, height * 10);
            robot.mouseMove(x, y);

            firstSet();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public void sizeSet(){
        if (!bigTF) {
            BgSize[0] = (int) (width / 6.535);
            BgSize[1] = (int) (height / 2.567);
            BgSize[2] = (int) (width / 1.956);
            BgSize[3] = (int) (height / 1.856);
            LNSize[0] = BgSize[0] / 4;
            LNSize[1] = (int) (BgSize[0] / 8);
            ArrowSize[0] = (int) (LNSize[0] / 2.85);
            ArrowSize[1] = (int) (LNSize[0] / 2.85);
        } else {
            BgSize[0] = (int) (width / 4.235);
            BgSize[1] = (int) (height / 1.45);
            LNSize[0] = (int) (BgSize[0] / 3.85);
            LNSize[1] = (int) (BgSize[0] / 7.35);
            ArrowSize[0] = (int) (LNSize[0] / 2.85);
            ArrowSize[1] = (int) (LNSize[0] / 2.85);
        }
    }
    public void quizPosSet(){
        BgPos[0] = (int) ((width - BgSize[0]) / 1.25);
        BgPos[1] = (height - BgSize[1]) >> 1;
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
        for (int i = 0; i < 8; i += 2) {//0,1 + 2,3 + 4,5 + 6,7
            if (i == 0) {
                LNPos[i] = (int) (BgSize[0] / 1.365) + BgPos[0];
                LNPos[i + 1] = (int) (BgSize[1] / 2.56) + BgPos[1];
            } else if (i == 4) {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 1.846);
            } else if (i == 6) {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 1.7183);
            } else {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 2);
            }
        }
        for (int i = 0; i < upArrowPos.length; i += 2) {
            upArrowPos[i] = (int) ((LNPos[i] + LNSize[0]) + (ArrowSize[0] / 4.5));
            upArrowPos[i + 1] = LNPos[i + 1] + (ArrowSize[0] / 6);
        }
        for (int i = 0; i < downArrowPos.length; i += 2) {
            downArrowPos[i] = LNPos[i] - ArrowSize[0];//(LNPos[i]+LNSize[0]) + (ArrowSize[0]/4.5)
            if (i == 0) {
                downArrowPos[i + 1] = upArrowPos[i + 1];
            } else {
                downArrowPos[i + 1] = upArrowPos[i + 1];
            }
        }
    }
    public void BgPosSet(){
        BgPos[0] = (int) ((width - BgSize[0]) / 2);
        BgPos[1] = (height - BgSize[1]) /2;
        if (BgPos[0] <= -1) {
            BgPos[0] = 0;
        }
        if (BgPos[1] <= -1) {
            BgPos[1] = 0;
        }
    }
    public void numberPos(){
        for (int i = 0; i < 8; i += 2) {//0,1 + 2,3 + 4,5 + 6,7
            if (i == 0) {
                LNPos[i] = (int) (BgSize[0] / 1.365) + BgPos[0];
                LNPos[i + 1] = (int) (height / 2.32);
            } else if (i == 4) {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 1.734);
            } else if (i == 6) {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 1.535);
            } else {
                LNPos[i] = LNPos[i - 2];
                LNPos[i + 1] = (int) (height / 1.985);
            }
        }
    }
    public void setUpArrowPos(){
        for (int i = 0; i < upArrowPos.length; i += 2) {
            upArrowPos[i] = (int) ((LNPos[i] + LNSize[0]) + (ArrowSize[0] / 4.5));
            upArrowPos[i + 1] = LNPos[i + 1] + (ArrowSize[0] / 6);
        }
    }
    public void setDownArrowPos(){
        for (int i = 0; i < downArrowPos.length; i += 2) {
            downArrowPos[i] = (int) (LNPos[i] - ArrowSize[0]);//(LNPos[i]+LNSize[0]) + (ArrowSize[0]/4.5)
            if (i == 0) {
                downArrowPos[i + 1] = upArrowPos[i + 1];
            } else {
                downArrowPos[i + 1] = upArrowPos[i + 1];
            }
        }
    }
    public void setRingPosClicked(){
        ringPos[3] = BgPos[1]+8;
    }
    public void setRingSize(){
        ringSize[0] = BgSize[0];
        ringSize[1] = (int) (BgSize[1]/3.3f);
    }
    public void setBodySize(){
        bodySize[1] = (int) (BgSize[1]-(ringSize[1]/1.16f));
    }
    public void setBodyPos(){
        bodyPos[1] = (int) (BgPos[1]+(ringSize[1]/1.15f));
    }
    public void firstSet() {
        if (BgSize[0]!=width / 6.535||BgSize[1]!=height / 2.567) {
            sizeSet();

            if (!bigTF) {
                quizPosSet();
            } else {
                BgPosSet();
                numberPos();
                setUpArrowPos();
                setDownArrowPos();
            }
            setRingPosClicked();
            setRingSize();
            setBodySize();
            setBodyPos();
        }
    }

    @Override
    public void updateScreen() {
        if (previousHeight == 0 || previousWidth ==0){
            previousHeight = height;  previousWidth = width;
        }else if (previousWidth == width || previousHeight == height){
            previousHeight = height; previousWidth = width;
        }else {
            firstSet();
        }

    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        try {
            if (!this.drawscreenBoolean){
                mc.mouseHelper.ungrabMouseCursor();
            }
            if (GetOneValue.GetOneValue("SPIN",passward)&&!displayed){
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
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/quizs/quiz_" + QuizNumber + ".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(BgPos[2], BgPos[3], BgSize[2], BgSize[3]);
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/spin/spin_lock_ring.png"));
        Render.setColor(0xffffffff);
        if (!displayed) {
            if (unlocked) {
                Render.drawTexturedRect(BgPos[0], ringPos[3], ringSize[0], ringSize[1]);
                unlockTime++;
                if (unlockTime==25){
                    unlocked=false;
                    unlockTime = 0;
                }
            }else {
                Render.drawTexturedRect(BgPos[0], BgPos[1], ringSize[0], ringSize[1]);

            }
        }else if (displayed){
            Render.drawTexturedRect(BgPos[0]+(ringSize[0]/1.7), BgPos[1], ringSize[0], ringSize[1]);
            displayTime++;
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/spin/spin_lock_body.png"));
        Render.setColor(0xffffffff);
        Render.drawTexturedRect(BgPos[0], bodyPos[1], BgSize[0], bodySize[1]);


        for (int i=0;i< LNPos.length;i+=2) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/spin/number_"+userInputNum[i/2]+".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(LNPos[i], LNPos[i+1], LNSize[0], LNSize[1]);
        }
        for (int i=0;i< downArrowPos.length;i+=2) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/spin/arrow_1.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(upArrowPos[i], upArrowPos[i+1], ArrowSize[0], ArrowSize[1]);
            if (Utils.iconClicked(mouseX,mouseY,upArrowPos[i], upArrowPos[i+1], ArrowSize[0], ArrowSize[1])) {
                Render.setColor(0x80000000);
                Render.drawTexturedRect(upArrowPos[i], upArrowPos[i+1], ArrowSize[0], ArrowSize[1]);
            }
        }
        for (int i=0;i< downArrowPos.length;i+=2) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("endpassward", "lock/spin/arrow_0.png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(downArrowPos[i], downArrowPos[i+1], ArrowSize[0], ArrowSize[1]);
            if (Utils.iconClicked(mouseX,mouseY,downArrowPos[i], downArrowPos[i+1], ArrowSize[0], ArrowSize[1])) {
                Render.setColor(0x80000000);
                Render.drawTexturedRect(downArrowPos[i], downArrowPos[i+1], ArrowSize[0], ArrowSize[1]);
            }
        }


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
        super.drawScreen(mouseX, mouseY, f);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_ESCAPE){
            mc.displayGuiScreen(null);
            GlStateManager.disableFog();
            GlStateManager.disableLighting();
            if (displayed){
                onReward();
            }
        }
    }
    public void onReward() throws IOException {
        SetValue setValue = new SetValue();
        setValue.SetValue("SPIN_"+passward,true);
    }
    public void sendInputPW(){
        if (userInputNum.length<=0) return;
        final String[] pwd = {""};
        Arrays.stream(userInputNum).forEach(l->{
            pwd[0] = pwd[0].concat(l);
        });
        if (mc.player!=null) mc.player.sendChatMessage("/so &f&l일반 자물쇠 LOG :: &6&l%player%&f가 입력한 비밀번호 : &e"+pwd[0]);
    }
    public void onFirstSlotLeftClick(){}
    public void onFirstSlotRightClick(){}
    public void onSecondSlotLeftClick(){}
    public void onSecondSlotRightClick(){}
    public void onThirdSlotLeftClick(){}
    public void onThirdSlotRightClick(){}
    public void onFourthSlotLeftClick(){}
    public void onFourthSlotRightClick(){}
    // 0 = LMB, 1 = RMB, 2 = MMB
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0)
        {
            if (!displayed) {
                for (int i = 0; i < upArrowPos.length; i += 2) {
                    if (Utils.iconClicked(mouseX, mouseY, upArrowPos[i], upArrowPos[i + 1], ArrowSize[0], ArrowSize[1])) {
                        userInputNum[i / 2] = addUserNum(userInputNum[i / 2]);
                        spin();
                        glClear();
                        switch (i){
                            case 0:
                                onFirstSlotRightClick();break;
                            case 2:
                                onSecondSlotRightClick();break;
                            case 4:
                                onThirdSlotRightClick();break;
                            case 6:
                                onFourthSlotRightClick();break;
                        }
                    }
                }

                for (int i = 0; i < downArrowPos.length; i += 2) {
                    if (Utils.iconClicked(mouseX, mouseY, downArrowPos[i], downArrowPos[i + 1], ArrowSize[0], ArrowSize[1])) {
                        userInputNum[i / 2] = removeUserNum(userInputNum[i / 2]);
                        spin();
                        glClear();
                        switch (i){
                            case 0:
                                onFirstSlotLeftClick();break;
                            case 2:
                                onSecondSlotLeftClick();break;
                            case 4:
                                onThirdSlotLeftClick();break;
                            case 6:
                                onFourthSlotLeftClick();break;
                        }
                    }
                }
                String userNum = userInputNum[0] + userInputNum[1] + userInputNum[2] + userInputNum[3];
                if (Utils.iconClicked(mouseX, mouseY, BgPos[0], BgPos[1], ringSize[0], (int) (ringSize[1]/1.13f))) {
                    if (userNum.length()<passward.length()||userNum.length()>passward.length()){
                        failLock();
                    }
                    if (!unlocked) unlocked=true;
                    if (userNum.equals(passward)) {
                        correctLock();
                    }else{
                        failLock();
                    }
                }
            }
        }
    }
    public void correctLock(){
        displayed = true;
    }
    public void spin(){
    }
    public void failLock(){
        sendInputPW();
    }
    public void glClear(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
    public String addUserNum(String NowSlot){
        if (NowSlot.equals("9")) {return "0";}
        return String.valueOf(Integer.parseInt(NowSlot) + 1);
    }
    public String removeUserNum(String NowSlot){
        if (NowSlot.equals("0")) {return "9";}
        return String.valueOf(Integer.parseInt(NowSlot) - 1);
    }

}
