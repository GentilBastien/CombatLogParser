package org.bastien.addon.gui.components;


import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class FooterComponent extends JPanel {

    private final JFileChooser fileChooser;

    public FooterComponent() {
        final BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setBackground(Styles.background);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Styles.white20));

        final JLabel label = new JLabel("Combat log directory");
        label.setFont(Styles.fontPlain12);
        label.setForeground(Styles.white);

        final JButton directoryButton = new JButton("D:\\Users\\Documents\\Star Wars - The Old Republic\\CombatLogs");
        directoryButton.setFont(Styles.fontPlain10);
        directoryButton.setForeground(Styles.white20);
        directoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        directoryButton.setFocusPainted(false);
        directoryButton.setContentAreaFilled(false);
        directoryButton.setIcon(Icons.FOLDER.getIcon());
        directoryButton.setIconTextGap(15);
        directoryButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Styles.white20, 1, true),
                BorderFactory.createEmptyBorder(1, 5, 1, 5)
        ));

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Set CombatLogs directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        final ActionListener listenToCombatLogDirectoryChanges = action -> {
            int returnVal = fileChooser.showOpenDialog(FooterComponent.this.getTopLevelAncestor());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path directory = fileChooser.getSelectedFile().toPath();
                directoryButton.setText(directory.toString());
                System.out.println(directoryButton);
            }
        };
        directoryButton.addActionListener(listenToCombatLogDirectoryChanges);

        add(label);
        add(directoryButton);
    }
}
