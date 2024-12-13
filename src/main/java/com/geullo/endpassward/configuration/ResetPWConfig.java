package com.geullo.endpassward.configuration;

import net.minecraft.client.Minecraft;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class ResetPWConfig {
    private File G4shrjBzsG81K = new File(Minecraft.getMinecraft().mcDataDir, "config/ESCAPEROOM");
    public ResetPWConfig() throws IOException {
        if (!this.G4shrjBzsG81K.exists()) {this.G4shrjBzsG81K.mkdirs();}
        Files.delete(new File(G4shrjBzsG81K, "passward.properties").toPath());
        Files.copy(getClass().getResourceAsStream("/assets/endpassward/configs/passward.properties"), new File(G4shrjBzsG81K, "passward.properties").toPath());
    }
}
