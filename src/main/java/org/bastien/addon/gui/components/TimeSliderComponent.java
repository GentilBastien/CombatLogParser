package org.bastien.addon.gui.components;

import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;

public class TimeSliderComponent extends JPanel {

    final int widthLabel = getFontMetrics(Styles.fontPlain8).charWidth('0') * 5;
    final int widthBlank = 40;

    private final long duration;
    private final long lowerValue;
    private final long upperValue;

    private String[] labels;

    public TimeSliderComponent(long duration) {
        this.duration = duration;
        this.lowerValue = 0;
        this.upperValue = duration;
        this.labels = new String[0];

        setBackground(Styles.background);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        scaleLabels();
        computeLabels();
        paintLabels(g);
        paintTimeline(g);
        g.dispose();
    }

    private void scaleLabels() {
        final int totalWidth = getWidth() - widthLabel - 2 * widthBlank - 40;
        int nLabels = Math.ceilDiv(totalWidth + widthBlank, widthLabel + widthBlank);
        this.labels = new String[nLabels];
    }

    private void computeLabels() {
        double exactRange = duration * 1.0 / (labels.length - 1);
        long range = Math.round(exactRange);
        for (int i = 0; i < labels.length; i++) {
            labels[i] = convertSecondsToMinutesAndSeconds(i * range);
        }
    }

    private String convertSecondsToMinutesAndSeconds(long seconds) {
        return String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60);
    }

    private void paintLabels(Graphics g) {
        g.setFont(Styles.fontPlain8);
        g.setColor(Styles.white20);
        for (int i = 0; i < labels.length; i++) {
            g.drawString(labels[i], i * widthLabel + (i + 1) * widthBlank, getHeight() / 2);
        }
    }

    private void paintTimeline(Graphics g) {
        int y = (getHeight() / 2) + 5;
        g.drawLine(20, y, getWidth() - 20, y);
    }

    private void paintLowerKnob(Graphics g) {

    }

    private void paintUpperKnob(Graphics g) {

    }
}