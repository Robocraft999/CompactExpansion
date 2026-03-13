package com.robocraft999.compactexpansion.mixin;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "dev.compactmods.machines.api.room.RoomDimensions$Decoder")
class RoomDimensionsDecoderMixin {

    @Redirect(
            method = "decode",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/serialization/Codec;intRange(II)Lcom/mojang/serialization/Codec;"
            ),
            remap = false
    )
    private static Codec<Integer> redirectDecodeIntRange(int a, int b) {
        return Codec.intRange(a, 255);
    }
}
