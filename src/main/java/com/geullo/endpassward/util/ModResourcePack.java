package com.geullo.endpassward.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;


import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Set;


public class ModResourcePack implements IResourcePack {

    private File modFolder;
    private static final Set<String> domains = ImmutableSet.of("endpassward");

    public ModResourcePack(File modFolder) {
        this.modFolder = modFolder;
    }

    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        return new FileInputStream(new File(modFolder, location.getResourcePath()));
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        return new File(modFolder, location.getResourcePath()).exists();
    }

    @Override
    public Set<String> getResourceDomains() {
        return domains;
    }

    @Override
    public <T extends IMetadataSection> T getPackMetadata(MetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
        return null;
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        return null;
    }

    @Override
    public String getPackName() {
        return "EndPasswardResourcePack";
    }
}