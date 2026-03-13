package com.robocraft999.compactexpansion.data;

import com.robocraft999.compactexpansion.CompactExpansion;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider {

    public LangGen(PackOutput output) {
        super(output, CompactExpansion.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("machine.compactmachines.single", "Compact Machine (%s)".formatted("Single"));
        add("machine.compactmachines.double", "Compact Machine (%s)".formatted("Double"));
        add("machine.compactmachines.triple", "Compact Machine (%s)".formatted("Triple"));
        add("machine.compactmachines.quadruple", "Compact Machine (%s)".formatted("Quadruple"));
        add("machine.compactmachines.quintuple", "Compact Machine (%s)".formatted("Quintuple"));
        add("machine.compactmachines.sextuple", "Compact Machine (%s)".formatted("Sextuple"));
    }
}
