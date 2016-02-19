package views;

import models.route.Path;
import models.route.Vertex;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Created by Андрей
 */

public class DrawingArea extends JPanel {
    private static DrawingArea instance;
    private Image image;
    private Graphics2D g2;

    // Singleton
    private DrawingArea(){
        setBackground(Color.WHITE);
        setBounds(15, 80 , 800, 650);
    }

    public static DrawingArea getInstance(){
        if (instance == null) instance = new DrawingArea();
        return instance;
    }

    public void drawVertex(final int x, final int y, final int size){
        g2.setColor(Color.BLACK);
        g2.drawString(Integer.toString(size), x * DrawingConstants.CIRCLE_SIZE, y * DrawingConstants.CIRCLE_SIZE);

        g2.setColor(Color.BLUE);
        g2.drawOval(x * DrawingConstants.CIRCLE_SIZE, y * DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);
        g2.fillOval(x *DrawingConstants.CIRCLE_SIZE, y * DrawingConstants.CIRCLE_SIZE,DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);

        repaint();
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void drawConnections(double[][] matrix, ArrayList<Vertex> vertices){
        g2.setColor(Color.GRAY);
        int position = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = position; j < matrix.length; j++){
                if (matrix[i][j] != Double.MAX_VALUE && i != j)
                    g2.drawLine(vertices.get(i).x * DrawingConstants.CIRCLE_SIZE +DrawingConstants.CIRCLE_SIZE / 2,
                            vertices.get(i).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                            vertices.get(j).x * DrawingConstants.CIRCLE_SIZE +DrawingConstants.CIRCLE_SIZE / 2,
                            vertices.get(j).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2);
            }
            position++;
        }
    }

    public void showStartAndFinishVertices(Vertex startVertex, Vertex finishVertex){
        g2.setColor(Color.GREEN);
        g2.drawOval(startVertex.x * DrawingConstants.CIRCLE_SIZE, startVertex.y * DrawingConstants.CIRCLE_SIZE ,
                DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);
        g2.fillOval(startVertex.x * DrawingConstants.CIRCLE_SIZE, startVertex.y * DrawingConstants.CIRCLE_SIZE ,
                DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);

        g2.setColor(Color.RED);
        g2.drawOval(finishVertex.x * DrawingConstants.CIRCLE_SIZE, finishVertex.y * DrawingConstants.CIRCLE_SIZE ,
                DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);
        g2.fillOval(finishVertex.x * DrawingConstants.CIRCLE_SIZE, finishVertex.y * DrawingConstants.CIRCLE_SIZE ,
                DrawingConstants.CIRCLE_SIZE, DrawingConstants.CIRCLE_SIZE);

        repaint();
    }

    public void showPath(ArrayList<Vertex> bestPath, Vertex startVertex, Vertex finishVertex){
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawLine(startVertex.x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                startVertex.y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                bestPath.get(0).x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                bestPath.get(0).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2);

        for (int i = 0 ; i < bestPath.size() - 1; i++){
            g2.drawLine(bestPath.get(i).x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                    bestPath.get(i).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                    bestPath.get(i+1).x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                    bestPath.get(i+1).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2);
        }
        g2.drawLine(bestPath.get(bestPath.size() - 1).x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                bestPath.get(bestPath.size() - 1).y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                finishVertex.x * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2,
                finishVertex.y * DrawingConstants.CIRCLE_SIZE + DrawingConstants.CIRCLE_SIZE / 2);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();

        }
        g.drawImage(image, 0, 0, null);
    }

}
