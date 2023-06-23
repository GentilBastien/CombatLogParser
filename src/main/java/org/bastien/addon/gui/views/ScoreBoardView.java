package org.bastien.addon.gui.views;

import org.bastien.addon.gui.components.InstanceTrackerGroupComponent;
import org.bastien.addon.gui.components.TimeSliderComponent;
import org.bastien.addon.gui.components.TimeSliderComponent2;

import java.awt.*;

public class ScoreBoardView extends View {

    public ScoreBoardView(InstanceTrackerGroupComponent instanceTrackerGroupComponent) {
        super("ScoreBoardView");
        setLayout(new BorderLayout());

        final TimeSliderComponent timeSliderComponent2 = new TimeSliderComponent(120);
        add(instanceTrackerGroupComponent, BorderLayout.WEST);
        add(timeSliderComponent2, BorderLayout.CENTER);
    }
}
