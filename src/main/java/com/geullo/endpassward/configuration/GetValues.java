package com.geullo.endpassward.configuration;

import net.minecraft.client.Minecraft;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetValues {
    private static InputStream GjhmPwbwK10c;
    private static Boolean[] G60GP1A;
    public static Boolean[] GetValues(String GGs4yqm1) throws IOException {
        try {
            Properties GmKsm = new Properties();
            String GmKsmI0c1 = Minecraft.getMinecraft().mcDataDir + "/config/ESCAPEROOM/passward.properties";
            GjhmPwbwK10c = new FileInputStream(GmKsmI0c1);
            if (GjhmPwbwK10c != null){
                GmKsm.load(GjhmPwbwK10c);
            }else {
                throw new FileNotFoundException("Gogumah: Configuration File :"+GmKsmI0c1+" not found in the class path.");
            }
            for (int i = 0;i<GmKsm.size();i++){
                G60GP1A[i] = Boolean.valueOf(GmKsm.getProperty(GGs4yqm1+"_"+i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            GjhmPwbwK10c.close();
        }
        return G60GP1A;
    }
}
