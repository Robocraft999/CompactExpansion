package com.robocraft999.compactexpansion.registry;

import com.robocraft999.compactexpansion.CompactExpansion;
import dev.compactmods.machines.forge.CompactMachines;
import dev.compactmods.machines.forge.machine.item.BoundCompactMachineItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CEItems {
    static final Supplier<Item.Properties> MACHINE_ITEM_PROPS = () -> new Item.Properties()
            .tab(CompactMachines.COMPACT_MACHINES_ITEMS);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CompactExpansion.MODID);

    /*public static final RegistryObject<Item> EXPANDED_MACHINE_ITEM_SIMPLE =
            ITEMS.register("expanded_machine_simple", () -> new BoundCompactMachineItem(MACHINE_ITEM_PROPS.get()));*/
}
