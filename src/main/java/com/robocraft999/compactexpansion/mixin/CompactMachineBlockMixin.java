package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.machine.CompactMachineBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CompactMachineBlock.class)
public class CompactMachineBlockMixin {

    @Redirect(
            method = "lambda$use$4",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/compactmods/machines/room/Rooms;getInternalBlocks(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/level/ChunkPos;)Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate;"
            )
    )
    private static StructureTemplate dontPreview(MinecraftServer server, ChunkPos room){
        return new StructureTemplate();
    }
}
