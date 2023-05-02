package com.github.whatasame.syncnotiontistory.gui.controller;

import com.github.whatasame.syncnotiontistory.gui.view.MainApplication;
import com.github.whatasame.syncnotiontistory.tistory.api.TistoryHttpHandler;
import com.github.whatasame.syncnotiontistory.tistory.model.TistoryBlog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainScreenController {

    private MainApplication mainApplication;

    @FXML
    private Label tistoryBlogNameLabel;

    @FXML
    private Label tistoryNicknameLabel;

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

    public void handleTistoryConnectionRefreshButtonAction() {
        try {
            TistoryBlog defaultBlog = new TistoryHttpHandler().getDefaultBlog();
            tistoryBlogNameLabel.setText(defaultBlog.getTitle());
            tistoryNicknameLabel.setText(defaultBlog.getNickname());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
