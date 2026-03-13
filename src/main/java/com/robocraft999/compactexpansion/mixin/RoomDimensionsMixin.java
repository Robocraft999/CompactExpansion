package com.robocraft999.compactexpansion.mixin;

import com.mojang.serialization.Codec;
import dev.compactmods.machines.api.room.RoomDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RoomDimensions.class)
public class RoomDimensionsMixin {

    @Redirect(
            method = "<init>(I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I"
            ),
            remap = false
    )
    private static int redirectConstructorMin(int a, int b){
        return Math.min(a, 255);
    }

    @Redirect(
            method = "lambda$static$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/serialization/Codec;intRange(II)Lcom/mojang/serialization/Codec;"
            ),
            remap = false
    )
    private static Codec<Integer> redirectClinitIntRange(int a, int b){
        return Codec.intRange(a, 255);
    }
}
