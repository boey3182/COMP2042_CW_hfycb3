package com.coursework.cw1dms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller extends Main {
    public Stage stage;

    private Scene scene;

    private Parent root;
    static final int WIDTH = 900;
    static final int HEIGHT = 700;

    @FXML
    private Pane pane;

    @FXML
    private ColorPicker colorPicker;

    public void changeColor(ActionEvent event) {

        Color color = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void switchScenes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();

        Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, colorPicker.getValue());

        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);


        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);

        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, WIDTH, HEIGHT, colorPicker.getValue());

        GameScene startGame = new GameScene();
        startGame.game(gameScene,gameRoot,stage,endGameScene,endgameRoot);

        stage.setScene(gameScene);
        stage.setResizable(false);
        stage.show();
    }

}
