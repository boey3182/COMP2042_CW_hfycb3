package com.coursework.cw1dms.ControllerClasses;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ControllerTest {

    private Controller controllerTest;

    private final Group controllerTestRoot= new Group();



    @BeforeEach
    void setUp() {
        controllerTest = new Controller();
    }

    @Test
    void testSwitchScenes() {
        // Setup
        final ActionEvent event = new ActionEvent("o", null);

        // Run the test
        Platform.startup(() -> controllerTest.switchScenes(event));

        // Verify the results
    }

}
