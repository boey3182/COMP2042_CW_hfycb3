package com.coursework.cw1dms;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import static java.lang.System.exit;


public class EndGame{
    private static EndGame singleInstance = null;
    private EndGame(){

    }

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(Scene endGameScene, @NotNull Group root, Stage primaryStage, long score){

        Text text = new Text("Game Over");
        text.relocate(220,275);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
        root.getChildren().add(text);


        Text scoreText = new Text("Your score: "+score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.relocate(260,375);
        scoreText.setFont(Font.font(String.valueOf(FontWeight.THIN),60));
        root.getChildren().add(scoreText);

        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(200,60);
        quitButton.setTextFill(Color.BLACK);
        root.getChildren().add(quitButton);
        quitButton.relocate(360,440);

        quitButton.setOnAction(event -> { //refactored code to make it simpler
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Quit Dialog");
            alert.setHeaderText("Quit from this page");
           alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                root.getChildren().clear();
                exit(0); // game will quit when OK button is clicked
            }
        });




    }
}
