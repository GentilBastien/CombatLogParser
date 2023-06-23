package org.bastien.addon.gui;

import org.bastien.addon.gui.components.*;
import org.bastien.addon.gui.containers.RouterOutlet;
import org.bastien.addon.gui.models.Outcome;
import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;
import org.bastien.addon.gui.views.*;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ////////////////////////////////
        final FeatureCheckComponent[] features = new FeatureCheckComponent[]{
                new FeatureCheckComponent("SHIELD", Icons.SHIELD.getIcon()),
                new FeatureCheckComponent("KOLTO PROBES", Icons.KOLTO_SHELL.getIcon()),
                new FeatureCheckComponent("STATIC BARRIER", Icons.STATIC_BARRIER.getIcon()),
                new FeatureCheckComponent("SHIELD", Icons.SHIELD.getIcon()),
                new FeatureCheckComponent("KOLTO PROBES", Icons.KOLTO_SHELL.getIcon()),
                new FeatureCheckComponent("STATIC BARRIER", Icons.STATIC_BARRIER.getIcon()),
                new FeatureCheckComponent("KOLTO PROBES", Icons.KOLTO_SHELL.getIcon()),
                new FeatureCheckComponent("STATIC BARRIER", Icons.STATIC_BARRIER.getIcon()),
        };
        final InstanceTrackerRadioComponent[] instances = new InstanceTrackerRadioComponent[]{
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.ONGOING),
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.WIN),
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.WIN),
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.DEFEAT),
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.DEFEAT),
                new InstanceTrackerRadioComponent("Tatooine Canyon", "20:02.05-29-2023", Outcome.WIN),
        };
        final InstanceTrackerGroupComponent instanceTrackerGroupComponent = new InstanceTrackerGroupComponent(instances);

        final View featureView = new FeatureView(features);
        final View scoreBoardView = new ScoreBoardView(instanceTrackerGroupComponent);
        final View timelineView = new TimelineView();
        final View logsStructureView = new LogsStructureView();

        final MainMenuRadioComponent[] radios = new MainMenuRadioComponent[]{
                new MainMenuRadioComponent("FEATURES", Icons.FEATURE),
                new MainMenuRadioComponent("SCOREBOARD", Icons.CHARTS),
                new MainMenuRadioComponent("TIMELINE", Icons.TIMELINE),
                new MainMenuRadioComponent("LOGS_STRUCTURE", Icons.LOGS)
        };
        final Icons[] runningAbilities = new Icons[]{
                Icons.KOLTO_SHELL,
                Icons.SHIELD,
                Icons.SHIELD,
                Icons.STATIC_BARRIER,
                Icons.KOLTO_SHELL,
        };
        final RunningComponent runningComponent = new RunningComponent(runningAbilities);
        ////////////////////////////////

        final RouterOutlet routerOutlet = new RouterOutlet();
        routerOutlet.addView(featureView);
        routerOutlet.addView(scoreBoardView);
        routerOutlet.addView(timelineView);
        routerOutlet.addView(logsStructureView);
        final HeaderComponent header = new HeaderComponent(routerOutlet, radios, runningComponent);
        final FooterComponent footer = new FooterComponent();

        getContentPane().setBackground(Styles.background);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(footer, BorderLayout.SOUTH);
        getContentPane().add(routerOutlet, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
