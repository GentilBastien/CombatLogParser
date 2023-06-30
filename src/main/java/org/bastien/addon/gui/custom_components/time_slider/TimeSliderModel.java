package org.bastien.addon.gui.custom_components.time_slider;

import java.util.function.Function;

public class TimeSliderModel {

    private final int duration, nTicks;
    private final Function<Double, Integer> fun;
    private int lowerValue, upperValue;

    public TimeSliderModel(int duration) {
        this.duration = duration;
        this.nTicks = (int) Math.round(duration * 1.0 / 30) + 1;
        this.lowerValue = 0;
        this.upperValue = Integer.MAX_VALUE;
        this.fun = perc -> Math.toIntExact(Math.round(duration * perc));
    }

    public boolean setUpperValue(double perc) {
        int upperValueTmp = fun.apply(perc);
        if (upperValueTmp > lowerValue + 10 && upperValueTmp <= duration) {
            this.upperValue = upperValueTmp;
            return true;
        }
        return false;
    }

    public boolean setLowerValue(double perc) {
        int lowerValueTmp = fun.apply(perc);
        if (lowerValueTmp >= 0 && lowerValueTmp < upperValue - 10) {
            this.lowerValue = lowerValueTmp;
            return true;
        }
        return false;
    }

    public int getLowerValue() {
        return lowerValue;
    }

    public double getLowerValuePerc() {
        return lowerValue * 1.0 / duration;
    }

    public int getUpperValue() {
        return upperValue;
    }

    public double getUpperValuePerc() {
        return upperValue * 1.0 / duration;
    }

    public int getNTicks() {
        return nTicks;
    }
}