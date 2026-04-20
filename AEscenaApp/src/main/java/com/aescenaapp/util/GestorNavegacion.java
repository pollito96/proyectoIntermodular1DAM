package com.aescenaapp.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class GestorNavegacion {
    public static boolean cambiarVista(String fxml, Node nodo) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    GestorNavegacion.class.getResource(fxml)
            );

            Parent root = loader.load();

            Stage stage = (Stage) nodo.getScene().getWindow();
            stage.getScene().setRoot(root);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
