package org.bastien.addon.gui.components;

import org.bastien.addon.gui.custom_components.CustomComponent;
import org.bastien.addon.gui.custom_components.time_slider.TimeSliderController;
import org.bastien.addon.gui.custom_components.time_slider.TimeSliderModel;
import org.bastien.addon.gui.custom_components.time_slider.TimeSliderView;

import javax.swing.*;

public class TimeSliderComponent extends CustomComponent {

    private final TimeSliderView view;

    public TimeSliderComponent(int duration) {
        TimeSliderModel model = new TimeSliderModel(duration);
        this.view = new TimeSliderView();
        new TimeSliderController(model, view);
    }

    @Override
    public JComponent getComponent() {
        return view;
    }
}