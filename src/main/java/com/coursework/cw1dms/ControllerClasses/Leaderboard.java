package com.coursework.cw1dms.ControllerClasses;

import com.coursework.cw1dms.Account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The Leaderboard.java class is used as a controller for the FXML file: Leaderboard.fxml
 *
 * @author Chun Hong Boey-modified
 */
public class Leaderboard {

    public Leaderboard(){
    }

    @FXML
    private ListView<String> L4; // generify ListView

    @FXML
    private ListView<String> L5; // generify ListView

    @FXML
    private ListView<String> L6; // generify ListView

    @FXML
    private Button back;

    private Stage leaderStage;

    @FXML
    private AnchorPane leaderPane;

    @FXML
    private Button exitButton;

    /**
     * Method updates a specific listview from fxml file with usernames and score which are sorted from Highest to lowest,
     * and this is based on the @param dim. if dim=4, it would only update and show the L4 ListView.
     *
     * @param dim refers to the N value from GameScene
     * @throws IOException if the file is not found or loaded.
     */
    public void leaderboardShow(int dim) throws IOException {
        for(int i = 0; i< Account.getAccounts().size(); i++) {
            switch (dim) {
                case 4 -> //above line updates the leaderboard based on the dimension that the user chooses.
                        L4.getItems().add("Username: " + Account.getAccounts().get(i).getUserName() + " | " + "Score: " + Account.getAccounts().get(i).getScore());
                case 5 ->
                        L5.getItems().add("Username: " + Account.getAccounts().get(i).getUserName() + " | " + "Score: " + Account.getAccounts().get(i).getScore());
                        //above line updates the leaderboard based on the dimension that the user chooses.
                case 6 ->
                        L6.getItems().add("Username: " + Account.getAccounts().get(i).getUserName() + " | " + "Score: " + Account.getAccounts().get(i).getScore());
                        //above line updates the leaderboard based on the dimension that the user chooses.
            }
        }
        //If the user decides to play the 4 by 4 grid, the LeaderboardScene would only show the Leaderboard of the gridLevel that the user played,
        //in this case that would be 4x4. So only 4x4 grid leaderboard would show, this is to indicate which level he chose to play.
    }

    /**
     * Method is tied to Button back, when Button is clicked, it would take the user back to the
     * main menu.
     *
     * @param event grabs current scene
     * @throws IOException if file is not loaded
     */
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/cw1dms/Menu.fxml")); //load the fxml file
        Parent leaderRoot = loader.load();
        leaderStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene leaderScene = new Scene(leaderRoot); //made leaderScene a local variable to simplify code
        leaderStage.setScene(leaderScene);
        leaderStage.show();
    }

    /**
     * Method closes the stage and that in turn would exit the game
     *
     * @param event grabs current scene and is cast to a stage
     */
    public void exitLeaderboard(ActionEvent event){ //implementation of exitGame Button

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit the Game!");

        if(alert.showAndWait().get() == ButtonType.OK){
            leaderStage =(Stage)((Node)event.getSource()).getScene().getWindow();
            leaderStage.close();
        }

    }
}
