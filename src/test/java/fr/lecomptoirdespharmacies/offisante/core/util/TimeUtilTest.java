package fr.lecomptoirdespharmacies.offisante.core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilTest {

    private TimeUtil timeUtil;

    private final int INITIAL_SECOND = 10;
    private final int INITIAL_MULTIPLIER = 1;

    @BeforeEach
    void setUp() {
        timeUtil = new TimeUtil(INITIAL_SECOND, INITIAL_MULTIPLIER);
    }

    @Test
    void test_multiplier_incrementation() {
        final int expected = 3;

        timeUtil.incrementMultiplier();
        timeUtil.incrementMultiplier();

        assertEquals(expected, timeUtil.getMultiplier());
    }

    @Test
    void test_reset_multiplier() {
        timeUtil.incrementMultiplier();
        timeUtil.incrementMultiplier();
        timeUtil.resetMultiplier();

        assertEquals(INITIAL_MULTIPLIER, timeUtil.getMultiplier());
    }

    @Test
    void test_seconds_computation() {
        final int expected = INITIAL_SECOND * 3;
        timeUtil.incrementMultiplier();
        timeUtil.incrementMultiplier();

        assertEquals(expected, timeUtil.getSeconds());
    }
}