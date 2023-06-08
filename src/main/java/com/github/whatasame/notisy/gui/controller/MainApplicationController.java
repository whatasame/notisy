package com.github.whatasame.notisy.gui.controller;

import com.github.whatasame.notisy.gui.config.AppConfig;
import com.github.whatasame.notisy.gui.view.MainApplication;
import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.service.DatabaseService;
import com.github.whatasame.notisy.tistory.api.TistoryHttpHandler;
import com.github.whatasame.notisy.tistory.model.TistoryBlog;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import notion.api.v1.model.search.DatabaseSearchResult;

import java.io.IOException;

import static com.github.whatasame.notisy.key.Key.DATABASE_NAME;

public class MainApplicationController {

    private MainApplication mainApplication;

    @FXML
    private Label tistoryBlogNameLabel;

    @FXML
    private Label tistoryNicknameLabel;

    @FXML
    private Label notionDatabaseTitleLabel;

    @FXML
    private Label notionDatabaseDescriptionLabel;

    private KeyManager keyManager;

    private DatabaseService databaseService;

    @FXML
    private void initialize() {
        AppConfig appConfig = new AppConfig();
        keyManager = appConfig.keyManager();
        databaseService = appConfig.databaseService();

        refreshTistoryConnection();
        refreshNotionConnection();
    }

    public void setMainApp(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void handleSettingButtonAction() {
        mainApplication.openSettingWindow();
    }

    public void handleTistoryConnectionRefreshButtonAction() {
        refreshTistoryConnection();
    }

    public void handleRefreshConnectionRefreshButtonAction() {
        refreshNotionConnection();
    }

    private void refreshTistoryConnection() {
        try {
            TistoryBlog defaultBlog = new TistoryHttpHandler().getDefaultBlog();
            tistoryBlogNameLabel.setText(defaultBlog.getTitle());
            tistoryNicknameLabel.setText(defaultBlog.getNickname());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshNotionConnection() {
        String databaseName = keyManager.readKey(DATABASE_NAME);

        DatabaseSearchResult database = databaseService.searchDatabase(databaseName);

        notionDatabaseDescriptionLabel.setText(databaseService.getDescription(database));
        notionDatabaseTitleLabel.setText(databaseService.getTitle(database));
    }

}
