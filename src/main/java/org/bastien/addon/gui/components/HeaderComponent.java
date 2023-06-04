package org.bastien.addon.gui.components;

import org.bastien.addon.gui.presenters.MainMenuRadio;
import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;

public class HeaderComponent extends JPanel {

    public HeaderComponent() {
        final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
        setLayout(flowLayout);
        setBackground(Styles.background);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Styles.white20));

        MainMenuRadio radioFeatures = new MainMenuRadio("FEATURES", Icons.FEATURE);
        MainMenuRadio radioCharts = new MainMenuRadio("CHARTS", Icons.CHARTS);
        MainMenuRadio radioTimeline = new MainMenuRadio("TIMELINE", Icons.TIMELINE);
        MainMenuRadio radioLogs = new MainMenuRadio("LOGS_STRUCTURE", Icons.LOGS);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioFeatures);
        buttonGroup.add(radioCharts);
        buttonGroup.add(radioTimeline);
        buttonGroup.add(radioLogs);
        buttonGroup.setSelected(radioFeatures.getModel(), true);

        add(radioFeatures);
        add(radioCharts);
        add(radioTimeline);
        add(radioLogs);

        final RunningComponent runningComponent = new RunningComponent();
        add(runningComponent);
    }
}
