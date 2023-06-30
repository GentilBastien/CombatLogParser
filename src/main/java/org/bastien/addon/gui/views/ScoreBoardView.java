package org.bastien.addon.gui.views;

import org.bastien.addon.gui.components.InstanceTrackerGroupComponent;
import org.bastien.addon.gui.components.TimeSliderComponent;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardView extends View {

    public ScoreBoardView(InstanceTrackerGroupComponent instanceTrackerGroupComponent, TimeSliderComponent timeSliderComponent) {
        super("ScoreBoardView");
        setLayout(new BorderLayout());

        add(instanceTrackerGroupComponent, BorderLayout.WEST);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(timeSliderComponent.getComponent(), BorderLayout.NORTH);
        panel.add(new JPanel(), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}
