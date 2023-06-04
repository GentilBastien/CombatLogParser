package org.bastien.addon.gui.components;

import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RunningComponent extends JPanel {
    public RunningComponent() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 3, 0);
        setLayout(layout);
        final TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Styles.primary, 2, true), "Running", TitledBorder.RIGHT, TitledBorder.TOP, Styles.fontPlain10, Styles.primary
        );
        setBackground(Styles.background);
        setBorder(border);
        JLabel labelIcon = new JLabel(Icons.KOLTO_SHELL.getIcon());
        JLabel labelIcon2 = new JLabel(Icons.KOLTO_SHELL.getIcon());
        JLabel runningIcon = new JLabel(Icons.LIVE.getSelectedIcon());
        add(labelIcon);
        add(labelIcon2);
        add(runningIcon);
    }
}
