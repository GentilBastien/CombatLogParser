package org.bastien.addon.gui.components;

import org.bastien.addon.gui.containers.RouterOutlet;
import org.bastien.addon.gui.resources.icons.Icons;
import org.bastien.addon.gui.resources.styles.Styles;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class HeaderComponent extends JPanel {
    private final ButtonGroup buttonGroup;
    private final MainMenuRadioComponent[] radios = new MainMenuRadioComponent[]{
            new MainMenuRadioComponent("FEATURES", Icons.FEATURE),
            new MainMenuRadioComponent("SCOREBOARD", Icons.CHARTS),
            new MainMenuRadioComponent("TIMELINE", Icons.TIMELINE),
            new MainMenuRadioComponent("LOGS_STRUCTURE", Icons.LOGS)
    };

    public HeaderComponent(RouterOutlet routerOutlet) {
        this.buttonGroup = new ButtonGroup();

        setLayout(new BorderLayout());
        setBackground(Styles.background);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Styles.white20));

        final JPanel mainMenuPanel = new JPanel();
        final BoxLayout boxLayout = new BoxLayout(mainMenuPanel, BoxLayout.X_AXIS);
        mainMenuPanel.setLayout(boxLayout);
        mainMenuPanel.setOpaque(false);

        for (MainMenuRadioComponent radio : radios) {
            buttonGroup.add(radio);
            radio.addItemListener(actionEvent -> {
                boolean isSelected = radio.isSelected();
                radio.setState(isSelected);
                if (isSelected) {
                    int selected = getSelectedTab();
                    routerOutlet.show(selected);
                }
            });
            mainMenuPanel.add(radio);
            mainMenuPanel.add(Box.createRigidArea(new Dimension(15,0)));
        }

        buttonGroup.setSelected(radios[0].getModel(), true);

        final RunningComponent runningComponent = new RunningComponent();
        add(mainMenuPanel, BorderLayout.WEST);
        add(runningComponent, BorderLayout.EAST);
    }

    private int getSelectedTab() {
        Iterator<AbstractButton> it = buttonGroup.getElements().asIterator();
        for (int i = 0; it.hasNext(); i++)
            if (it.next().isSelected())
                return i;
        return -1;
    }
}
