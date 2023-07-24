package com.robocraft999.compactexpansion.registry;

import com.robocraft999.compactexpansion.CompactExpansion;
import dev.compactmods.machines.api.core.CMRegistryKeys;
import dev.compactmods.machines.api.room.RoomTemplate;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class CERoomTemplates {
    public static final DeferredRegister<RoomTemplate> ROOM_TEMPLATES = DeferredRegister.create(CMRegistryKeys.ROOM_TEMPLATES, CompactExpansion.MODID);

    public static final RegistryObject<RoomTemplate> EXPANDED_MACHINE_BASIC = ROOM_TEMPLATES.register("expanded_machine_basic", () -> new RoomTemplate(31, 0x00FF00));
    public static final RegistryObject<RoomTemplate> EXPANDED_MACHINE_SIMPLE = ROOM_TEMPLATES.register("expanded_machine_simple", () -> new RoomTemplate(63, 0x00FF00));
    public static final RegistryObject<RoomTemplate> EXPANDED_MACHINE_ADVANCED = ROOM_TEMPLATES.register("expanded_machine_advanced", () -> new RoomTemplate(127, 0x00FF00));
    public static final RegistryObject<RoomTemplate> EXPANDED_MACHINE_SUPER = ROOM_TEMPLATES.register("expanded_machine_super", () -> new RoomTemplate(255, 0x00FF00));
}
