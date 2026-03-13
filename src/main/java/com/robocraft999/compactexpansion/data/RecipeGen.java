package com.robocraft999.compactexpansion.data;

import com.robocraft999.compactexpansion.CompactExpansion;
import dev.compactmods.machines.api.room.template.RoomTemplate;
import dev.compactmods.machines.machine.Machines;
import dev.compactmods.machines.room.Rooms;
import dev.compactmods.machines.shrinking.Shrinking;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider provider) {
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "single"), Tags.Items.INGOTS_IRON);
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "double"), Tags.Items.INGOTS_GOLD);
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "triple"), Tags.Items.GEMS_DIAMOND);
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "quadruple"), Tags.Items.OBSIDIANS);
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "quintuple"), Tags.Items.INGOTS_NETHERITE);
        addMachineRecipe(recipeOutput, provider, ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "sextuple"), Tags.Items.NETHER_STARS);
    }

    private void addMachineRecipe(RecipeOutput consumer, HolderLookup.Provider provider, ResourceLocation id, TagKey<Item> catalyst) {
        final var templateRef = provider.lookupOrThrow(RoomTemplate.REGISTRY_KEY)
                .getOrThrow(ResourceKey.create(RoomTemplate.REGISTRY_KEY, id));

        machineRecipeBuilder(consumer, templateRef, builder -> builder.define('P', catalyst));
    }

    protected void machineRecipeBuilder(RecipeOutput consumer, Holder.Reference<RoomTemplate> templateRef, UnaryOperator<ShapedRecipeBuilder> configure) {
        final var builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Machines.Items.forNewRoom(templateRef))
                .pattern("WWW")
                .pattern("EPS")
                .pattern("WWW")
                .define('W', Rooms.Items.BREAKABLE_WALL)
                .define('E', Shrinking.ENLARGING_MODULE)
                .define('S', Shrinking.SHRINKING_MODULE);

        configure.apply(builder);

        builder.unlockedBy("has_recipe", has(Rooms.Items.BREAKABLE_WALL));

        final var recipeId = ResourceLocation.fromNamespaceAndPath(CompactExpansion.MODID, "new_machine_" + templateRef.key().location().getPath());
        builder.save(consumer, recipeId);
    }
}
