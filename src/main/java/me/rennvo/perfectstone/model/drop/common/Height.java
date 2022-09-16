package me.rennvo.perfectstone.model.drop.common;

public class Height {

    private final int minHeight, maxHeight;

    public Height(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public boolean isValid(int y) {
        return y >= minHeight && y <= maxHeight;
    }

    @Override
    public String toString() {
        return "Height: " + this.minHeight + "-" + this.maxHeight;
    }
}
