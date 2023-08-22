package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import com.codecool.bonusintheair.data.CompanyProfit;

import java.util.List;

public class CompanyProfitCalculator {
    private final List<BonusRule> bonusRules;

    public CompanyProfitCalculator(List<BonusRule> bonusRules) {
        this.bonusRules = bonusRules;
    }

    public CompanyProfit calculate(List<Broker> brokers) {
        int j = 0;
        int i = brokers.size();
        double totalProfit = 0;
        double baseSalaries = 0;
        double remaningProfit = 0;
        while (i > 0) {
            Broker b = brokers.get(brokers.size() - i);
            totalProfit += b.profit();
            double multiplier = 0;
            j = bonusRules.size();
            while (j > 0) {
                BonusRule bo = bonusRules.get(bonusRules.size() - j);
                if (b.profit() >= bo.minimum()) {
                    multiplier = bo.multiplier();
                }
                j--;
            }
            baseSalaries += b.baseSalary() + (b.profit() * multiplier);
            i--;
        }
        remaningProfit = totalProfit - baseSalaries;
        return new CompanyProfit(totalProfit, baseSalaries, remaningProfit);
    }
}