package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import com.codecool.bonusintheair.data.CompanyProfit;

import java.util.List;

public class CompanyProfitCalculator {
    private final List<BonusRule> bis;

    public CompanyProfitCalculator(List<BonusRule> bis) {
        this.bis = bis;
    }

    public CompanyProfit calculate(List<Broker> brs) {
        int j = 0;
        int i = brs.size();
        double totalProfit = 0;
        double baseSalaries = 0;
        double remaningProfit = 0;
        while (i > 0) {
            Broker b = brs.get(brs.size() - i);
            totalProfit += b.profit();
            double multiplier = 0;
            j = bis.size();
            while (j > 0) {
                BonusRule bo = bis.get(bis.size() - j);
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