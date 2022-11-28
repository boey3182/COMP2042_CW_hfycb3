package com.coursework.cw1dms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Leaderboard {

    public Leaderboard(){
    }

    private static Leaderboard singleInstance = null;


    @FXML
    public ListView listView1;

    @FXML
    public ListView listView2;

    @FXML
    public ListView listView3;

    @FXML
    public Button back;

    public Parent leaderRoot;

    private Stage leaderStage;

    private Scene leaderScene;

    @FXML
    private AnchorPane leaderPane;


    private static long score;

    public static Leaderboard getInstance(){
        if(singleInstance == null)
            singleInstance= new Leaderboard();
        return singleInstance;
    }



    public void goBack(ActionEvent event) throws IOException {
        leaderRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        leaderStage =(Stage)((Node)event.getSource()).getScene().getWindow();
        leaderScene = new Scene(leaderRoot);
        leaderStage.setScene(leaderScene);
        leaderStage.show();
    }


    public void leaderboardShow() throws IOException {

    }
}
