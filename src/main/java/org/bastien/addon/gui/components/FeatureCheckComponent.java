package org.bastien.addon.gui.components;

import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;

public class FeatureCheckComponent extends JToggleButton {

    private final JLabel status, labelIcon, title;

    private final Border enabledBorder, disabledBorder;

    public FeatureCheckComponent(String text, Icon icon) {
        this.title = new JLabel(text);
        title.setFont(Styles.fontPlain24);
        title.setForeground(Styles.white);

        this.labelIcon = new JLabel(icon);

        this.status = new JLabel();
        status.setFont(Styles.fontBold20);

        this.enabledBorder = BorderFactory.createLineBorder(Styles.primary, 3, true);
        this.disabledBorder = BorderFactory.createLineBorder(Styles.white20, 3, true);

        final JPanel panelAll = new JPanel(new BorderLayout());
        panelAll.setPreferredSize(new Dimension(426, 72));
        panelAll.setFont(Styles.fontPlain24);
        panelAll.setBackground(Styles.white5);
        panelAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final JPanel panelContent = new JPanel(new BorderLayout(20, 0));
        panelContent.setOpaque(false);

        final JPanel panelTexts = new JPanel(new BorderLayout());
        panelTexts.setOpaque(false);

        final JPanel downTexts = new JPanel(new FlowLayout(FlowLayout.LEFT));
        downTexts.setOpaque(false);

        final JLabel config = createTextsLabel("CONFIG", Icons.CONFIG.getIcon());
        final JLabel infos = createTextsLabel("INFOS", Icons.HELP.getIcon());
        downTexts.add(config);
        downTexts.add(infos);

        panelTexts.add(title, BorderLayout.CENTER);
        panelTexts.add(downTexts, BorderLayout.SOUTH);

        panelContent.add(panelTexts, BorderLayout.CENTER);
        panelContent.add(labelIcon, BorderLayout.EAST);

        panelAll.add(panelContent, BorderLayout.WEST);
        panelAll.add(status, BorderLayout.EAST);

        setFocusPainted(false);
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel p1 = new JPanel();
        p1.setOpaque(false);
        p1.add(panelAll);
        add(p1);

        addItemListener(itemEvent -> changeState(itemEvent.getStateChange() == ItemEvent.SELECTED));
        changeState(false);
    }

    private void changeState(boolean state) {
        setBorder(state ? enabledBorder : disabledBorder);
        status.setText(state ? "ENABLED" : "DISABLED");
        status.setForeground(state ? Styles.primary : Styles.white20);
        labelIcon.setEnabled(state);
        title.setEnabled(state);
    }

    private JLabel createTextsLabel(String text, Icon icon) {
        final JLabel infos = new JLabel(text);
        infos.setIcon(icon);
        infos.setFont(Styles.fontPlain12);
        infos.setForeground(Styles.white80);
        return infos;
    }
}
