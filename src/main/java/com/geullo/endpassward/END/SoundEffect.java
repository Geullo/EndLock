package com.geullo.endpassward.END;

import com.geullo.endpassward.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import net.minecraftforge.registries.IForgeRegistry;


@SuppressWarnings("WeakerAccess")
public class SoundEffect {
    public static SoundEvent CORRECT, WRONG,SPIN, DOOR_LOCK_FAIL, DOOR_LOCK_OPEN,INPUT,
            DOOR_LOCK_PAD_CLICK, QUIZ_COMPUTER_2, QUIZ_COMPUTER_4,MAIN_MENU,
            BGM1,BGM2,BGM3;

    public static void registerSounds(IForgeRegistry<SoundEvent> e){
        CORRECT = registerSound("lock.correct",e);
        WRONG = registerSound("lock.wrong",e);
        SPIN = registerSound("lock.spin",e);
        INPUT = registerSound("lock.input",e);
        DOOR_LOCK_FAIL = registerSound("lock.door.fail",e);
        DOOR_LOCK_OPEN = registerSound("lock.door.open",e);
        DOOR_LOCK_PAD_CLICK = registerSound("lock.door.pad_click",e);
        QUIZ_COMPUTER_2 = registerSound("quiz.computer_2",e);
        QUIZ_COMPUTER_4 = registerSound("quiz.computer_4",e);
        MAIN_MENU = registerSound("background.main_menu",e);
        BGM1 = registerSound("background.1",e);
        BGM2 = registerSound("background.2",e);
        BGM3 = registerSound("background.3",e);
    }

    private static SoundEvent registerSound(String soundName, IForgeRegistry e){
        final ResourceLocation soundId = new ResourceLocation(Reference.MODID,soundName);
        SoundEvent soundEvent = new SoundEvent(soundId).setRegistryName(soundName);
        e.register(soundEvent);
        return soundEvent;
    }
}
