package dev.creoii.creoapi.api.advancement.injector;

import dev.creoii.creoapi.api.advancement.AdvancementInjectionRegistry;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;

public class CriteriaInjector extends AdvancementInjectionRegistry.Injector {
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
    public AdvancementInjectionRegistry.Type getType() {
        return AdvancementInjectionRegistry.Type.CRITERIA;
    }

    public String getName() {
        return name;
    }

    public AdvancementCriterion getCriterion() {
        return criterion;
    }
}
