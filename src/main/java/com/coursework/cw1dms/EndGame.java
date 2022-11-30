package com.coursework.cw1dms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;


public class EndGame{
    private static EndGame singleInstance = null;

    @FXML
    public Button exitButton;

    @FXML
    private Button saveViewButton;

    @FXML
    public AnchorPane exitPane;
    @FXML
    private Label finalScore;
    @FXML
    private TextField getUsername;

    private Stage endStage;

    private Scene endScene;

    public Parent endRoot;

    public String username;


    public EndGame(){
    }

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    public void endGameShow(@NotNull Stage primaryStage, long score, Color color) throws IOException {

            FXMLLoader loader= new FXMLLoader(getClass().getResource("EndGameScene.fxml"));
            endRoot = loader.load(); //load the loader
            EndGame eg = loader.getController(); //use loader to get controller to get label( if this is not done, this.finalscore will be null)

            eg.finalScore.setText(""+score); // show final score
            eg.exitPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))); //change color of the EndGameScene -> EndGameScene.fxml

            endScene = new Scene(endRoot);
            primaryStage.setScene(endScene);
            primaryStage.setResizable(false);
            primaryStage.show();
    }



    public void exitGame(ActionEvent event){ //implementation of exitGame Button

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You're about to exit without saving your score!");

            if(alert.showAndWait().get() == ButtonType.OK){
                endStage =(Stage)((Node)event.getSource()).getScene().getWindow();
                endStage.close();
            }

    }


    public void saveView(ActionEvent event) throws IOException {

        if(getUsername.getText().isEmpty()){ //error handling for when user does not enter username.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Username is Empty");
            alert.setHeaderText("You did not insert a username, Please try again!");
            alert.showAndWait().get();// do nothing and go back
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml")); //load the fxml file
            endRoot = loader.load();
            Leaderboard lbd_ctrl = loader.getController();

            endStage =(Stage)((Node)event.getSource()).getScene().getWindow(); //get current stage
            endScene = new Scene(endRoot);
            endStage.setScene(endScene); //switch to Leaderboard scene -> Leaderboard.fxml
            endStage.show();

            username=getUsername.getText(); //grab input from user for username

            Account acc = new Account(Long.parseLong(finalScore.getText()),username); //make new instance of the class Account
            acc.makeNewAccount(username,Long.parseLong(finalScore.getText())); //call the method makeNewAccount()
            acc.readAccount(lbd_ctrl); //and read account and pass the controller instance

            //Flow of game from this point: username and finalScore would be passed on to class Account -> new Account would be made,
            //the purpose of readAccount is to read the file and store it back in the array list, but this would only be useful if the file
            //is not empty. In the case scenario where it is empty, readAccount() would break out of loop and call sortAccounts() instead, so it would
            //directly write to its respective highscore list. If it's not empty, everything in the txt file would be read and stored back into the arraylist,
            //and resorted and reprinted into its respective highscore list.
        }


    }


}
