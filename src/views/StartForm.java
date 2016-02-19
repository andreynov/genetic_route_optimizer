package views;

import java.awt.*;
import javax.swing.*;

import models.route.Path;

/**
 * Created by Андрей
 */

public class StartForm  extends JFrame {
    private JPanel contentPane;
    private DrawingArea drawingArea;
    private JComboBox<String> startVertexComboBox;
    private JComboBox<String> finishVertexComboBox;
    private JSlider graphConnectionSlider;
    private JSlider mutationSlider;
    private JSlider generationSlider;
    private JSlider populationSlider;
    private JTextField graphConnectionTextField;
    private JTextField mutationTextField;
    private JTextField generationTextField;
    private JTextField populationTextField;
    private JButton startButton;
    private JButton setVerticesButton;
    private JButton calculationMatrixButton;
    private JButton clearButton;
    private JTable distancesTable;

    public StartForm() {
        createForm();
        createDrawingArea();
        createControls();
    }

    public void showAnswerForm(StringBuilder path, double bestLength){
        JOptionPane.showMessageDialog(new JFrame(), "Маршрут: " + path + ". Длина: " + bestLength + ".");
    }

    public void activateStartButton(){
        startButton.setEnabled(true);
    }

    public void blockStartButton(){
        startButton.setEnabled(false);
    }

    public void activateSetVerticesButton(){
        setVerticesButton.setEnabled(true);
    }

    public void blockSetVerticesButton(){
        setVerticesButton.setEnabled(false);
    }

    public DrawingArea getDrawingArea() {
        return drawingArea;
    }

    public void activateCalculationMatrixButton(){
        calculationMatrixButton.setEnabled(true);
    }

    public void blockCalculationMatrixButton(){
        calculationMatrixButton.setEnabled(false);
    }

    public void showDistanceTable(double[][] matrix, final int size){
        String[] headers = new String[size];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = Integer.toString(i);
        }
        // Данные для отображения матрицы расстояний. Если элемент равен Double.MAX_VALUE, то указываем, что связи между вершинами нет
        String[][] tableData = new String[size][size];
        for (int i = 0; i < tableData.length ; i++) {
            for (int j = 0; j < tableData.length; j++) {
                if (matrix[i][j] == Double.MAX_VALUE) tableData[i][j] = "нет";
                else tableData[i][j] = Double.toString(matrix[i][j]);
            }
        }

        distancesTable = new JTable(tableData, headers);
        distancesTable.setBounds(835, 230, 350, 20 * size);
        distancesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        distancesTable.setVisible(true);
        distancesTable.setRowHeight(20);
        contentPane.add(distancesTable);

