package dev.creoii.creoapi.api.advancement;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;

public class CriteriaInjector extends AdvancementInjector.Injector {
    private final String name;
    private final AdvancementCriterion criterion;

    public CriteriaInjector(String name, AdvancementCriterion criterion) {
        this.name = name;
        this.criterion = criterion;
    }

    public CriteriaInjector(String name, CriterionConditions conditions) {
        this(name, new AdvancementCriterion(conditions));
    }

    @Override
    public AdvancementInjector.Type getType() {
        return AdvancementInjector.Type.CRITERIA;
    }

    public String getName() {
        return name;
    }

    public AdvancementCriterion getCriterion() {
        return criterion;
    }
}
