package com.coursework.cw1dms.Game;

import javafx.scene.Group;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.testng.Assert.assertNotEquals;

class TextMakerTest{

    @Test
    void testGetSingleInstance() {

        final TextMaker result = TextMaker.getSingleInstance();
        final Group root = new Group(List.of());
    }

    @Test
    void madeText(){
        final TextMaker result = TextMaker.getSingleInstance();
        final Group root = new Group(List.of());
        assertNotEquals(new Text("Test"), result.madeText("Test",0,0,root, 12));
        //should not be equals as the font size would change according to the length
    }

    @Test
    void testChangeTwoText() {
        // Setup
        final Text first = new Text(0.0, 0.0, "s");
        final Text second = new Text(0.0, 0.0, "s");

        // Run the test
        TextMaker.changeTwoText(first, second);

        // Verify the results
    }
}
