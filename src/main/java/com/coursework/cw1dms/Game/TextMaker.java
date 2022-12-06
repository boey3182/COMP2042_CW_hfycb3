package com.coursework.cw1dms.Game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * TextMaker class holds methods that can change Texts or create Text instances
 */
class TextMaker {
    private static TextMaker singleInstance = null;

    /**
     * Empty Constructor of the class TextMaker
     */

    private TextMaker() {

    }

    /**
     * Method creates new Instance of the class
     * @return new Instance of the class
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * Creates a new Text Instance and content of it would be based on input. Location
     * would be based on xCell, yCell
     *
     * @param input  content of the text
     * @param xCell  row index
     * @param yCell  column index
     * @param root   node that contains all the components in GameScene class
     * @param length
     * @return text instance
     */

    static Text madeText(String input, double xCell, double yCell, Group root, double length) {
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);
        return text;
    }

    /**
     * Swap the position and the content of two texts
     * @param first Text 1 that would be swapped relocated
     * @param second Text 2 that would be swapped and relocated
     */

    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
