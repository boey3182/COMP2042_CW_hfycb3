package com.coursework.cw1dms.ControllerClasses;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class EndGameTest {

    private EndGame endGameTest;

    @BeforeEach
    void setUp() {
        endGameTest = new EndGame();
    }


    @Test
    void testExitGame() {

        final ActionEvent event = new ActionEvent("o", null);

        Platform.startup(() -> endGameTest.exitGame(event));

    }

    @Test
    void testSaveView(){

        final ActionEvent event = new ActionEvent("o", null);

        Platform.startup(() -> {
            try {
                endGameTest.saveView(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    @Test
    void testGetInstance() {

        final EndGame result = EndGame.getInstance();

    }
}
