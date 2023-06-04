package org.bastien.addon.gui;

import org.bastien.addon.gui.components.FeatureComponent;
import org.bastien.addon.gui.components.FooterComponent;
import org.bastien.addon.gui.components.HeaderComponent;
import org.bastien.addon.gui.resources.styles.Styles;

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
        final HeaderComponent header = new HeaderComponent();
        final FooterComponent footer = new FooterComponent();
        final FeatureComponent content = new FeatureComponent();
        getContentPane().setBackground(Styles.background);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(footer, BorderLayout.SOUTH);
        getContentPane().add(content, BorderLayout.CENTER);
        setVisible(true);
    }
}
