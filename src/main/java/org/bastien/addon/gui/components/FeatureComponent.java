package org.bastien.addon.gui.components;

import org.bastien.addon.gui.presenters.FeatureCheck;
import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;

public class FeatureComponent extends JPanel {
    public FeatureComponent() {
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Styles.background);
        FeatureCheck f1 = new FeatureCheck("SHIELD", Icons.SHIELD.getIcon());
        FeatureCheck f2 = new FeatureCheck("KOLTO PROBES", Icons.KOLTO_SHELL.getIcon());
        FeatureCheck f3 = new FeatureCheck("STATIC BARRIER", Icons.STATIC_BARRIER.getIcon());
        add(f1);
        add(f2);
        add(f3);
    }
}
