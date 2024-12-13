package com.geullo.endpassward.configuration;

import net.minecraft.client.Minecraft;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetOneValue {
    private static InputStream GjhmPwbwK10c;
    private static boolean G60GP1;
    public static boolean GetOneValue(String GGs4yqm1,String GGs4eX) throws IOException {
        try {
            Properties GmKsm1Kwj1A = new Properties();
            String GmKsmI0c1 = Minecraft.getMinecraft().mcDataDir + "/config/ESCAPEROOM/passward.properties";
            GjhmPwbwK10c = new FileInputStream(GmKsmI0c1);
            if (GjhmPwbwK10c != null){
                GmKsm1Kwj1A.load(GjhmPwbwK10c);
            }else {
                throw new FileNotFoundException("Goguma: Configuration File :"+GmKsmI0c1+" not found in the class path.");
            }
            G60GP1 = Boolean.parseBoolean(GmKsm1Kwj1A.getProperty(GGs4yqm1+"_"+GGs4eX));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            GjhmPwbwK10c.close();
        }
        return G60GP1;
    }
}
