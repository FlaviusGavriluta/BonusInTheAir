package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CalculateBrokerSalaryTest {
    private BonusCalculator bonusCalculatorMock;

    @BeforeEach
    void setUp() {
        bonusCalculatorMock = Mockito.mock(BonusCalculator.class);
    }

    @Test
    void calculateSalaryWithoutBonus() {
        // Arrange
        when(bonusCalculatorMock.calculateBonus(any(Broker.class))).thenReturn(0.0);
        CalculateBrokerSalary calculator = new CalculateBrokerSalary(bonusCalculatorMock);
        Broker broker = new Broker("John Doe", 1200, 0);

        // Assert
        assertEquals(1200, calculator.calculateSalary(broker));
    }

    @Test
    void calculateSalaryWithBonus() {
        // Arrange
        when(bonusCalculatorMock.calculateBonus(any(Broker.class))).thenReturn(1000.0);
        CalculateBrokerSalary calculator = new CalculateBrokerSalary(bonusCalculatorMock);
        Broker broker = new Broker("John Doe", 1200, 45000);

        // Assert
        assertEquals(2200, calculator.calculateSalary(broker));
    }

    @Test
    void calculateSalary() {
        // Arrange
        Broker broker = new Broker("John Snow", 2500, 15000);
        List<BonusRule> bonusRules = List.of(new BonusRule(10000, .1));
        BonusCalculator bonusCalculator = new BonusCalculatorImpl(bonusRules);
        CalculateBrokerSalary calculator = new CalculateBrokerSalary(bonusCalculator);

        // Act
        double brokerSalary = calculator.calculateSalary(broker);

        // Assert
        assertEquals(4000, brokerSalary);

    }
}