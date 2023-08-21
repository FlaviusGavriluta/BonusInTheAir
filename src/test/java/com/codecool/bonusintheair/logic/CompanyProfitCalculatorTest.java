package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import com.codecool.bonusintheair.data.CompanyProfit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyProfitCalculatorTest {
    private CompanyProfitCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new CompanyProfitCalculator(List.of(
                new BonusRule(5000, 0.1),
                new BonusRule(10000, 0.15),
                new BonusRule(15000, 0.2),
                new BonusRule(20000, 0.25),
                new BonusRule(25000, 0.3)
        ));
    }

    @Test
    void noBrokersAchievedProfit() {
        // Arrange
        List<Broker> brokers = List.of(
                new Broker("John Doe", 1200, 0),
                new Broker("Jane Thompson", 1000, 0),
                new Broker("Jack Stephan", 800, 0)
        );

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(0, profit.total());
        assertEquals(3000, profit.salaries());
        assertEquals(-3000, profit.remaining());
    }
}