package com.coursework.cw1dms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
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

    public static void main(String[] args) {
        launch(args);
    }
}
