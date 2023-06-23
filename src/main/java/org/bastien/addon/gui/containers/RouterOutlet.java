package org.bastien.addon.gui.containers;

import org.bastien.addon.gui.views.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RouterOutlet extends JPanel {

    private final List<View> views;
    private final CardLayout cardLayout;

    public RouterOutlet() {
        setOpaque(false);
        this.views = new ArrayList<>();
        this.cardLayout = new CardLayout();
        setLayout(cardLayout);
    }

    public void addView(View view) {
        views.add(view);
        add(view, view.getName());
    }

    public void show(int i) {
        final View view = views.get(i);
        cardLayout.show(this, view.getName());
    }
}
