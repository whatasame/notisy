package com.github.whatasame.syncnotiontistory.gui.controller;

import com.github.whatasame.syncnotiontistory.gui.view.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainScreenController {

    private MainApplication mainApplication;

    @FXML
    private Label tistoryBlogNameLabel;

    @FXML
    private Label tistoryEmailLabel;

    @FXML
    private Label notionDatabaseNameLabel;

    @FXML
    private Label notionEmailLabel;

    @FXML
    private Button tistoryConnectionRefreshButton;

    @FXML
    private Button notionConnectionRefreshButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button syncButton;

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void initialize() {
    }


}
