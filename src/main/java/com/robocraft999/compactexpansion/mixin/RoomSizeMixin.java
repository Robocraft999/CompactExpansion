package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.api.room.RoomSize;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RoomSize.class)
public class RoomSizeMixin {

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 3)
    )
    private static int changeSizeTiny(int constant){
        return 7;
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 5)
    )
    private static int changeSizeSmall(int constant){
        return 15;
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 7)
    )
    private static int changeSizeNormal(int constant){
        return 31;
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 9)
    )
    private static int changeSizeLarge(int constant){
        return 63;
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 11)
    )
    private static int changeSizeGiant(int constant){
        return 127;
    }

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(intValue = 13)
    )
    private static int changeSizeMaximum(int constant){
        return 255;
    }
}
