package com.robocraft999.compactexpansion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.gametest.GameTestHolder;

import java.util.Random;

@GameTestHolder(CompactExpansion.MODID)
public class CorrectGenerationTest {

    @GameTest(
            timeoutTicks = 100000000,
            template = "empty"
    )
    public static void testRandomSizedGeneration(GameTestHelper helper) {
        int offsetX = 500;
        int offsetY = 200;
        var random = new Random();

        for(int round = 0; round < 100; round++) {
            if(round % 5 == 0) {
                System.out.println("Running Round: " + round);
            }

            int startX = random.nextInt(16) + round * offsetX;
            int startY = random.nextInt(16) + offsetY;
            int startZ = random.nextInt(16);

            int endX = random.nextInt(24,96) + round * offsetX;
            int endY = random.nextInt(24,96) + offsetY;
            int endZ = random.nextInt(24,96);

            AABB aabb = AABB.of(BoundingBox.fromCorners(new Vec3i(startX,startY,startZ), new Vec3i(endX,endY,endZ)));
            var world = helper.getLevel();

            // START TESTED CODE
            int minSectionX = SectionPos.blockToSectionCoord(Mth.floor(aabb.minX));
            int maxSectionX = SectionPos.blockToSectionCoord(Mth.floor(aabb.maxX));

            int minSectionY = SectionPos.blockToSectionCoord(Mth.floor(aabb.minY));
            int maxSectionY = SectionPos.blockToSectionCoord(Mth.floor(aabb.maxY));

            int minSectionZ = SectionPos.blockToSectionCoord(Mth.floor(aabb.minZ));
            int maxSectionZ = SectionPos.blockToSectionCoord(Mth.floor(aabb.maxZ));

            for (int sx = minSectionX; sx < maxSectionX; sx++) {
                for (int sy = minSectionY; sy < maxSectionY; sy++) {
                    for (int sz = minSectionZ; sz < maxSectionZ; sz++) {
                        int x = SectionPos.sectionToBlockCoord(sx) + 15;
                        int y = SectionPos.sectionToBlockCoord(sy) + 15;
                        int z = SectionPos.sectionToBlockCoord(sz) + 15;

                        world.setBlock(new BlockPos(x, y, z), Blocks.DIRT.defaultBlockState(), 7);
                    }
                }
            }
            // END TESTED CODE

            helper.runAfterDelay(2, () -> {
                for (int sx = minSectionX; sx < maxSectionX; sx++) {
                    for (int sy = minSectionY; sy < maxSectionY; sy++) {
                        for (int sz = minSectionZ; sz < maxSectionZ; sz++) {
                            int x = SectionPos.sectionToBlockCoord(sx);
                            int y = SectionPos.sectionToBlockCoord(sy);
                            int z = SectionPos.sectionToBlockCoord(sz);

                            var chunk = world.getChunk(new BlockPos(x,y,z));
                            var section = chunk.getSection(chunk.getSectionIndex(y));
                            helper.assertTrue(!section.hasOnlyAir(), "Only Air at: " + (sx-minSectionX) + " " + (sy-minSectionY) + " " + (sz-minSectionZ));
                        }
                    }
                }
            });
        }

        helper.succeed();
    }
}
