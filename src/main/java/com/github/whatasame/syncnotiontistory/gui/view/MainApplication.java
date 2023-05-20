package com.github.whatasame.syncnotiontistory.gui.view;

import com.github.whatasame.syncnotiontistory.gui.controller.MainApplicationController;
import com.github.whatasame.syncnotiontistory.gui.controller.SettingWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        loadMainScreen();
    }

    /**
     * Main application UI
     */
    private void loadMainScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-application.fxml"));
            AnchorPane mainScreen = fxmlLoader.load();

            MainApplicationController mainApplicationController = fxmlLoader.getController();
            mainApplicationController.setMainApp(this);

            Scene scene = new Scene(mainScreen);
            primaryStage.setTitle("Sync Tistory Notion");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 설정 창을 띄운다.
     */
    public void openSettingWindow() {
        try {
            Stage popupStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setting-window.fxml"));
            AnchorPane settingWindow = fxmlLoader.load();

            SettingWindowController settingWindowController = fxmlLoader.getController();
            settingWindowController.setPopupStage(popupStage);

            Scene popupScene = new Scene(settingWindow);
            popupStage.setTitle("Setting");
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initOwner(primaryStage);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
