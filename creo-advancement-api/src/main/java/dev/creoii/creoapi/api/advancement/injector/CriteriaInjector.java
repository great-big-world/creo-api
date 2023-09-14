package dev.creoii.creoapi.api.advancement.injector;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;

public record CriteriaInjector(String name, AdvancementCriterion criterion) implements Injector {
    public CriteriaInjector(String name, CriterionConditions conditions) {
        this(name, new AdvancementCriterion(conditions));
    }

    @Override
    public Type getType() {
        return Type.CRITERIA;
    }
}
