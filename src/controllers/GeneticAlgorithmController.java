package controllers;

import models.genetic.GeneticAlgorithm;
import views.StartForm;
import models.route.Path;

/**
 * Created by Андрей
 */
public class GeneticAlgorithmController {
    private GeneticAlgorithm geneticAlgorithm;
    private StartForm startForm;

    public GeneticAlgorithmController(GeneticAlgorithm geneticAlgorithm, StartForm startForm) {
        this.geneticAlgorithm = geneticAlgorithm;
        this.startForm = startForm;
    }

    public void control(){
        startForm.getStartButton().addActionListener(e -> controlGeneticAlgorithm());
    }

    private void controlGeneticAlgorithm(){
        try{
            geneticAlgorithm = new GeneticAlgorithm();
            geneticAlgorithm.initGeneticParameters(startForm.getPopulationSize(), startForm.getNumberOfGenerations(),
                    startForm.getMutationProbability());
            geneticAlgorithm.start();
        } catch (Exception e){
            e.printStackTrace();
        }
        startForm.getDrawingArea().showPath(Path.getBestPath(), Path.getStartVertex(), Path.getFinishVertex());
        startForm.showAnswerForm(Path.getStringPath(), Path.getBestLength());
    }
}
