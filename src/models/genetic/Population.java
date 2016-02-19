package models.genetic;

import models.route.Path;

import java.util.*;

/**
 * Created by Андрей
 */

public class Population {
    private ArrayList<Chromosome> currentPopulation;
    private int populationSize;
    private final Random random = new Random();
    private ArrayList<Integer> randomIndexes;
    private ArrayList<Chromosome> selectedChromosomes;

    public Population(int populationSize){
        this.populationSize = populationSize;
        randomIndexes = new ArrayList<>();
        currentPopulation = new ArrayList<>();
        selectedChromosomes = new ArrayList<>();
    }

    public Chromosome defineBestChromosome(){
        Chromosome bestChromosome = currentPopulation.get(0);
        for (Chromosome chromosome : currentPopulation){
            if (chromosome.getLength()  < bestChromosome.getLength())
                bestChromosome = chromosome;
        }

        return bestChromosome;
    }

    void initializeStartPopulation(){
        for (int i = 0; i < populationSize; i++){
            Chromosome chromosome = new Chromosome();
            chromosome.randomInitialize();
            currentPopulation.add(chromosome);
        }
    }

    void selection(){
        currentPopulation.sort((o1, o2) -> {
            if (o1.getLength() < o2.getLength())
                return -1;
            else if (o1.getLength() > o2.getLength())
                return 1;
            return 0;
        });
        for (int i = 0; i < populationSize / 2 ; i++)
            selectedChromosomes.add(currentPopulation.get(i));

        currentPopulation.clear();
        currentPopulation.addAll(selectedChromosomes);
        selectedChromosomes.clear();
    }

    // Скрещиваем хромосомы, каждый раз случайно выбирая точку скрещивания
    void crossing(){
        int crossingPoint;
        // В качестве точки скрещивания подходит любая позиция в хромосоме, исключая начальную и конечную
        do{
            crossingPoint = random.nextInt(Path.getNumberOfVertices() - 2);
        } while (crossingPoint == 0 || crossingPoint == Path.getNumberOfVertices() - 3);

        Chromosome firstChild = new Chromosome();
        Chromosome secondChild = new Chromosome();
        Chromosome firstParent;
        Chromosome secondParent;

        this.shuffleIndexes(populationSize / 2);

        for (int i = 0; i < populationSize / 2 - 1; i+=2){
            firstParent = currentPopulation.get(randomIndexes.get(i));
            secondParent = currentPopulation.get(randomIndexes.get(i+1));
            firstChild.setGenes(getChildGenes(firstParent, secondParent, crossingPoint));
            firstChild.calculateLength();
            currentPopulation.add(firstChild);

            firstParent = currentPopulation.get(randomIndexes.get(i+1));
            secondParent = currentPopulation.get(randomIndexes.get(i));
            secondChild.setGenes(getChildGenes(firstParent, secondParent, crossingPoint));
            secondChild.calculateLength();
            currentPopulation.add(secondChild);
        }
    }

    // Выбираем хромосомы для мутации, лучшая хромосома никогда не мутирует!
    void mutation(int probability){
        int randomNumber;
        Chromosome best = defineBestChromosome();
        for (Chromosome chromosome : currentPopulation){
            randomNumber = random.nextInt(100);
            if (randomNumber < probability && chromosome != best){
                chromosome.mutateChromosome();
                chromosome.calculateLength();
            }
        }
    }

    private ArrayList<Integer> getChildGenes(Chromosome firstParent, Chromosome secondParent, int crossingPoint){
        ArrayList<Integer> newGenes = new ArrayList<>();
        for (int j = 0; j < crossingPoint; j++)
            newGenes.add(firstParent.getGenes().get(j));

        for (int j = crossingPoint; j < Path.getNumberOfVertices() - 2; j++)
            newGenes.add(secondParent.getGenes().get(j));

        return newGenes;
    }

    // Для генерации рандомных пар
    private void shuffleIndexes(final int size){
        randomIndexes.clear();
        int randomIndex;
        for (int i = 0; i < size; i++){
            do{
                randomIndex = random.nextInt(size);
            } while (randomIndexes.contains(randomIndex));

            randomIndexes.add(randomIndex);
        }
    }

}
