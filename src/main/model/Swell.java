package model;

// Represents a swell, with direction, min/max height, and period
public class Swell {
    private int oneThirdHeight = 0;
    private int oneTenthHeight = 0;
    private int direction = 0;
    private int period = 0;

    public int getOneThirdHeight() {
        return oneThirdHeight;
    }

    public int getOneTenthHeight() {
        return oneTenthHeight;
    }

    public int getDirection() {
        return direction;
    }

    public int getPeriod() {
        return period;
    }

    // EFFECTS: Constructs a Swell with no values
    public Swell() {}

    public void updateSwell(Integer direction, Integer period, Integer oneThirdHeight, Integer oneTenthHeight) {
        this.direction = direction;
        this.period = period;
        this.oneThirdHeight = oneThirdHeight;
        this.oneTenthHeight = oneTenthHeight;
    }
}
