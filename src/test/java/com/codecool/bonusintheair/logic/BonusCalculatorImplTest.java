package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusCalculatorImplTest {
    private BonusCalculatorImpl bonusCalculator;

    @BeforeEach
    void setup() {
        bonusCalculator = new BonusCalculatorImpl(List.of(
                new BonusRule(5000, 0.1),
                new BonusRule(10000, 0.15),
                new BonusRule(15000, 0.2),
                new BonusRule(20000, 0.25),
                new BonusRule(25000, 0.3)
        ));
    }

    @Test
    void noBonus() {
        // Arrange
        Broker broker = new Broker("John Doe", 1200, 4500);

        // Act
        double bonus = bonusCalculator.calculateBonus(broker);

        // Assert
        assertEquals(0, bonusCalculator.calculateBonus(broker));
    }

    @Test
    void lowBonus() {
        // Arrange
        Broker broker = new Broker("John Doe", 1200, 5500);

        // Act
        double bonus = bonusCalculator.calculateBonus(broker);

        // Assert
        assertEquals(550, bonusCalculator.calculateBonus(broker));
    }

    @Test
    void highBonus() {
        // Arrange
        Broker broker = new Broker("John Doe", 1200, 30000);

        // Act
        double bonus = bonusCalculator.calculateBonus(broker);

        // Assert
        assertEquals(9000, bonus);
    }

    @Test
    void limitBonus() {
        // Arrange
        Broker broker = new Broker("Bill Adam", 2500, 5000);

        // Act
        double bonus = bonusCalculator.calculateBonus(broker);

        // Assert
        assertEquals(500, bonus);
    }
}