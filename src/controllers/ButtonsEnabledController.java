package controllers;

import views.StartForm;

/**
 * Created by Андрей
 */
public class ButtonsEnabledController {
    private StartForm startForm;

    public ButtonsEnabledController(StartForm startForm) {
        this.startForm = startForm;
    }

    public void control(){
        startForm.getCalculationMatrixButton().addActionListener(e -> controlCalculationMatrixButtonClick());
        startForm.getSetVerticesButton().addActionListener(e -> controlSetVerticesButtonClick());
        startForm.getClearButton().addActionListener(e -> controlClearButtonClick());
        startForm.getStartButton().addActionListener(e -> controlStartButtonClick());
    }

    private void controlCalculationMatrixButtonClick(){
        startForm.blockCalculationMatrixButton();
        startForm.activateSetVerticesButton();
    }

    private void controlSetVerticesButtonClick(){
        startForm.blockSetVerticesButton();
        startForm.activateStartButton();
    }

    private void controlClearButtonClick(){
        startForm.activateCalculationMatrixButton();
        startForm.blockSetVerticesButton();
        startForm.blockStartButton();
    }

    private void controlStartButtonClick(){
        startForm.blockStartButton();
    }
}
