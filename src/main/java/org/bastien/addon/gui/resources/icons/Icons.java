package org.bastien.addon.gui.resources.icons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Getter
@RequiredArgsConstructor
public enum Icons {
    FEATURE("flash_on", true),
    CHARTS("bar_chart_4_bars", true),
    TIMELINE("bar_chart_4_bars", true),
    LOGS("account_tree", true),
    FOLDER("bookmark_manager", true),
    LIVE("launcher_assistant_on", true),
    CONFIG("tune", true),
    HELP("help", true),
    STATIC_BARRIER("electricshield", false),
    SHIELD("energyshield", false),
    KOLTO_SHELL("koltoshell", false);

    private final Icon icon, selectedIcon;

    Icons(String filename, boolean isApp) {
        this.icon = loadIcon(filename, isApp);
        this.selectedIcon = isApp ? loadIcon(filename + "_selected", true) : null;
    }

    private static Icon loadIcon(String filename, boolean isApp) {
        try {
            File svgFile = new File("src/main/resources/icons/" + (isApp ? "app" : "abilities") + "/" + filename + ".png");
            BufferedImage image = ImageIO.read(svgFile);
            return new ImageIcon(image);
        } catch (Exception e) {
            System.err.println("Error while loading the icon -> " + filename);
            e.printStackTrace();
            return null;
        }
    }
}
