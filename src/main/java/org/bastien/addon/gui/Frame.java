package org.bastien.addon.gui;

import org.bastien.addon.gui.components.FooterComponent;
import org.bastien.addon.gui.components.HeaderComponent;
import org.bastien.addon.gui.containers.RouterOutlet;
import org.bastien.addon.gui.resources.styles.Styles;
import org.bastien.addon.gui.views.*;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        setSize(1000, 700);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final View featureView = new FeatureView();
        final View scoreBoardView = new ScoreBoardView();
        final View timelineView = new TimelineView();
        final View logsStructureView = new LogsStructureView();

        final RouterOutlet routerOutlet = new RouterOutlet();
        routerOutlet.addView(featureView);
        routerOutlet.addView(scoreBoardView);
        routerOutlet.addView(timelineView);
        routerOutlet.addView(logsStructureView);
        final HeaderComponent header = new HeaderComponent(routerOutlet);
        final FooterComponent footer = new FooterComponent();

        getContentPane().setBackground(Styles.background);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(footer, BorderLayout.SOUTH);
        getContentPane().add(routerOutlet, BorderLayout.CENTER);
        setVisible(true);
    }
}
