package models.genetic;

import models.route.Path;

public class GeneticAlgorithm {
    private int numberOfGenerations;
    private int mutationProbability;
    private Population population;

    public void initGeneticParameters(final int populationSize, int numberOfGenerations, int mutationProbability){
        this.numberOfGenerations = numberOfGenerations;
        this.mutationProbability = mutationProbability;
        population = new Population(populationSize);
        population.initializeStartPopulation();
    }

    public void start(){
        Chromosome best;
        for (int i = 0; i < numberOfGenerations; i++){
            population.selection();
            population.crossing();
            population.mutation(mutationProbability);
        }
        best = population.defineBestChromosome();

        Path.setBestLength(best.getLength());
        Path.setBestPath(best.getGenes());
    }

}
