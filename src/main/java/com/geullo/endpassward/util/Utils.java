package com.geullo.endpassward.util;

public class Utils {
    public static boolean iconClicked(int mouseX, int mouseY, int x, int y, int width, int height){
        return (mouseX >= x&&mouseY >= y&&mouseX < x + width&&mouseY < y + height);
    }
    public static boolean iconClicked(int mouseX, int mouseY, float x, float y, float width, float height){
        return (mouseX >= x&&mouseY >= y&&mouseX < x + width&&mouseY < y + height);
    }
}
