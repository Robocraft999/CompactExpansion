package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.api.room.RoomSize;
import dev.compactmods.machines.machine.CompactMachineItem;
import dev.compactmods.machines.machine.Machines;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CompactMachineItem.class)
public class CompactMachineItemMixin {

    @Inject(
            method = "getItemBySize",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private static void onGetBySize(RoomSize size, CallbackInfoReturnable<Item> cir){
        Item var10000;
        if (size == RoomSize.TINY)
            var10000 = Machines.MACHINE_BLOCK_ITEM_TINY.get();
        else if (size == RoomSize.SMALL)
            var10000 = Machines.MACHINE_BLOCK_ITEM_SMALL.get();
        else if (size == RoomSize.NORMAL)
            var10000 = Machines.MACHINE_BLOCK_ITEM_NORMAL.get();
        else if (size == RoomSize.LARGE)
            var10000 = Machines.MACHINE_BLOCK_ITEM_LARGE.get();
        else if (size == RoomSize.GIANT)
            var10000 = Machines.MACHINE_BLOCK_ITEM_GIANT.get();
        else if (size == RoomSize.MAXIMUM)
            var10000 = Machines.MACHINE_BLOCK_ITEM_MAXIMUM.get();
        else{
            throw new IncompatibleClassChangeError();
        }
        cir.setReturnValue(var10000);
    }
}
