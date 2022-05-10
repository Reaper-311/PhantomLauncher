package com.example.finallauncherrefactored;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsAboutController {

    @FXML
    private Button btn_Okay;

    @FXML
    void btn_handleOkay(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

}
