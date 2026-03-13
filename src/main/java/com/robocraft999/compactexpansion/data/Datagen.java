package com.robocraft999.compactexpansion.data;

import com.robocraft999.compactexpansion.CompactExpansion;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CompactExpansion.MODID)
public class Datagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final var generator = event.getGenerator();

        final var packOutput = generator.getPackOutput();
        final var holderLookup = event.getLookupProvider();

        boolean server = event.includeServer();
        var rtOut = generator.addProvider(server, new TemplateGen(packOutput, holderLookup));
        generator.addProvider(server, new RecipeGen(packOutput, rtOut.getRegistryProvider()));

        var client = event.includeClient();
        generator.addProvider(client, new LangGen(packOutput));
    }
}
