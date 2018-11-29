package fr.lecomptoirdespharmacies.offisante.core.util;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * Simple class to sleep program and manage sleeping time
 */
@Getter
public class TimeUtil {
    private int seconds;

    private int multiplier;

    private int initialValue;

    public TimeUtil(int seconds, int multiplier) {
        this.seconds = seconds;
        this.multiplier = multiplier;
        this.initialValue = multiplier;
    }

    public void resetMultiplier(){
        multiplier = initialValue;
    }

    public void incrementMultiplier(){
        multiplier = multiplier + 1;
    }

    public int getSeconds(){
        return seconds * multiplier;
    }

    @SneakyThrows(InterruptedException.class)
    public void sleep(){
        TimeUnit.SECONDS.sleep(getSeconds());
        incrementMultiplier();
    }
}
