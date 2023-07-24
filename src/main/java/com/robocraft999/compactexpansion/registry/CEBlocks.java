package com.robocraft999.compactexpansion.registry;

import com.robocraft999.compactexpansion.CompactExpansion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class CEBlocks {
    static final BlockBehaviour.Properties MACHINE_BLOCK_PROPS = BlockBehaviour.Properties
            .of(Material.METAL)
            .strength(8.0F, 20.0F)
            .requiresCorrectToolForDrops();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CompactExpansion.MODID);

    /*public static final RegistryObject<Block> EXPANDED_MACHINE_SIMPLE =
            BLOCKS.register("expanded_machine_simple", () -> new LegacySizedCompactMachineBlock(RoomSize.TINY, MACHINE_BLOCK_PROPS));*/
}
