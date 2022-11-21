package com.coursework.cw1dms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.Objects;


public class EndGame{
    private static EndGame singleInstance = null;
    public Button exitButton;
    public Button goBacktoMainMenu;
    @FXML
    private Pane exitPane;
    @FXML
    public Label finalScore;
    private Stage endStage;
    private Scene endScene;
    public EndGame(){

    }

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(@NotNull Stage primaryStage, long score) throws IOException {

            FXMLLoader loader= new FXMLLoader(getClass().getResource("EndGameScene.fxml")); // EndGameScene now done in SceneBuilder

            Parent endRoot = loader.load(); //load the loader
            EndGame eg = loader.getController(); //use loader to get controller to get label( if this is not done, this.finalscore will be null)
            eg.finalScore.setText(""+score);

            Scene endScene = new Scene(endRoot);
            primaryStage.setScene(endScene);
            primaryStage.setResizable(false);
            primaryStage.show();
    }



    public void exitGame(ActionEvent event) { //implementation of exitGame Button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit!");

        if(alert.showAndWait().get() == ButtonType.OK){
            endStage =(Stage)((Node)event.getSource()).getScene().getWindow();
            endStage.close();
        }
    }

    public void BacktoMainMenu(ActionEvent event) throws IOException { //implementation of going back to main menu in the form of a button
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        endStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        endScene = new Scene(root);
        endStage.setScene(endScene);
        endStage.show();
    }

}
