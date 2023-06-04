package org.bastien.addon.gui.presenters;

import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;

public class FeatureCheck extends JPanel {
    public FeatureCheck(String text, Icon icon) {
        super(new BorderLayout());
        setFont(Styles.fontPlain24);
        setBackground(Styles.white5);
        setPreferredSize(new Dimension(426, 72));
        setBorder(BorderFactory.createLineBorder(Styles.primary, 3, true));

        JPanel panelContent = new JPanel(new BorderLayout(20, 0));
        panelContent.setOpaque(false);

        JPanel panelTexts = new JPanel();
        panelTexts.setOpaque(false);
        panelTexts.setLayout(new BorderLayout());

        JLabel title = new JLabel(text);
        title.setFont(Styles.fontPlain24);
        title.setForeground(Styles.white);

        JPanel downTexts = new JPanel();
        downTexts.setLayout(new FlowLayout(FlowLayout.LEFT));
        downTexts.setOpaque(false);
        JLabel config = createTextsLabel("CONFIG", Icons.CONFIG.getIcon());
        JLabel infos = createTextsLabel("INFOS", Icons.HELP.getIcon());
        downTexts.add(config);
        downTexts.add(infos);

        panelTexts.add(title, BorderLayout.CENTER);
        panelTexts.add(downTexts, BorderLayout.SOUTH);

        panelContent.add(panelTexts, BorderLayout.CENTER);
        panelContent.add(new JLabel(icon), BorderLayout.EAST);

        add(panelContent, BorderLayout.WEST);
        JLabel status = new JLabel("ENABLED");
        status.setFont(Styles.fontBold20);
        status.setForeground(Styles.primary);
        add(status, BorderLayout.EAST);
    }

    private JLabel createTextsLabel(String text, Icon icon) {
        final JLabel infos = new JLabel(text);
        infos.setIcon(icon);
        infos.setFont(Styles.fontPlain12);
        infos.setForeground(Styles.white80);
        return infos;
    }
}