        distancesTable.repaint();
        distancesTable.revalidate();
    }

    public void deleteDistanceTable(){
        contentPane.remove(distancesTable);
        this.repaint();
    }

    public void addVerticesComboBoxData(final int size){
        for (int i = 0; i < size; i++) {
            startVertexComboBox.addItem(Integer.toString(i));
            finishVertexComboBox.addItem(Integer.toString(i));
        }
    }

    public void clearVerticesComboBoxData(){
        startVertexComboBox.removeAllItems();
        finishVertexComboBox.removeAllItems();
    }

    public int getConnectionLevel(){
        return Integer.parseInt(graphConnectionTextField.getText());
    }

    public int getStartVertexNumber(){
        return Integer.parseInt(startVertexComboBox.getSelectedItem().toString());
    }

    public int getFinishVertexNumber(){
        return Integer.parseInt(finishVertexComboBox.getSelectedItem().toString());
    }

    public int getPopulationSize(){
        return Integer.parseInt(populationTextField.getText());
    }

    public int getNumberOfGenerations(){
        return Integer.parseInt(generationTextField.getText());
    }

    public int getMutationProbability(){
        return Integer.parseInt(mutationTextField.getText());
    }

    public JTextField getGraphConnectionTextField() {
        return graphConnectionTextField;
    }

    public JTextField getMutationTextField() {
        return mutationTextField;
    }

    public JTextField getGenerationTextField() {
        return generationTextField;
    }

    public JTextField getPopulationTextField() {
        return populationTextField;
    }

    public JSlider getMutationSlider() {
        return mutationSlider;
    }

    public JSlider getGenerationSlider() {
        return generationSlider;
    }

    public JSlider getPopulationSlider() {
        return populationSlider;
    }

    public JSlider getGraphConnectionSlider() {
        return graphConnectionSlider;
    }

    public JButton getCalculationMatrixButton(){
        return calculationMatrixButton;
    }

    public JButton getSetVerticesButton() {
        return setVerticesButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getStartButton(){
        return startButton;
    }


    private void createForm(){
        setBounds(100, 100, 1220, 850);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel headerLabel = new JLabel("Нахождение оптимального пути");
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        headerLabel.setBounds(0, 25, 1200, 30);
        contentPane.add(headerLabel);
    }

    private void createDrawingArea(){
        drawingArea = DrawingArea.getInstance();
        contentPane.add(drawingArea);
    }

    private void createControls(){
        createGraphConnectionsControls();
        createGeneticParametersControls();
        createStartAndFinishVerticesControls();
        createStartButton();
        createClearButton();
        blockStartButton();
        blockSetVerticesButton();
    }

    private void createGraphConnectionsControls(){
        JLabel connectionsLevelLabel = new JLabel("Уровень связности графа (%):");
        connectionsLevelLabel.setBounds(835, 80, 300, 25);
        connectionsLevelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(connectionsLevelLabel);

        graphConnectionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        graphConnectionSlider.setBounds(830, 105, 320, 50);
        graphConnectionSlider.setMajorTickSpacing(10);
        graphConnectionSlider.setPaintTicks(true);
        graphConnectionSlider.setPaintLabels(true);
        contentPane.add(graphConnectionSlider);

        graphConnectionTextField = new JTextField();
        graphConnectionTextField.setText("0");
        graphConnectionTextField.setBounds(1155, 105, 30, 20);
        contentPane.add(graphConnectionTextField);

        calculationMatrixButton = new JButton("Рассчитать матрицу расстояний");
        calculationMatrixButton.setBounds(835, 165, 310, 30);
        calculationMatrixButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        calculationMatrixButton.setForeground(new Color(0, 128, 0));
        contentPane.add(calculationMatrixButton);

        JLabel distancesMatrixLabel = new JLabel("Матрица расстояний:");
        distancesMatrixLabel.setBounds(835, 200, 300, 25);
        distancesMatrixLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(distancesMatrixLabel);
    }

    private void createStartAndFinishVerticesControls(){
        JLabel startVertexLabel = new JLabel("Стартовая вершина:");
        startVertexLabel.setBounds(15, 740, 160, 30);
        startVertexLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(startVertexLabel);

        startVertexComboBox = new JComboBox<>();
        startVertexComboBox.setBounds(175, 740, 50, 30);
        startVertexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(startVertexComboBox);

        JLabel finishVertexLabel = new JLabel("Конечная вершина:");
        finishVertexLabel.setBounds(250, 740, 160, 30);
        finishVertexLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(finishVertexLabel);

        finishVertexComboBox = new JComboBox<>();
        finishVertexComboBox.setBounds(405, 740, 50, 30);
        finishVertexComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.add(finishVertexComboBox);

        setVerticesButton = new JButton("Задать");
        setVerticesButton.setBounds(480, 740, 100, 30);
        setVerticesButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        setVerticesButton.setForeground(Color.BLUE);
        contentPane.add(setVerticesButton);
    }

    private void createGeneticParametersControls(){
        JLabel parametersLabel = new JLabel("Настройки алгоритма:");
        parametersLabel.setBounds(835, 480, 300, 25);
        parametersLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(parametersLabel);

        JLabel mutationLabel = new JLabel("Вероятность мутации (%):");
        mutationLabel.setBounds(835, 655, 300, 25);
        mutationLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(mutationLabel);

        mutationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        mutationSlider.setBounds(830, 680, 320, 50);
        mutationSlider.setMajorTickSpacing(10);
        mutationSlider.setPaintTicks(true);
        mutationSlider.setPaintLabels(true);
        contentPane.add(mutationSlider);

        mutationTextField = new JTextField();
        mutationTextField.setText("0");
        mutationTextField.setBounds(1155, 680, 30, 20);
        contentPane.add(mutationTextField);

        JLabel generationLabel = new JLabel("Число поколений (x2):");
        generationLabel.setBounds(835, 580, 300, 25);
        generationLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(generationLabel);

        generationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        generationSlider.setBounds(830, 605, 320, 50);
        generationSlider.setMajorTickSpacing(10);
        generationSlider.setPaintTicks(true);
        generationSlider.setPaintLabels(true);
        contentPane.add(generationSlider);

        generationTextField = new JTextField();
        generationTextField.setText("0");
        generationTextField.setBounds(1155, 605, 30, 20);
        contentPane.add(generationTextField);

        JLabel populationLabel = new JLabel("Размер популяции (x4):");
        populationLabel.setBounds(835, 505, 300, 25);
        populationLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(populationLabel);

        populationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        populationSlider.setBounds(830, 530, 320, 50);
        populationSlider.setMajorTickSpacing(10);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);
        contentPane.add(populationSlider);

        populationTextField = new JTextField();
        populationTextField.setText("0");
        populationTextField.setBounds(1155, 530, 30, 20);
        contentPane.add(populationTextField);
    }

    private void createClearButton(){
        clearButton = new JButton("Очистить");
        clearButton.setBounds(695, 740, 120, 30);
        clearButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        clearButton.setForeground(Color.RED);
        contentPane.add(clearButton);
    }

    private void createStartButton(){
        startButton = new JButton("Начать");
        startButton.setBounds(1065, 740, 120, 30);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        startButton.setForeground(new Color(0, 128, 0));
        contentPane.add(startButton);
    }

}
