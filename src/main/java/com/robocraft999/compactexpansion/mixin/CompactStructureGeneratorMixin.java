package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.util.CompactStructureGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static dev.compactmods.machines.util.CompactStructureGenerator.generateCompactWall;
import static dev.compactmods.machines.util.CompactStructureGenerator.getWallBounds;

@Mixin(CompactStructureGenerator.class)
public abstract class CompactStructureGeneratorMixin {

    @Unique
    private static void compactExpansion$generateChunkgenBlocks(LevelAccessor world, AABB machineInternal){
        IntStream.iterate((int)machineInternal.minX, x -> x <= machineInternal.maxX, x -> x + 2)
                .boxed()
                .flatMap(x -> IntStream.iterate((int)machineInternal.minY, y -> y <= machineInternal.maxY, y -> y + 2)
                        .boxed()
                        .flatMap(y -> IntStream.iterate((int)machineInternal.minZ, z -> z <= machineInternal.maxZ, z -> z + 2)
                                .mapToObj(z -> new BlockPos(x,y,z))
                        )
                )
                .forEach(pos -> world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 7));
    }

    @Inject(method = "generateCompactStructure", at = @At("HEAD"), remap = false, cancellable = true)
    private static void onGenerateCompactStructure(LevelAccessor world, Vec3i dimensions, BlockPos cubeFloorCenter, CallbackInfo ci){
        AABB floorBlocks = getWallBounds(dimensions, cubeFloorCenter, Direction.DOWN);
        AABB machineInternal = floorBlocks.move(1.0F, 1.0F, 1.0F).contract(2.0F, 0.0F, 2.0F).expandTowards(0.0F, dimensions.getY() - 1, 0.0F);
        Stream<BlockPos> var10000 = BlockPos.betweenClosedStream(floorBlocks);
        Objects.requireNonNull(world);
        boolean anyAir = var10000.anyMatch(world::isEmptyBlock);
        if (anyAir) {
            for(Direction dir : Direction.values()) {
                generateCompactWall(world, dimensions, cubeFloorCenter, dir);
            }

            compactExpansion$generateChunkgenBlocks(world, machineInternal);
            //works too but is 4x more blocks
            //BlockPos.betweenClosedStream(machineInternal).forEach((p) -> world.setBlock(p, Blocks.DIRT.defaultBlockState(), 7));

            BlockPos.betweenClosedStream(machineInternal).forEach((p) -> world.setBlock(p, Blocks.AIR.defaultBlockState(), 7));
        }
        ci.cancel();
    }
}
