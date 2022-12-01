package com.coursework.cw1dms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;


public class Controller{ //removed redundant inheritance

    public Stage stage;
    static final int WIDTH = 900;
    static final int HEIGHT = 700;
    @FXML
    private Pane pane;
    @FXML
    private ColorPicker colorPicker;
    private Color color;
    public Group gameRoot = new Group();
    @FXML
    private RadioButton radioButton4,radioButton5,radioButton6;
    @FXML
    private Button play,pause;

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;

    public Controller() {
    }

    public void changeColor() {
        color = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public void playVideo(){
        File file = new File("Videos/2048Tutorial.mp4");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play(); //on Press, the video would show up and play
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(new Duration(0.0))); //set to Loop
    }

    public void pauseVideo(){
        mediaPlayer.pause();
    }


    public void switchScenes(ActionEvent event) {
        if(!radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()){ // added error handling for when user does not click on radio button to choose level
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Empty Selection");
            alert.setHeaderText("You did not select a level, Please try again!");
            alert.showAndWait().get();// do nothing and go back
        }
        else {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, colorPicker.getValue());

            Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
            backgroundOfMenu.setX((float) WIDTH / 2 - 120);
            backgroundOfMenu.setY(180);

            Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
            backgroundOfMenuForPlay.setX((float) WIDTH / 2 - 120);
            backgroundOfMenuForPlay.setY(180);

            GameScene startGame = new GameScene();
            startGame.game(gameScene, gameRoot, stage, color);

            stage.setScene(gameScene);
            stage.setResizable(false);
            stage.show();
        }
    }

    public void setNValue(){ // method for radio buttons
        GameScene gs = new GameScene();
        if(radioButton4.isSelected()){ //if the specific radio button is selected, the value "n" in GameScene would change to its respective value
            gs.setN(4);
        }

        else if(radioButton5.isSelected()){
            gs.setN(5);
        }

        else {
            gs.setN(6);
        }

    }

}
