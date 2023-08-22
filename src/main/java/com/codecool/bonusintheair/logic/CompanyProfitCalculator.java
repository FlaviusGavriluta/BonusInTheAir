package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import com.codecool.bonusintheair.data.CompanyProfit;

import java.util.List;

public class CompanyProfitCalculator implements ProfitCalculator {
    private final List<BonusRule> bonusRules;
    private final CalculateBrokerSalary calculateBrokerSalary;

    public CompanyProfitCalculator(List<BonusRule> bonusRules) {
        this.bonusRules = bonusRules;
        this.calculateBrokerSalary = new CalculateBrokerSalary(new BonusCalculatorImpl(bonusRules));
    }

    public CompanyProfit calculate(List<Broker> brokers) {
        double totalProfit = brokers.stream().
                mapToDouble(Broker::profit).
                sum();
        double baseSalaries = brokers.stream().
                mapToDouble(calculateBrokerSalary::calculateSalary).
                sum();

        double remainingProfit = totalProfit - baseSalaries;
        return new CompanyProfit(totalProfit, baseSalaries, remainingProfit);
    }
}