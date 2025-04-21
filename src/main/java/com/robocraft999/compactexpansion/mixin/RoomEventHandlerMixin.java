package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.api.dimension.CompactDimension;
import dev.compactmods.machines.room.RoomEventHandler;
import dev.compactmods.machines.room.data.CompactRoomData;
import dev.compactmods.machines.room.exceptions.NonexistentRoomException;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RoomEventHandler.class)
public class RoomEventHandlerMixin {

    @Inject(
            at = @At("HEAD"),
            method = "positionInsideRoom",
            cancellable = true,
            remap = false
    )
    private static void onPositionInsideRoom(Entity entity, Vec3 target, CallbackInfoReturnable<Boolean> cir){
        Level level = entity.level();
        if (!level.dimension().equals(CompactDimension.LEVEL_KEY)) {
            cir.setReturnValue(false);
        } else if (level instanceof ServerLevel compactDim) {
            CompactRoomData intern = CompactRoomData.get(compactDim);
            cir.setReturnValue(intern.streamRooms().anyMatch(room -> room.getRoomBounds().inflate(1).contains(target)));
        } else {
            cir.setReturnValue(false);
        }
        cir.cancel();
    }
}
