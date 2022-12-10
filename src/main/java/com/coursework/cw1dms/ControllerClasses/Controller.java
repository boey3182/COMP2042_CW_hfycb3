package com.coursework.cw1dms.ControllerClasses;

import com.coursework.cw1dms.Game.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

/**
 * The Controller class is used as a controller for the fxml file: Menu.fxml
 *
 * @author Chun Hong Boey-modified
 */

public class Controller{ //removed redundant inheritance

    /**
     * Width of the GameScene
     */
    static final int WIDTH = 900;
    /**
     * Height of the GameScene
     */
    static final int HEIGHT = 700;

    /**
     * Pane of Menu.fxml
     */
    @FXML
    private Pane pane;

    /**
     * ColorPicker variable of Menu.fxml
     */
    @FXML
    public ColorPicker colorPicker;
    /**
     * Variable that would store the color of the colorPicker
     */
    private Color color;

    /**
     * New root that would store all the components that it is tied to
     */
    private final Group gameRoot = new Group();

    /**
     * RadioButtons used to switch the levels of the game
     */
    @FXML
    private RadioButton radioButton4,radioButton5,radioButton6;

    /**
     * Buttons used to control the MediaView
     */
    @FXML
    private Button play,pause;
    /**
     * MediaView used to make the video show in the GameScene
     */
    @FXML
    private MediaView mediaView;
    /**
     * MediaPlayer variable provides the ability to control the video, eg: mediaPlayer.play();
     */
    private MediaPlayer mediaPlayer;

    @FXML
    private Label GameTitle,LabelColorPicker,LabelLevel,LabelStartGame,LabelVideoDes;

    /**
     * Empty Constructor of class Controller
     */

    public Controller() {
    }

    /**
     * Method used to change the color of the Main Menu scene using a colorPicker
     */
    public void changeColor() {
        color = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        mainMenuStyling(color);
    }


    /**
     * Setter method
     *
     * @param color value of color
     */
    public void setColor(Color color){
        this.color=color;
    }


    /**
     *Method used to change the background of the main menu if user chooses to play again
     */
    public void changeColorFromLeaderboard(Color color){
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        setColor(color);
        mainMenuStyling(color);
    }

    /**
     * Method used for finding the Specific mp4 file, and playing it when Button play is activated,
     * mp4 file is repeated when it reaches the end of the video.
     */
    public void playVideo(){
        File file = new File("Videos/2048Tutorial.mp4");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play(); //on Press, the video would show up and play
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(new Duration(0.0))); //set to Loop
    }

    /**
     * Method used to pause the video
     */

    public void pauseVideo(){
        mediaPlayer.pause();
    }

    /**
     * Method used to switch scenes from Main Menu(Menu.fxml) to the GameScene, and this can only be done iff
     * a radioButton is selected.
     *
     * @param event used to get the current scene
     */

    public void switchScenes(ActionEvent event) {

        if(!radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()){ // added error handling for when user does not click on radio button to choose level
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Empty Selection");
            alert.setHeaderText("You did not select a level, Please try again!");
            alert.showAndWait().get();// do nothing and go back
        }
        else {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(gameRoot, WIDTH-300, HEIGHT, color);


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

    /**
     * Method used to change color of text accordingly to color picker
     * @param color value from the colorpicker
     */
    public void mainMenuStyling(Color color){
        if(color!=null) {
            GameTitle.setTextFill(color.invert());

            LabelColorPicker.setTextFill(color.invert());
            LabelLevel.setTextFill(color.invert());
            LabelStartGame.setTextFill(color.invert());
            LabelVideoDes.setTextFill(color.invert());

            radioButton4.setTextFill(color.invert());
            radioButton5.setTextFill(color.invert());
            radioButton6.setTextFill(color.invert());
        }
    }

    /**
     * Method used to change the Value "N" of the GameScene.java, this is to create new Levels for the game
     */
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
