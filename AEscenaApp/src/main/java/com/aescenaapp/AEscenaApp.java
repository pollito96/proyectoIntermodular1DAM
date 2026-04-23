package com.aescenaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.sql.SQLOutput;

public class AEscenaApp extends Application {

    @Override
    public void start(Stage stage) {


        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/aescenaapp/login.fxml")
        );

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Error al cargar la vista");
            System.out.println(e.getMessage());
        }

        stage.setTitle("AEscena");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}