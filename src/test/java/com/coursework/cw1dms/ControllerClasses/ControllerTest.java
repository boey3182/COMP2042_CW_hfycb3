package com.coursework.cw1dms.ControllerClasses;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ControllerTest {

    private Controller controllerTest;

    @BeforeEach
    void setUp() {
        controllerTest = new Controller();
    }

    @Test
    void testSwitchScenes() {
        final ActionEvent event = new ActionEvent("o", null);
        Platform.startup(() -> controllerTest.switchScenes(event));
    }

}
