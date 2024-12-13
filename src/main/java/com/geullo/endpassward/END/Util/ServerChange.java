package com.geullo.endpassward.END.Util;

import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class ServerChange extends BackGround {
    private final BackGround parentScreen;
    private GuiTextField server;
    private final int[] serverInputBoxSize = new int[2];
    public ServerChange(BackGround parent){
        iconVisible = false;
        parentScreen = parent;
        nowImage = parent.nowImage;
    }

    @Override
    public void initGui() {
        super.initGui();
        serverInputBoxSize[0]=width/2/2;
        serverInputBoxSize[1]= height/3/2;
        server = new GuiTextField(0,fontRenderer,(width-serverInputBoxSize[0])/2,(height-serverInputBoxSize[1])/2,serverInputBoxSize[0],serverInputBoxSize[1]);
        server.setFocused(true);
        server.setEnableBackgroundDrawing(true);
        if (parentScreen.getPort()!=25565) {
            server.setText(parentScreen.getServerPort());
        }else{
            server.setText(parentScreen.getServer());
        }
        Keyboard.enableRepeatEvents(true);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        if (server!=null){
            server.drawTextBox();
            server.setEnabled(true);
        }
    }
    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        if(server!= null){server.mouseClicked(x,y,button);}
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (server!=null) {
            server.textboxKeyTyped(typedChar, keyCode);
            if (keyCode == Keyboard.KEY_RETURN&&!server.getText().equals("")&&server.getText().length()>1) {
                if (server.getText().contains(":")) {
                    parentScreen.setServer(server.getText().split(":")[0]);
                    parentScreen.setPort(Integer.parseInt(server.getText().split(":")[1]));
                } else {
                    parentScreen.setServer(server.getText());
                    parentScreen.setPort(25565);
                }
                parentScreen.nowImage = nowImage;
                mc.displayGuiScreen(parentScreen);
            }else if (keyCode==Keyboard.KEY_ESCAPE){
                parentScreen.nowImage = nowImage;
                mc.displayGuiScreen(parentScreen);
            }
        }
    }
}
