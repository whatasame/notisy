package com.github.whatasame.notisy.gui.controller;

import com.github.whatasame.notisy.gui.config.AppConfig;
import com.github.whatasame.notisy.gui.view.MainApplication;
import com.github.whatasame.notisy.key.KeyManager;
import com.github.whatasame.notisy.notion.service.DatabaseService;
import com.github.whatasame.notisy.tistory.api.exception.TistoryException;
import com.github.whatasame.notisy.tistory.api.model.Blog;
import com.github.whatasame.notisy.tistory.service.BlogService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import notion.api.v1.model.search.DatabaseSearchResult;

import java.util.Optional;

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

    private BlogService blogService;

    private DatabaseService databaseService;

    @FXML
    private void initialize() {
        AppConfig appConfig = new AppConfig();
        keyManager = appConfig.keyManager();
        databaseService = appConfig.databaseService();
        blogService = appConfig.blogService();

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
        Optional<Blog> result = blogService.getDefaultBlog();
        if (result.isEmpty()) {
            throw new TistoryException("기본 블로그가 없습니다.");
        }

        Blog defaultBlog = result.get();
        tistoryBlogNameLabel.setText(defaultBlog.title());
        tistoryNicknameLabel.setText(defaultBlog.nickname());
    }

    private void refreshNotionConnection() {
        String databaseName = keyManager.readKey(DATABASE_NAME);

        DatabaseSearchResult database = databaseService.searchDatabase(databaseName);

        notionDatabaseDescriptionLabel.setText(databaseService.getDescription(database));
        notionDatabaseTitleLabel.setText(databaseService.getTitle(database));
    }

}
