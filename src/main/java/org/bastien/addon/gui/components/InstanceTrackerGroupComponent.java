package org.bastien.addon.gui.components;

import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;

public class InstanceTrackerGroupComponent extends JPanel {

    public InstanceTrackerGroupComponent(InstanceTrackerRadioComponent[] instances) {
        super(new BorderLayout());
        setOpaque(false);

        final JPanel columnInstancesPanel = new JPanel();
        final BoxLayout boxLayout = new BoxLayout(columnInstancesPanel, BoxLayout.Y_AXIS);
        columnInstancesPanel.setLayout(boxLayout);
        columnInstancesPanel.setOpaque(false);

        final ButtonGroup buttonGroup = new ButtonGroup();
        int verticalSpace = 10;
        for (InstanceTrackerRadioComponent instanceTrackerRadioComponent : instances) {
            instanceTrackerRadioComponent.addItemListener(itemEvent -> {
                boolean isSelected = instanceTrackerRadioComponent.isSelected();
                instanceTrackerRadioComponent.setState(isSelected);
            });
            buttonGroup.add(instanceTrackerRadioComponent);
            columnInstancesPanel.add(instanceTrackerRadioComponent);
            columnInstancesPanel.add(Box.createRigidArea(new Dimension(0, verticalSpace)));
        }

        JPanel pushUp = new JPanel(new BorderLayout());
        pushUp.setOpaque(false);
        pushUp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Styles.white20));
        pushUp.add(columnInstancesPanel, BorderLayout.NORTH);
        add(pushUp, BorderLayout.WEST);
    }
}
