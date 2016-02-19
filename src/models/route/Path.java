package models.route;

import java.util.*;

/**
 * Created by Андрей
 */

public class Path {
    private static ArrayList<Vertex> vertices = new ArrayList<>();
    private static ArrayList<Vertex> bestPath = new ArrayList<>();
    private static double[][] distanceMatrix;
    private static Vertex startVertex;
    private static Vertex finishVertex;
    private static double bestLength;

    public static void calculateDistanceMatrix(final int connectionLevel){
        final Random random = new Random();
        int size = vertices.size();
        System.out.println(size);
        distanceMatrix = new double[size][size];
        int position = 0;

        // Путь из вершины в себя саму равен 0 (случай i = j)
        // Если случайное число больше уровня связности - то связи между вершинами не будет. Признак этого значение Double.Max_VALUE
        // Иначе, рассчитываем расстояние по теореме Пифагора
        for (int i = 0; i < size; i++) {
            for (int j = position; j < size; j++) {
                if (i == j) distanceMatrix[i][j] = 0;
                else if (connectionLevel < random.nextInt(100)) distanceMatrix[i][j] = Double.MAX_VALUE;
                else distanceMatrix[i][j] = Math.rint(Math.sqrt(Math.pow(vertices.get(i).x - vertices.get(j).x , 2)
                            + Math.pow(vertices.get(i).y - vertices.get(j).y , 2)) * 10.0) / 10.0;

                distanceMatrix[j][i] = distanceMatrix[i][j];
            }
            position++;
        }

    }
    // Возвращает строковое представление лучшего пути
    public static StringBuilder getStringPath(){
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < bestPath.size() - 1; i++) path.append(bestPath.get(i).number).append("-");
        path.append(bestPath.get(bestPath.size() - 1).number);
        return path;
    }

    public static ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public static void addVertex(final int x, final int y){
        vertices.add(new Vertex(x, y, vertices.size()));
    }

    public static double getBestLength() {
        return bestLength;
    }

    public static void setBestLength(double bestLength) {
        Path.bestLength = bestLength;
    }

    public static ArrayList<Vertex> getBestPath() {
       return bestPath;
    }

    public static void setBestPath(ArrayList<Integer> best) {
        bestPath.add(startVertex);
        // Добавляем только те, которые не повторяются
        best.stream().filter(vertexNumber -> vertexNumber != bestPath.get(bestPath.size() - 1).number).forEach(vertexNumber ->
                bestPath.add(vertices.get(vertexNumber)));
        bestPath.add(finishVertex);

        // Исключение из правил. Если есть прямая между точками начала и конца, то она и есть минимальный путь.
        if (distanceMatrix[startVertex.number][finishVertex.number] < bestLength){
            bestPath.clear();
            bestPath.add(startVertex);
            bestPath.add(finishVertex);
            bestLength = distanceMatrix[startVertex.number][finishVertex.number];
        }

    }

    public static Vertex getStartVertex() {
        return startVertex;
    }

    public static void setStartVertex(int startVertexNumber) {
        startVertex = vertices.get(startVertexNumber);
    }

    public static Vertex getFinishVertex() {
        return finishVertex;
    }

    public static void setFinishVertex(int finishVertexNumber) {
        finishVertex = vertices.get(finishVertexNumber);
    }

    public static void clearPathData(){
        vertices.clear();
        bestPath.clear();
        bestLength = 0;
    }

    public static int getNumberOfVertices(){
        return vertices.size();
    }

    public static double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

}
