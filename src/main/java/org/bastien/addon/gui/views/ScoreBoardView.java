package org.bastien.addon.gui.views;

import org.bastien.addon.gui.components.InstanceTrackerGroupComponent;

import java.awt.*;

public class ScoreBoardView extends View {
    public ScoreBoardView() {
        super("ScoreBoardView");
        setLayout(new BorderLayout());

        final InstanceTrackerGroupComponent instanceTrackerGroupComponent = new InstanceTrackerGroupComponent();


        add(instanceTrackerGroupComponent, BorderLayout.WEST);
    }
}
