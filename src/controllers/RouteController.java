package controllers;

import views.DrawingConstants;
import views.StartForm;
import models.route.Path;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Андрей
 */
public class RouteController {
    private StartForm startForm;

    public RouteController(StartForm startForm){
        this.startForm = startForm;
    }

    public void control(){
        startForm.getCalculationMatrixButton().addActionListener(e -> {
            controlDistanceMatrix();
            controlGraphConnections();
            controlVerticesComboBoxes();
        });
        startForm.getDrawingArea().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int x = e.getX() / DrawingConstants.CIRCLE_SIZE;
                int y = e.getY() / DrawingConstants.CIRCLE_SIZE;
                startForm.getDrawingArea().drawVertex(x, y, Path.getNumberOfVertices());
                Path.addVertex(x, y);
            }
        });
        startForm.getSetVerticesButton().addActionListener(e -> controlStartAndFinishVertices());
        startForm.getClearButton().addActionListener(e -> clearPathData());
    }

    private void controlDistanceMatrix(){
        Path.calculateDistanceMatrix(startForm.getConnectionLevel());
        startForm.showDistanceTable(Path.getDistanceMatrix(), Path.getNumberOfVertices());
        startForm.repaint();
    }

    private void controlGraphConnections(){
        startForm.getDrawingArea().drawConnections(Path.getDistanceMatrix(), Path.getVertices());
    }

    private void controlVerticesComboBoxes(){
        startForm.addVerticesComboBoxData(Path.getNumberOfVertices());
    }

    private void controlStartAndFinishVertices(){
        Path.setStartVertex(startForm.getStartVertexNumber());
        Path.setFinishVertex(startForm.getFinishVertexNumber());
        startForm.getDrawingArea().showStartAndFinishVertices(Path.getStartVertex(), Path.getFinishVertex());
    }

    private void clearPathData(){
        startForm.getDrawingArea().clear();
        Path.clearPathData();
        startForm.clearVerticesComboBoxData();
        startForm.deleteDistanceTable();
    }

}
