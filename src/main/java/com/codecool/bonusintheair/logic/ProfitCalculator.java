package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.Broker;
import com.codecool.bonusintheair.data.CompanyProfit;

import java.util.List;

public interface ProfitCalculator {
    CompanyProfit calculate(List<Broker> brokers);
}