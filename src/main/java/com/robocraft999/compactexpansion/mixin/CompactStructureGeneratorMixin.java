package com.robocraft999.compactexpansion.mixin;

import dev.compactmods.machines.util.CompactStructureGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.stream.Stream;

import static dev.compactmods.machines.util.CompactStructureGenerator.generateCompactWall;
import static dev.compactmods.machines.util.CompactStructureGenerator.getWallBounds;

@Mixin(CompactStructureGenerator.class)
public abstract class CompactStructureGeneratorMixin {

    @Unique
    private static void compactExpansion$generateChunkgenBlocks(LevelAccessor world, AABB aabb) {
        int minSectionX = SectionPos.blockToSectionCoord(Mth.floor(aabb.minX));
        int maxX = Mth.floor(aabb.maxX - 1.0E-7);
        int maxSectionX = SectionPos.blockToSectionCoord(maxX);

        int minSectionY = SectionPos.blockToSectionCoord(Mth.floor(aabb.minY));
        int maxY = Mth.floor(aabb.maxY - 1.0E-7);
        int maxSectionY = SectionPos.blockToSectionCoord(maxY);

        int minSectionZ = SectionPos.blockToSectionCoord(Mth.floor(aabb.minZ));
        int maxZ = Mth.floor(aabb.maxZ - 1.0E-7);
        int maxSectionZ = SectionPos.blockToSectionCoord(maxZ);

        for (int sx = minSectionX; sx <= maxSectionX; sx++) {
            for (int sy = minSectionY; sy <= maxSectionY; sy++) {
                for (int sz = minSectionZ; sz <= maxSectionZ; sz++) {

                    int sectionMinX = SectionPos.sectionToBlockCoord(sx);
                    int sectionMinY = SectionPos.sectionToBlockCoord(sy);
                    int sectionMinZ = SectionPos.sectionToBlockCoord(sz);

                    int x = Math.min(maxX, sectionMinX + 15);
                    int y = Math.min(maxY, sectionMinY + 15);
                    int z = Math.min(maxZ, sectionMinZ + 15);

                    world.setBlock(new BlockPos(x, y, z), Blocks.DIRT.defaultBlockState(), 7);
                }
            }
        }
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
