package com.coursework.cw1dms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller{ //removed redundant inheritance

    public Stage stage;
    static final int WIDTH = 900;
    static final int HEIGHT = 700;

    @FXML
    private Pane pane;

    private static Controller singleInstance = null;

    @FXML
    public ColorPicker colorPicker;

    public Color color;

    public Group gameRoot = new Group();

    @FXML
    public RadioButton radioButton4;

    @FXML
    public RadioButton radioButton5;

    @FXML
    public RadioButton radioButton6;


    public void changeColor(ActionEvent event) throws IOException {
        color = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }


    public void switchScenes(ActionEvent event) throws IOException {
        if(!radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()){ // added error handling for when user does not click on radio button to choose level
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Empty Selection");
            alert.setHeaderText("You did not select a level, Please try again!");
            alert.showAndWait().get();// do nothing and go back
        }
        else {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
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

    public void setNValue(ActionEvent event){ // method for radio buttons
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
