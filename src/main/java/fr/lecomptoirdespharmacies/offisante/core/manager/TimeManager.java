package fr.lecomptoirdespharmacies.offisante.core.manager;

import java.util.concurrent.TimeUnit;

public class TimeManager {
    private int seconds;

    private int multiplier;

    private int initialValue;

    public TimeManager(int seconds, int multiplier) {
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

    public void sleep(){
        try {
            TimeUnit.SECONDS.sleep(getSeconds());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        incrementMultiplier();
    }
}
