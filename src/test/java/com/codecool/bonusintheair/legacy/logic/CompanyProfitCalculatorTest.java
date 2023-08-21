package com.codecool.bonusintheair.legacy.logic;

import com.codecool.bonusintheair.legacy.data.BonusRule;
import com.codecool.bonusintheair.legacy.data.Broker;
import com.codecool.bonusintheair.legacy.data.CompanyProfit;
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

    @Test
    void noBrokers() {
        // Arrange
        List<Broker> brokers = Collections.emptyList();

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(0, profit.total());
        assertEquals(0, profit.salaries());
        assertEquals(0, profit.remaining());
    }

    @Test
    void brokersWithProfitBelowBonusLimit() {
        // Arrange
        List<Broker> brokers = List.of(
                new Broker("John Doe", 1200, 2400),
                new Broker("Jane Thompson", 1000, 3500),
                new Broker("Jack Stephan", 800, 1400)
        );

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(7300, profit.total());
        assertEquals(3000, profit.salaries());
        assertEquals(4300, profit.remaining());
    }

    @Test
    void brokersWithProfitAboveBonusLimit() {
        // Arrange
        List<Broker> brokers = List.of(
                new Broker("John Doe", 1200, 32000),
                new Broker("Jane Thompson", 1000, 45000),
                new Broker("Jack Stephan", 800, 28000)
        );

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(105000, profit.total());
        assertEquals(34500, profit.salaries());
        assertEquals(70500, profit.remaining());
    }

    @Test
    void singleBroker() {
        // Arrange
        List<Broker> brokers = List.of(
                new Broker("Johnny Deep", 1200, 6000)
        );

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(6000, profit.total());
        assertEquals(1800, profit.salaries());
        assertEquals(4200, profit.remaining());
    }

    @Test
    void allBrokersWithMaximumBonus() {
        // Arrange
        List<Broker> brokers = List.of(
                new Broker("John Doe", 1200, 5500),
                new Broker("Jane Thompson", 1000, 15000),
                new Broker("Jack Stephan", 800, 4500)
        );

        // Act
        CompanyProfit profit = calculator.calculate(brokers);

        // Assert
        assertEquals(25000, profit.total());
        assertEquals(6550, profit.salaries());
        assertEquals(18450, profit.remaining());
    }
}