package com.coursework.cw1dms.ControllerClasses;

import com.coursework.cw1dms.Account.Account;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The Leaderboard.java class is used as a controller for the FXML file: Leaderboard.fxml
 *
 * @author Chun Hong Boey-modified
 */
public class Leaderboard{

    /**
     * Empty constructor for class Leaderboard
     */
    public Leaderboard(){
    }

    /**
     * ListView used to store the 4x4 High Score List
     */
    @FXML
    private ListView<String> L4; // generify ListView

    /**
     * ListView used to store the 5x5 High Score List
     */
    @FXML
    private ListView<String> L5; // generify ListView

    /**
     * ListView used to store the 6x6 High Score List
     */
    @FXML
    private ListView<String> L6; // generify ListView

    /**
     * Button which would be tied with method: goBack, to go back to Main Menu
     */
    @FXML
    private Button back;
    /**
     * Stage for Leaderboard.fxml
     */
    private Stage leaderStage;

    /**
     * Pane for Leaderboard.fxml
     */
    @FXML
    private AnchorPane leaderPane;

    /**
     * Button which would be tied with method: exitLeaderboard(), to exit the game.
     */
    @FXML
    private Button exitButton;

    @FXML
    private Label LabelLeaderboard,LabelLvl4,LabelLvl5,LabelLvl6;

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
        leaderboardStyling();
    }

    /**
     * Method used to style the leaderboard-scene
     */
    public void leaderboardStyling(){
        if (EndGame.getInstance().getColor()!=null){
            leaderPane.setBackground(new Background(new BackgroundFill(EndGame.getInstance().getColor(),CornerRadii.EMPTY,Insets.EMPTY)));
            LabelLeaderboard.setTextFill(EndGame.getInstance().getColor().invert());
            LabelLvl4.setTextFill(EndGame.getInstance().getColor().invert());
            LabelLvl5.setTextFill(EndGame.getInstance().getColor().invert());
            LabelLvl6.setTextFill(EndGame.getInstance().getColor().invert());
        }
    }

    /**
     * Method is tied to Button back, when Button is clicked, it would take the user back to the
     * main menu.
     *
     * @param event grabs current scene
     */
    public void goBack(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/coursework/cw1dms/Menu.fxml")); //load the fxml file
                Parent leaderRoot;
                try {
                    leaderRoot = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                leaderStage =(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene leaderScene = new Scene(leaderRoot); //made leaderScene a local variable to simplify code

                Controller MenuCtrl = loader.getController();//grab Controller instance
                MenuCtrl.changeColorFromLeaderboard(EndGame.getInstance().getColor());//change color of pane

                leaderStage.setScene(leaderScene);
                leaderStage.show();
            }
        });
    }

    /**
     * Method closes the stage and that in turn would exit the game
     *
     * @param event grabs current scene and is cast to a stage
     */
    public void exitLeaderboard(ActionEvent event){ //implementation of exitGame Button

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You're about to exit the Game!");

            if(alert.showAndWait().get() == ButtonType.OK){
                leaderStage =(Stage)((Node)event.getSource()).getScene().getWindow();
                leaderStage.close();
            }
        });
    }
}
