package com.geullo.endpassward.configuration;

import net.minecraft.client.Minecraft;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetValue {
    static InputStream GjhmPwbwK10c;
    public void SetValue(String Gt0GP1eX, boolean GA1wwjhBA) throws IOException {
        try {
            String Gm0AAf0K8uKsm = Minecraft.getMinecraft().mcDataDir + "/config/ESCAPEROOM/passward.properties";
            try {
                FileInputStream GjhmPw = new FileInputStream(Gm0AAf0K8uKsm);
                Properties GmKsm = new Properties();
                GmKsm.load(GjhmPw);
                GjhmPw.close();
                if (GmKsm.getProperty(Gt0GP1eX)!=null) {
                    FileOutputStream GsPwmPw = new FileOutputStream(Gm0AAf0K8uKsm);
                    GmKsm.setProperty(Gt0GP1eX, String.valueOf(GA1wwjhBA));
                    GmKsm.store(GsPwmPw, null);
                    GsPwmPw.close();
                }
            }
            catch(IOException io) {
                io.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert false;
                if (GjhmPwbwK10c!=null) {
                    GjhmPwbwK10c.close();
                }
            }catch (NullPointerException e){e.printStackTrace();}
        }
    }
}
