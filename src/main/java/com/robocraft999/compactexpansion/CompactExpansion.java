package com.robocraft999.compactexpansion;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CompactExpansion.MODID)
public class CompactExpansion {
    public static final String MODID = "compactexpansion";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public CompactExpansion() {
        //CERoomTemplates.ROOM_TEMPLATES.register(modEventBus);
    }
}
