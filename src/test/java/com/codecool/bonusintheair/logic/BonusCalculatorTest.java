package com.codecool.bonusintheair.logic;

import com.codecool.bonusintheair.data.BonusRule;
import com.codecool.bonusintheair.data.Broker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BonusCalculatorTest {
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
        Broker borker = new Broker("John Doe", 1200, 4500);
        assertEquals(0, bonusCalculator.calculateBonus(borker));
    }

    @Test
    void lowBonus(){
        Broker borker = new Broker("John Doe", 1200, 5500);
        assertEquals(550, bonusCalculator.calculateBonus(borker));
    }

    @Test
    void highBonus(){
        Broker borker = new Broker("John Doe", 1200, 30000);
        assertEquals(9000, bonusCalculator.calculateBonus(borker));
    }
}