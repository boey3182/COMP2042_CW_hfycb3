package com.coursework.cw1dms.ModelClasses;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Cell.java class is used to change properties of the cell such as
 * color and its number
 *
 * @author Chun Hong Boey-modified
 */

public class Cell{
    /**
     * Defines the properties of the cell
     */
    private final Rectangle rectangle;
    /**
     * root containing all the elements at that instance
     */
    private Group root;
    /**
     * Instance of Public Class Text();
     */
    private Text textClass;
    /**
     * Check whether there have been any changes to the cell
     */
    private boolean modify = false;
    /**
     * Sets the value of variable modify
     *
     * @param modify sets the new value of modify(either true or false)
     */
    void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * Method returns the value of modify
     *
     * @return the boolean variable modify
     */
    boolean getModify() {
        return modify;
    }

    /**
     *
     * @param x position index of rows
     * @param y position index of column
     * @param scale used to set the height of each individual rectangle
     * @param root used to set the width of each individual rectangle
     */
    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x); //set the number of columns for the grid
        rectangle.setY(y); // set the number of rows for the grid
        rectangle.setHeight(scale); // set the height of each individual rectangle in the grid
        rectangle.setWidth(scale); // set the width of each individual rectangle in the grid
        setRoot(root);
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root, GameScene.getLENGTH());
        //usage of singleton design pattern
        getRoot().getChildren().add(rectangle); //used to show the whole grid in the GameScene
    }

    /**
     * Method to grab the root
     * @return root
     */
    public Group getRoot(){
        return root;
    }

    /**
     * Method to set the root
     */
    public void setRoot(Group root){
        this.root=root;
    }
    /**
     * Method to set the property of this.textClass to the new textClass
     * @param textClass text from the grid.
     */
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Remove the existing Text and replace it with the new Text to change the
     * number on the provided Cell.
     * @param cell the cell of the grid to be changed
     */
    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Method grabs the number (when cells are added together, eg:4+4), cell.getNumber would return 8
     * after it gets the numbers, it would set the other cell to 0, and it removes all properties of the
     * cell after it has been added. After setting the text to cell.getNumber() it would call the method
     * to change the color of the cell
     *
     * @param cell represents an individual cell and its properties
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + ""); // get number after added
        textClass.setText("0"); // set other cell to 0
        root.getChildren().remove(textClass); //remove it
        cell.setColorByNumber(cell.getNumber());// set it back to the color of the grid
        setColorByNumber(getNumber());//set the "added" cell to its respective color
    }

    /**
     * Method sets color based on the value of the cell using switch cases
     *
     * @param number the value of the cell
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0 -> rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
            case 2 -> rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
            case 4 -> rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
            case 8 -> rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
            case 16 -> rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
            case 32 -> rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
            case 64 -> rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
            case 128 -> rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
            case 256 -> rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
            case 512 -> rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
            case 1024 -> rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
            case 2048 -> rectangle.setFill(Color.rgb(250, 0, 0, 1));
        }

    }

    /**
     * Method returns the number of columns of the grid
     *
     * @return the value of columns of the grid
     */
    double getX() {
        return rectangle.getX();
    }

    /**
     * Method returns the number of rows of the grid
     *
     * @return the value of rows of the grid
     */
    double getY() {
        return rectangle.getY();
    }

    /**
     * Method returns the value of the cell after it has been added
     *
     * @return the value of the cell after it has been added
     */
    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Method returns the property of the  Text class
     * @return the property of the  Text class
     */
    private Text getTextClass() {
        return textClass;
    }

}
