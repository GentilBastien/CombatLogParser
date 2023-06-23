package org.bastien.addon.gui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimeSliderComponent2 extends JPanel {

    private final JSlider slider = new JSlider();
    private final BoundedRangeModel model;
    private float scaleX;
    private int minExtent;

    public TimeSliderComponent2() {
        this(0, 100, 0, 10);
    }

    public TimeSliderComponent2(int min, int max, int value, int extent) {
        this(new DefaultBoundedRangeModel(value, extent, min, max));
    }

    public TimeSliderComponent2(BoundedRangeModel model) {
        this.model = model;

        slider.setMinimum(model.getMinimum());
        slider.setMaximum(model.getMaximum());

        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                computeScaleX();
            }
        });
        setBorder(new EmptyBorder(1, 1, 1, 1));
        model.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                fireChangeEvent();
                repaint();
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        slider.setEnabled(enabled);
    }

    public int getValue() {
        return model.getValue();
    }

    public void setValue(int i) {
        i = clamp(i, minExtent);
        // keep second value
        int secondValue = model.getValue() + model.getExtent();
        int extent = secondValue - i;
        if (extent < minExtent) {
            extent = minExtent;
        }
        model.setRangeProperties(i, extent, model.getMinimum(), model.getMaximum(), false);
    }

    private int clamp(int i, int minExtent) {
        int max = model.getMaximum() - minExtent;
        if (i > max) {
            i = max;
        }
        int min = model.getMinimum();
        if (i < min) {
            i = min;
        }
        return i;
    }

    public int getSecondValue() {
        return model.getValue() + model.getExtent();
    }

    public void setSecondValue(int i) {
        i = clamp(i, minExtent);
        int v = model.getValue();
        model.setExtent(Math.max(minExtent, i - v));
    }

    private void fireChangeEvent() {
//        EventListenerListIterator<ChangeListener> iter = new EventListenerListIterator<ChangeListener>(
//                ChangeListener.class, listenerList);
//        ChangeEvent e = new ChangeEvent(this);
//        while (iter.hasNext()) {
//            ChangeListener next = iter.next();
//            next.stateChanged(e);
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        slider.setMinimum(model.getMinimum());
        slider.setMaximum(model.getMaximum());

        slider.setBounds(getBounds());

        slider.setValue(model.getMinimum());
        BasicSliderUI ui = (BasicSliderUI) slider.getUI();
        if (getPaintTrack()) {
            ui.paintTrack(g);
        }

        slider.setValue(getSecondValue());

        Rectangle clip = g.getClipBounds();

        Rectangle r = new Rectangle((int) ((model.getValue() - model.getMinimum()) / scaleX), 0, getWidth(),
                getHeight());
        r = r.intersection(clip);
        g.setClip(r.x, r.y, r.width, r.height);

        slider.paint(g);

        g.setClip(clip.x, clip.y, clip.width, clip.height);

        if (getPaintLabels()) {
            ui.paintLabels(g);
        }
        if (getPaintTicks()) {
            ui.paintTicks(g);
        }

        slider.setValue(getValue());
        ui.paintThumb(g);
    }

    private void computeScaleX() {
        float width = getWidth();
        Insets ins = getInsets();
        width -= ins.left + ins.right;

        int min = model.getMinimum();
        int max = model.getMaximum();

        float size = max - min;
        scaleX = size / width;
    }

    public boolean getPaintLabels() {
        return slider.getPaintLabels();
    }

    public boolean getPaintTrack() {
        return slider.getPaintTrack();
    }

    public boolean getPaintTicks() {
        return slider.getPaintTicks();
    }

    public void setFont(Font font) {
        if (slider != null) {
            slider.setFont(font);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return slider.getPreferredSize();
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        slider.setPreferredSize(preferredSize);
    }

    private final class MouseHandler extends MouseAdapter {
        private int cursorType;
        private int pressX, pressY;
        private int firstValue;
        private int modelExtent;

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!isEnabled()) {
                return;
            }
            int value = model.getValue() - model.getMinimum();
            int secondValue = value + model.getExtent();
            int x = ((int) (e.getX() * scaleX));
            if (Math.abs(x - secondValue) < 3) {
                cursorType = Cursor.E_RESIZE_CURSOR;
            } else if (Math.abs(x - value) < 3) {
                cursorType = Cursor.W_RESIZE_CURSOR;
            } else if (x > value && x < secondValue) {
                cursorType = Cursor.MOVE_CURSOR;
            } else {
                cursorType = Cursor.DEFAULT_CURSOR;
            }
            setCursor(Cursor.getPredefinedCursor(cursorType));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!isEnabled()) {
                return;
            }
            int delta;
            int value;
            switch (cursorType) {
                case Cursor.DEFAULT_CURSOR:
                    break;
                case Cursor.MOVE_CURSOR:
                    delta = Math.round((pressX - e.getX()) * scaleX);
                    value = firstValue - delta;
                    setValue(value);
                    setSecondValue(getValue() + modelExtent);
                    repaint();
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    delta = Math.round((pressX - e.getX()) * scaleX);
                    int extent = modelExtent - delta;
                    if (extent < minExtent)
                        extent = minExtent;
                    int secondValue = firstValue + modelExtent - delta;
                    setValue(secondValue - extent);
                    setSecondValue(secondValue);
                    repaint();
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    delta = Math.round((pressX - e.getX()) * scaleX);
                    value = firstValue - delta;
                    setValue(value);
                    repaint();
                    break;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!isEnabled()) {
                return;
            }
            pressX = e.getX();
            pressY = e.getY();
            firstValue = model.getValue();
            modelExtent = model.getExtent();
        }
    }
}