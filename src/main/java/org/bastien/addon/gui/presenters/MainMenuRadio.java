package org.bastien.addon.gui.presenters;

import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainMenuRadio extends JRadioButton {

    private final Icons icon;

    private final Border border;

    public MainMenuRadio(String text, Icons icon) {
        super(text);
        this.icon = icon;
        this.border = BorderFactory.createEmptyBorder(8, 17, 8, 17);
        setOpaque(false);
        setFont(Styles.fontPlain12);
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setState(false);
        addItemListener(actionEvent -> setState(MainMenuRadio.this.isSelected()));
    }

    private void setState(boolean isSelected) {
        setIcon(isSelected ? icon.getSelectedIcon() : icon.getIcon());
        setBorder(border);
        setBackground(isSelected ? Styles.white5 : Styles.background);
        setForeground(isSelected ? Styles.primary : Styles.white80);
        repaint();
    }

    @Override
    protected void paintComponent(@NotNull Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
