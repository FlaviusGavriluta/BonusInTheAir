package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;

import java.util.List;

public class BonusCalculatorImpl implements BonusCalculator {
    private final List<BonusRule> bonusRules;

    public BonusCalculatorImpl(List<BonusRule> bonusRules) {
        this.bonusRules = bonusRules;
    }

    public double calculateBonus(Broker broker) {
        double multiplier = bonusRules.stream()
                .filter(bonusRule -> broker.profit() >= bonusRule.minimum())
                .mapToDouble(BonusRule::multiplier)
                .max()
                .orElse(0);
        return broker.profit() * multiplier;
    }
}
