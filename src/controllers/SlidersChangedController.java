package controllers;

import views.StartForm;

import javax.swing.*;

/**
 * Created by Андрей
 */
public class SlidersChangedController {
    private StartForm startForm;

    public SlidersChangedController(StartForm startForm) {
        this.startForm = startForm;
    }

    public void control(){
        startForm.getGraphConnectionSlider().addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            startForm.getGraphConnectionTextField().setText(Integer.toString(source.getValue()));
        });

        startForm.getPopulationSlider().addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            startForm.getPopulationTextField().setText(Integer.toString(source.getValue() * 4));
        });

        startForm.getGenerationSlider().addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            startForm.getGenerationTextField().setText(Integer.toString(source.getValue() * 2));
        });

        startForm.getMutationSlider().addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            startForm.getMutationTextField().setText(Integer.toString(source.getValue()));
        });
    }

}
