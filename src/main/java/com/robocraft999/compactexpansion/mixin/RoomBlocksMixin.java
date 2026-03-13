package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.api.room.RoomInstance;
import dev.compactmods.machines.room.RoomBlocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(RoomBlocks.class)
public class RoomBlocksMixin {

    @Inject(method = "getInternalBlocks", at = @At("RETURN"), cancellable = true, remap = false)
    private static void filterLargePreview(MinecraftServer server, RoomInstance room, CallbackInfoReturnable<CompletableFuture<StructureTemplate>> cir){
        if (room.boundaries().innerBounds().getSize() > 63) {
            cir.setReturnValue(CompletableFuture.completedFuture(new StructureTemplate()));
        }
    }

}
