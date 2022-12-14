package com.coursework.cw1dms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * Method where you run everything --> Main.java
 *
 * @author Chun Hong Boey
 */


public class Main extends Application {

    /**
     * Method sets the Program Icon to the original 2048 logo, and sets the Program Title to: Enhanced 2048
     *
     * @param primaryStage first stage of the game set scene to Main Menu -> Menu.fxml
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.setTitle("Enhanced 2048");
            primaryStage.getIcons().add(new Image("file:icon.jpeg"));

            primaryStage.setResizable(false);
            primaryStage.show();

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Main method to run program
     *
     * @param args string array that stores command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
