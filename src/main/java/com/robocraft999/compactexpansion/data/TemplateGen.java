package com.robocraft999.compactexpansion.data;

import com.robocraft999.compactexpansion.CompactExpansion;
import dev.compactmods.machines.api.CompactMachines;
import dev.compactmods.machines.api.machine.MachineColor;
import dev.compactmods.machines.api.room.template.RoomTemplate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TemplateGen extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(RoomTemplate.REGISTRY_KEY, TemplateGen::addRoomTemplates);

    TemplateGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries, BUILDER, Set.of(CompactExpansion.MODID));
    }

    private static void addRoomTemplates(BootstrapContext<RoomTemplate> ctx) {
        roomTemplate(ctx, "single", new RoomTemplate(7, FastColor.ARGB32.color(255, 0xff2679)));
        roomTemplate(ctx, "double", new RoomTemplate(15, FastColor.ARGB32.color(255, 0xffac26)));
        roomTemplate(ctx, "triple", new RoomTemplate(31, FastColor.ARGB32.color(255, 0x79ff26)));
        roomTemplate(ctx, "quadruple", new RoomTemplate(63, FastColor.ARGB32.color(255, 0x26ffac)));
        roomTemplate(ctx, "quintuple", new RoomTemplate(127, FastColor.ARGB32.color(255, 0x2679ff)));
        roomTemplate(ctx, "sextuple", new RoomTemplate(255, FastColor.ARGB32.color(255, 0xac26ff)));
    }

    private static void roomTemplate(BootstrapContext<RoomTemplate> ctx, String name, RoomTemplate template) {
        ctx.register(ResourceKey.create(RoomTemplate.REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, name)), template);
    }
}
