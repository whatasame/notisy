package com.github.whatasame.syncnotiontistory.gui.view;

import com.github.whatasame.syncnotiontistory.gui.controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private Stage primaryStage;

    private AnchorPane mainScreen;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Sync Tistory Notion");
        this.primaryStage.setResizable(false);

        loadMainScreen();
    }

    private void loadMainScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("MainScreen.fxml"));
            mainScreen = loader.load();

            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(mainScreen);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
