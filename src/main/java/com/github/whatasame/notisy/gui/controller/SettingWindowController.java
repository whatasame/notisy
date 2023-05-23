package com.github.whatasame.notisy.gui.controller;

import com.github.whatasame.notisy.key.KeyManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.github.whatasame.notisy.key.Key.NOTION_TOKEN;
import static com.github.whatasame.notisy.key.Key.TISTORY_ACCESS_TOKEN;

public class SettingWindowController {

    private Stage popupStage;

    private KeyManager keyManager;

    @FXML
    private TextField tistoryAccessToken;

    @FXML
    private TextField notionToken;

    @FXML
    private void initialize() {
        keyManager = new KeyManager();
        tistoryAccessToken.setText(keyManager.readKey(TISTORY_ACCESS_TOKEN));
        notionToken.setText(keyManager.readKey(NOTION_TOKEN));
    }

    public void handleTistoryAccessTokenButtonAction() {
        keyManager.updateKey(TISTORY_ACCESS_TOKEN, tistoryAccessToken.getText());
    }

    public void handleNotionTokenButtonAction() {
        keyManager.updateKey(NOTION_TOKEN, notionToken.getText());
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}