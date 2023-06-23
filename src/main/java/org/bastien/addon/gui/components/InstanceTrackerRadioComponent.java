package org.bastien.addon.gui.components;

import org.bastien.addon.gui.models.Outcome;
import org.bastien.addon.gui.resources.styles.Styles;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InstanceTrackerRadioComponent extends JRadioButton {

    private final Outcome outcome;
    private final Border enabledBorder, disabledBorder;

    public InstanceTrackerRadioComponent(String areaName, String timestamp, Outcome outcome) {
        final Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.enabledBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Styles.primary, 2, true), paddingBorder);
        this.disabledBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Styles.white20, 2, true), paddingBorder);
        this.outcome = outcome;

        setLayout(new BorderLayout());
        setOpaque(false);
        setBackground(Styles.white5);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setState(false);

        final JPanel texts = new JPanel(new BorderLayout());
        texts.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        texts.setOpaque(false);

        final JLabel labelArea = buildTextLabel(areaName, Styles.white);
        final JLabel labelTimestamp = buildTextLabel(timestamp, Styles.white80);
        final JLabel status = buildStatusLabel();

        texts.add(labelArea, BorderLayout.NORTH);
        texts.add(Box.createRigidArea(new Dimension(0, 8)), BorderLayout.CENTER);
        texts.add(labelTimestamp, BorderLayout.SOUTH);
        add(texts, BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.CENTER);
        add(status, BorderLayout.EAST);
    }

    public void setState(boolean isSelected) {
        setBorder(isSelected ? enabledBorder : disabledBorder);
        repaint();
    }

    private JLabel buildTextLabel(String text, Color fg) {
        final JLabel label = new JLabel();
        label.setText(text);
        label.setFont(Styles.fontPlain10);
        label.setForeground(fg);
        return label;
    }

    private JLabel buildStatusLabel() {
        JLabel label = new JLabel();
        label.setText(outcome.name());
        label.setFont(Styles.fontPlain12);
        switch (outcome) {
            case WIN -> label.setForeground(Styles.green);
            case DEFEAT -> label.setForeground(Styles.red);
            case ONGOING -> label.setForeground(Styles.orange);
        }
        return label;
    }

    @Override
    protected void paintComponent(@NotNull Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
