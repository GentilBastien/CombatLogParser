package org.bastien.addon.gui.components;

import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RunningComponent extends JPanel {

    private final Icons[] runningAbilities = new Icons[]{
            Icons.KOLTO_SHELL,
            Icons.SHIELD,
            Icons.SHIELD,
            Icons.STATIC_BARRIER,
            Icons.KOLTO_SHELL,
    };

    public RunningComponent() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 3, 0);
        setLayout(layout);
        final TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Styles.primary, 2, true), "Running", TitledBorder.RIGHT, TitledBorder.TOP, Styles.fontPlain10, Styles.primary
        );
        setBackground(Styles.background);
        setBorder(border);

        for (Icons runningAbility : runningAbilities) {
            add(new JLabel(runningAbility.getIcon()));
        }

        JLabel runningIcon = new JLabel(Icons.LIVE.getSelectedIcon());
        add(runningIcon);
    }
}
