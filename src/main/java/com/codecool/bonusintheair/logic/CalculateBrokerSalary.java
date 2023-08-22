package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.Broker;

public class CalculateBrokerSalary {
    private final BonusCalculator bonusCalculator;

    public CalculateBrokerSalary(BonusCalculator bonusCalculator) {
        this.bonusCalculator = bonusCalculator;
    }

    public double calculateSalary(Broker broker) {
        return broker.baseSalary() + bonusCalculator.calculateBonus(broker);
    }
}