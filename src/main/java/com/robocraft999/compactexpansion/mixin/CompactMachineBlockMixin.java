package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.api.room.RoomSize;
import dev.compactmods.machines.machine.CompactMachineBlock;
import dev.compactmods.machines.machine.Machines;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(
            method = "getBySize",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private static void onGetBySize(RoomSize size, CallbackInfoReturnable<Block> cir){
        Block var10000;
        if (size == RoomSize.TINY)
            var10000 = Machines.MACHINE_BLOCK_TINY.get();
        else if (size == RoomSize.SMALL)
            var10000 = Machines.MACHINE_BLOCK_SMALL.get();
        else if (size == RoomSize.NORMAL)
            var10000 = Machines.MACHINE_BLOCK_NORMAL.get();
        else if (size == RoomSize.LARGE)
            var10000 = Machines.MACHINE_BLOCK_LARGE.get();
        else if (size == RoomSize.GIANT)
            var10000 = Machines.MACHINE_BLOCK_GIANT.get();
        else if (size == RoomSize.MAXIMUM)
            var10000 = Machines.MACHINE_BLOCK_MAXIMUM.get();
        else{
            throw new IncompatibleClassChangeError();
        }
        cir.setReturnValue(var10000);
    }
}
