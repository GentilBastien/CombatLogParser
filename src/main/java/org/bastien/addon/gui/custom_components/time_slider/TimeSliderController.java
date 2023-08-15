package org.bastien.addon.gui.custom_components.time_slider;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimeSliderController extends MouseAdapter {
    private final TimeSliderModel model;
    private final TimeSliderView view;
    private int cursorType;

    public TimeSliderController(final TimeSliderModel model, final TimeSliderView view) {
        this.model = model;
        this.view = view;

        model.setLowerValue(0);
        view.setLowerValuePx(0, model.getLowerValue());

        model.setUpperValue(1);
        view.setUpperValuePx(1, model.getUpperValue());

        view.setLabels(model.getNTicks());

        view.addMouseMotionListener(this);
        view.addMouseListener(this);
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // now that the slider has been resized, the knobs must be replaced in the same proportion as before
                view.refreshLabels();
                view.setLowerValuePx(model.getLowerValuePerc(), model.getLowerValue());
                view.setUpperValuePx(model.getUpperValuePerc(), model.getUpperValue());
            }
        });
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Math.abs(view.getLowerValuePx() - e.getX()) < 15) {
            cursorType = Cursor.W_RESIZE_CURSOR;
        } else if (Math.abs(view.getUpperValuePx() - e.getX()) < 15) {
            cursorType = Cursor.E_RESIZE_CURSOR;
        } else {
            cursorType = Cursor.DEFAULT_CURSOR;
        }
        view.setCursor(Cursor.getPredefinedCursor(cursorType));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        view.isKnobsMoving = true;
        double perc = (double) e.getX() / view.getWidth();
        switch (cursorType) {
            case Cursor.W_RESIZE_CURSOR -> {
                if (model.setLowerValue(perc))
                    view.setLowerValuePx(perc, model.getLowerValue());
            }
            case Cursor.E_RESIZE_CURSOR -> {
                if (model.setUpperValue(perc))
                    view.setUpperValuePx(perc, model.getUpperValue());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (view.isKnobsMoving) {
            view.isKnobsMoving = false;
            view.repaint();
        }
    }
}
