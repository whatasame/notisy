package com.github.whatasame.notisy.gui.controller;

import com.github.whatasame.notisy.gui.config.AppConfig;
import com.github.whatasame.notisy.key.KeyManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.github.whatasame.notisy.key.Key.*;

public class SettingWindowController {

    private Stage popupStage;

    private KeyManager keyManager;

    @FXML
    private TextField tistoryAccessToken;

    @FXML
    private TextField notionToken;

    @FXML
    private TextField databaseTitle;

    @FXML
    private TextField categoryName;

    @FXML
    private void initialize() {
        AppConfig appConfig = new AppConfig();
        keyManager = appConfig.keyManager();

        tistoryAccessToken.setText(keyManager.readKey(TISTORY_ACCESS_TOKEN));
        notionToken.setText(keyManager.readKey(NOTION_TOKEN));
        databaseTitle.setText(keyManager.readKey(DATABASE_NAME));
        categoryName.setText(keyManager.readKey(CATEGORY_NAME));
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }

    public void handleTistoryAccessTokenButtonAction() {
        keyManager.updateKey(TISTORY_ACCESS_TOKEN, tistoryAccessToken.getText());
    }

    public void handleNotionTokenButtonAction() {
        keyManager.updateKey(NOTION_TOKEN, notionToken.getText());
    }

    public void handleDatabaseTitleButtonAction() {
        keyManager.updateKey(DATABASE_NAME, databaseTitle.getText());
    }

    public void handleCategoryNameButtonAction() {
        keyManager.updateKey(CATEGORY_NAME, categoryName.getText());
    }

}