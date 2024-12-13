package com.geullo.endpassward.Render;


public class Effect {
    public static boolean between(int smallnum, int nownum,int bignum){
        return bignum>=nownum&&smallnum<=nownum;
    }
    public static int fadeIn(int nowDisplayTime){
        return fadeIn(nowDisplayTime,100);
    }
    public static int fadeOut(int nowDisplayTime){
        return fadeOut(nowDisplayTime,100);
    }
    public static int fadeIn(int nowDisplayTime,int allDisplayTime){
        return reChangeAlphaAtFadeIn(nowDisplayTime*100/allDisplayTime);
    }
    public static int fadeOut(int nowDisplayTime,int allDisplayTime){
        return reChangeAlphaAtFadeOut(nowDisplayTime*100/allDisplayTime);
    }
    public static int reChangeAlphaAtFadeIn(int alpha){
        if (between(0,alpha,5)){
            return 0xffffffff;
        }else if (between(5,alpha,10)){
            return 0xf2ffffff;
        }else if (between(10,alpha,15)){
            return 0xe6ffffff;
        }else if (between(15,alpha,20)){
            return 0xd9ffffff;
        }else if (between(20,alpha,25)){
            return 0xccffffff;
        }else if (between(25,alpha,30)){
            return 0xbfffffff;
        }else if (between(30,alpha,35)){
            return 0xb3ffffff;
        }else if (between(35,alpha,40)){
            return 0xa6ffffff;
        }else if (between(40,alpha,45)){
            return 0x99ffffff;
        }else if (between(45,alpha,50)){
            return 0x8cffffff;
        }else if (between(50,alpha,55)){
            return 0x80ffffff;
        }else if (between(55,alpha,60)){
            return 0x73ffffff;
        }else if (between(60,alpha,65)){
            return 0x66ffffff;
        }else if (between(65,alpha,70)){
            return 0x59ffffff;
        }else if (between(70,alpha,75)){
            return 0x4dffffff;
        }else if (between(75,alpha,80)){
            return 0x40ffffff;
        }else if (between(80,alpha,85)){
            return 0x33ffffff;
        }else if (between(85,alpha,90)){
            return 0x26ffffff;
        }else if (between(90,alpha,95)){
            return 0x0dffffff;
        }else if (between(95,alpha,101)){
            return 0x00ffffff;
        }
        return 0;
    }

    public static int reChangeAlphaAtFadeOut(int alpha){
        if (between(95,alpha,101)){
            return 0xffffffff;
        }else if (between(90,alpha,95)){
            return 0xf2ffffff;
        }else if (between(85,alpha,90)){
            return 0xe6ffffff;
        }else if (between(80,alpha,85)){
            return 0xd9ffffff;
        }else if (between(75,alpha,80)){
            return 0xccffffff;
        }else if (between(70,alpha,75)){
            return 0xbfffffff;
        }else if (between(65,alpha,70)){
            return 0xb3ffffff;
        }else if (between(60,alpha,65)){
            return 0xa6ffffff;
        }else if (between(55,alpha,60)){
            return 0x99ffffff;
        }else if (between(50,alpha,55)){
            return 0x8cffffff;
        }else if (between(45,alpha,50)){
            return 0x80ffffff;
        }else if (between(40,alpha,45)){
            return 0x73ffffff;
        }else if (between(35,alpha,40)){
            return 0x66ffffff;
        }else if (between(30,alpha,35)){
            return 0x59ffffff;
        }else if (between(25,alpha,30)){
            return 0x4dffffff;
        }else if (between(20,alpha,25)){
            return 0x40ffffff;
        }else if (between(15,alpha,20)){
            return 0x33ffffff;
        }else if (between(10,alpha,15)){
            return 0x26ffffff;
        }else if (between(5,alpha,10)){
            return 0x0dffffff;
        }else if (between(0,alpha,5)){
            return 0x00ffffff;
        }
        return 0;
    }
}
