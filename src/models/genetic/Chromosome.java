package models.genetic;

import models.route.Path;

import java.util.*;

/**
 * Created by Андрей
 */

public class Chromosome {

    private ArrayList<Integer> genes;
    private final int numberOfGenes = Path.getNumberOfVertices() - 2;
    private final Random random = new Random();
    // Для каждой храмосомы опеределяем длину пути и по этому показателю сравниваем хромосомы между собой
    private double length;

    public Chromosome(){
        genes = new ArrayList<>();
        length = 0;
    }

    // Случайно генерируем хромосому из диапазона возможных вершин, исключая стартовую и конечную
    public void randomInitialize(){
        int gen;
        for (int i = 0; i < numberOfGenes; i++){
            do{
                gen = random.nextInt(Path.getNumberOfVertices());
            } while (gen == Path.getStartVertex().number || gen == Path.getFinishVertex().number);
            genes.add(gen);
        }
        calculateLength();
    }

    public void calculateLength(){
        length = 0;
        addDistanceLength(Path.getDistanceMatrix()[Path.getStartVertex().number][genes.get(0)]);
        addDistanceLength(Path.getDistanceMatrix()[genes.get(genes.size() - 1)][Path.getFinishVertex().number]);

        for (int i = 0; i < genes.size() - 1; i++)
            addDistanceLength(Path.getDistanceMatrix()[genes.get(i)][genes.get(i+1)]);
    }

    public void setGenes(ArrayList<Integer> genes){
        this.genes = genes;
    }

    public ArrayList<Integer> getGenes(){
        return genes;
    }

    public double getLength(){
        return length;
    }

    // Случайно выбираем ген, который будет мутировать и заменяем его на произвольный, исключая стартовую и конечную вершины
    public void mutateChromosome(){
        int randomGenPosition = random.nextInt(numberOfGenes);
        int randomGenValue;

        do{
            randomGenValue = random.nextInt(Path.getNumberOfVertices());
        } while (randomGenValue == Path.getStartVertex().number ||  randomGenValue == Path.getFinishVertex().number);
        genes.set(randomGenPosition, randomGenValue);
    }

    private void addDistanceLength(double value){
        // Если нет связи между вершинами, добавляем очень большое значение. Если есть - значение из матрицы.
        final int noConnectionValue = 10000;
        length += (value == Double.MAX_VALUE) ?  noConnectionValue : value;
    }
}
