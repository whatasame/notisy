package com.github.whatasame.notisy.gui.controller;

import com.github.whatasame.notisy.gui.view.MainApplication;
import com.github.whatasame.notisy.tistory.api.TistoryHttpHandler;
import com.github.whatasame.notisy.tistory.model.TistoryBlog;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainApplicationController {

    private MainApplication mainApplication;

    @FXML
    private Label tistoryBlogNameLabel;

    @FXML
    private Label tistoryNicknameLabel;

    @FXML
    private Label notionDatabaseNameLabel;

    @FXML
    private Label notionEmailLabel;

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void handleSettingButtonAction() {
        mainApplication.openSettingWindow();
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
