package org.bastien.addon.gui.custom_components.time_slider;

import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class TimeSliderView extends JPanel {

    private final int WIDTH_LABEL = getFontMetrics(Styles.fontPlain8).stringWidth("00:00");
    private static final int KNOB_HEIGHT = 6;
    private static final int TIME_BETWEEN_TICKS = 30;

    private final Function<Double, Integer> fun;
    boolean isKnobsMoving = false;
    private String[] labels;
    private int lowerValuePx, upperValuePx, lowerValueModel, upperValueModel;

    public TimeSliderView() {
        this.fun = perc -> Math.toIntExact(Math.round((getWidth() > 0 ? getWidth() : 774) * perc));
        setBackground(Styles.background);
        setPreferredSize(new Dimension(WIDTH_LABEL, 80));
    }

    public int getLowerValuePx() {
        return lowerValuePx;
    }

    public void setLowerValuePx(double perc, int lowerValueModel) {
        this.lowerValuePx = fun.apply(perc);
        this.lowerValueModel = lowerValueModel;
        repaint();
    }

    public int getUpperValuePx() {
        return upperValuePx;
    }

    public void setUpperValuePx(double perc, int upperValueModel) {
        this.upperValuePx = fun.apply(perc);
        this.upperValueModel = upperValueModel;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLabels(g);
        paintTimeline(g);
        paintKnobs(g);
        g.dispose();
    }

    public void setLabels(int nTicks) {
        this.labels = new String[nTicks];
    }

    public void refreshLabels() {
        for (int i = 0; i < labels.length; i++) {
            labels[i] = convertSecondsToMinutesAndSeconds(i * TIME_BETWEEN_TICKS);
        }
    }

    private void paintLabels(Graphics g) {
        g.setFont(Styles.fontPlain8);
        g.setColor(Styles.white20);
        for (int i = 0; i < labels.length; i++) {
            g.drawString(labels[i], i * (computeDistanceBetweenTicks() + WIDTH_LABEL), (getHeight() / 2) - 5);
        }
    }

    private void paintTimeline(Graphics g) {
        int y = (getHeight() / 2);
        g.drawLine(0, y, getWidth(), y);
    }

    private void paintKnobs(Graphics g) {
        g.setColor(isKnobsMoving ? Styles.primary_light : Styles.primary);
        final int midHeight = getHeight() / 2;
        g.fillRect(lowerValuePx, midHeight, upperValuePx - lowerValuePx, KNOB_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(convertSecondsToMinutesAndSeconds(lowerValueModel), lowerValuePx, 10 + WIDTH_LABEL);
        g.drawString(convertSecondsToMinutesAndSeconds(upperValueModel), upperValuePx - WIDTH_LABEL, 10 + WIDTH_LABEL);
    }

    private int computeDistanceBetweenTicks() {
        int num = getWidth() - (labels.length * WIDTH_LABEL);
        int den = labels.length - 1;
        return num / den;
    }

    private String convertSecondsToMinutesAndSeconds(int seconds) {
        return String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60);
    }
}