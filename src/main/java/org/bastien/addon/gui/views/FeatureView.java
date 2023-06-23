package org.bastien.addon.gui.views;

import org.bastien.addon.gui.components.FeatureCheckComponent;

import javax.swing.*;
import java.awt.*;

public class FeatureView extends View {

    public FeatureView(FeatureCheckComponent[] features) {
        super("FeatureView");

        JPanel columnFeaturesPanel = new JPanel();
        columnFeaturesPanel.setOpaque(false);
        columnFeaturesPanel.setLayout(new BoxLayout(columnFeaturesPanel, BoxLayout.Y_AXIS));
        int verticalSpace = 10;

        for (FeatureCheckComponent feature : features) {
            columnFeaturesPanel.add(feature);
            columnFeaturesPanel.add(Box.createRigidArea(new Dimension(0, verticalSpace)));
        }

        JPanel pushNorth = new JPanel(new BorderLayout());
        pushNorth.setOpaque(false);
        JPanel pushWest = new JPanel(new BorderLayout());
        pushWest.setOpaque(false);

        pushWest.add(columnFeaturesPanel, BorderLayout.WEST);
        pushNorth.add(pushWest, BorderLayout.NORTH);
        add(pushNorth);
    }
}
