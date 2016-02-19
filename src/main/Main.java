package main;

import controllers.ButtonsEnabledController;
import controllers.GeneticAlgorithmController;
import controllers.RouteController;
import controllers.SlidersChangedController;
import views.StartForm;
import models.genetic.GeneticAlgorithm;
import javax.swing.*;

/**
 * Created by Андрей
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartForm startForm = new StartForm();
            startForm.setVisible(true);
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

            RouteController routeController = new RouteController(startForm);
            routeController.control();
            GeneticAlgorithmController geneticAlgorithmController
                    = new GeneticAlgorithmController(geneticAlgorithm, startForm);
            geneticAlgorithmController.control();
            ButtonsEnabledController buttonsEnabledController = new ButtonsEnabledController(startForm);
            buttonsEnabledController.control();
            SlidersChangedController slidersChangedController = new SlidersChangedController(startForm);
            slidersChangedController.control();
        });
    }
}
