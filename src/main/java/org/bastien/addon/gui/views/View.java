package org.bastien.addon.gui.views;

import javax.swing.*;

public abstract class View extends JPanel {
    public View(String name) {
        setName(name);
        setOpaque(false);
    }
}
