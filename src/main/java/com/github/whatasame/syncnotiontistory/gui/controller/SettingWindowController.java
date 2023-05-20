package com.github.whatasame.syncnotiontistory.gui.controller;

import com.github.whatasame.syncnotiontistory.key.Key;
import com.github.whatasame.syncnotiontistory.key.KeyManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingWindowController {

    private Stage popupStage;

    @FXML
    private TextField tistoryAccessToken;

    @FXML
    private TextField notionToken;

    @FXML
    private Button tistoryAccessTokenButton;

    @FXML
    private Button notionTokenButton;

    @FXML
    private void initialize() {
        KeyManager keyManager = new KeyManager();
        tistoryAccessToken.setText(keyManager.readKey(Key.TISTORY_ACCESS_TOKEN));
        notionToken.setText(keyManager.readKey(Key.NOTION_TOKEN));
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}