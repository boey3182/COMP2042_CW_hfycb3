package com.coursework.cw1dms.ControllerClasses;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeaderboardTest {

    private Leaderboard leaderboardUnderTest;

    private Group leaderRootTest= new Group();

    @BeforeEach
    void setUp() {
        leaderboardUnderTest = new Leaderboard();
    }


    @Test
    void testGoBack() {
        final ActionEvent event = new ActionEvent("o", null);
        Platform.startup(() -> leaderboardUnderTest.goBack(event));
    }

    @Test
    void testExitLeaderboard() {
        final ActionEvent event = new ActionEvent("o", null);
        Platform.startup(() -> leaderboardUnderTest.exitLeaderboard(event));
    }
}
