package com.coursework.cw1dms.Game;

import com.coursework.cw1dms.ControllerClasses.EndGame;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;


/**
 * GameScene.java involves all the game logic regarding the 2048 game
 *
 * @author Chun Hong Boey
 */
public class GameScene{
    private static int n; //<-n value is now controlled by radio buttons, in Controller.java
    private final static int distanceBetweenCells = 10;
    private static double LENGTH;
    private final TextMaker textMaker = TextMaker.getSingleInstance();
    private final Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;
    private long sc1=0; // another variable that worked side by side with boolean added.
    private boolean added=false; // variable added to check whether the score was incremented
    private int ll,lr,ul,dl=0; // all variables here were used in cooperation with their methods , so if moveLeft returned 1 then ll.moveLeft would ==1
    private int x,y=0;

    /**
     * This method is a setter for the variable n and sets the variable LENGTH according to
     * the specific n number , the height and the distance between cells
     * @param number used to overwrite variable n;
     */
    public void setN(int number) {
        n = number;
        int HEIGHT = 700;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * This method is a getter method for the variable n
     * @return n which represent the dimension of the grid
     */
    public int getN(){
        return n;
    }

    /**
     * This method returns the length of the grid
     *
     * @return the length of the grid
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * Method used to randomly fill number "2" or "4" at a random space in the cells
     */
    private void randomFillNumber() {  //remove int turn as it was redundant

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cells[i][j].getNumber() == 0) {
                        emptyCells[a][b] = cells[i][j];
                        if (b < n - 1) {
                            bForBound = b;
                            b++;

                        } else {
                            aForBound = a;
                            a++;
                            b = 0;
                            if (a == n)
                                break outer;
                        }
                    }
                }
        }



        Text text;
        Random random = new Random();
        boolean putTwo = random.nextInt() % 2 != 0;
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell]. setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    /**
     * Method checks whether you have empty cells, logic-> if method returns -1,
     * you have no more remaining moves left, and you lose the game, when method returns 1, it
     * means that you still have empty cells avaliable, and 0 means you have gotten 2048, and would
     * abruptly stop the game, as you have reached the objective
     *
     * @return -1, no more remaining moves(no more empty cells). 1, there's empty cells, 0, you have
     * gotten 2048
     *
     */
    private int haveEmptyCell() {
        for (int i= 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }

        for (int i= 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
            }
        }

        return -1;
    }

    /**
     *
     * Method logically returns the new location of where the cell should go if user where to press up,down,left
     * right, based on the availability of space
     *
     * @param i represents the row index of the cell
     * @param j represents the column index of the cell
     * @param direct represents the current move
     * @return the new location on where the cell should go
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') { // when user decides to go left
            for (int k = j - 1; k >= 0; k--) { // traverse from the furthest left to the right
                if (cells[i][k].getNumber() != 0) { // if the furthest left of the grid has a number
                    coordinate = k + 1; //go to the next cell( +1 from the furthest left)
                    break;
                } else if (k == 0) { // if the furthest left of the grid is empty
                    coordinate = 0; // go straight to the furthest left of the grid
                }
            }
            return coordinate; //return (k+1) or 0
        }
        if (direct == 'r') { // when user decides to go right
            for (int k = j + 1; k <= n - 1; k++) { //traverse from the furthest right to the left
                if (cells[i][k].getNumber() != 0) { // if the furthest right of the grid has a number
                    coordinate = k - 1; // go to the next cell(-1 from the furthest right)
                    break;
                } else if (k == n - 1) { // if the furthest right of the grid is empty
                    coordinate = n - 1; // go straight to the furthest right of the grid
                }
            }
            return coordinate; // return (k+1), 2nd element from the right or (n-1), 1st element from the right
        }
        coordinate = i;
        if (direct == 'd') { // when user decides to go down
            for (int k = i + 1; k <= n - 1; k++) { // traverse from top to bottom of the grid
                if (cells[k][j].getNumber() != 0) { // if there's a number on the bottom of the grid
                    coordinate = k - 1; // go to one cell above the bottom
                    break;

                } else if (k == n - 1) { // if the bottom has no number
                    coordinate = n - 1; // go to the bottom
                }
            }
            return coordinate; // return either (k-1) or (n-1)
        }
        if (direct == 'u') { // when users decides to go up
            for (int k = i - 1; k >= 0; k--) { // traverse from bottom to the top of the grid
                if (cells[k][j].getNumber() != 0) { // if there's a number in the top of the grid
                    coordinate = k + 1; // go down a grid
                    break;
                } else if (k == 0) { //if there's no grid
                    coordinate = 0; // go to the top of the grid
                }
            }
            return coordinate;
        }
        return -1; // if there's nowhere left to go, then return -1
    }

    /**
     * Method detects when user moves left, if the user moves left then method would return y=1
     * @return y=1 when user presses left
     */
    private int moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                x=moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
                if(x==1){  // if moveHorizontally were to return 1
                    y=1; //then y would =1
                }
            }
        }
        return y; // then this method will return 1 , and this logic would reoccur with every up, down, left, right else return 0

        /* removed cells[i][j].setModify from methods: moveLeft(), moveRight(), moveUp(), moveDown() -> pushed to sumCellNumberstoScore(), so that
         it would only be set back to false after I add to score */
    }
    /**
     * Method detects when user moves right, if the user moves right then method would return y=1
     * @return y=1 when user presses right
     */
    private int moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                x=moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
                if(x==1){ // if moveHorizontally were to return 1
                    y=1; // then y would =1
                }
            }
        }
        return y; // then this method will return 1 , and this logic would reoccur with every up, down, left, right
    }
    /**
     * Method detects when user moves up, if the user moves up then method would return y=1
     * @return y=1 when user presses up
     */
    private int moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                x=moveVertically(i, j, passDestination(i, j, 'u'), -1); // if moveVertically were to return 1
                if(x==1){ //then x would =1
                    y=1; // which in turn make y=1 as well
                }

            }
        }
        return y;
    }
    /**
     * Method detects when user moves down, if the user moves down then method would return y=1
     * @return y=1 when user presses down
     */
    private int moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                x=moveVertically(i, j, passDestination(i, j, 'd'), 1); // if moveVertically were to return 1
                if(x==1){ //then x would =1
                    y=1; // which in turn make y=1 as well
                }
            }
        }
        return y;
    }

    /**
     * Method used to determine whether the destination of a cell is another cell with the same number
     * @param i row index of cell that is being checked
     * @param j column index of cell that is being checked
     * @param des destination of cell after moving
     * @param sign indication of which movement, up and left is -1, down and right is 1
     * @return true if destination cell = current cell, return false otherwise
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            return cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0;
        }
        return false;
    }



    /**
     *
     * Method ensures that: cells are moved correctly according to user input, and method detects movement,
     * if there's movement(ref to line 309) and the cell does not equal to 0, then return 1
     *
     * @param i row index
     * @param j column index
     * @param des destination of celling after moving
     * @param sign indication of which movement, up and left is -1, down and right is 1
     * @return 1 if moved, 0 otherwise
     */
    private int moveHorizontally(int i, int j, int des, int sign) {
        int x,y;
        if (isValidDesH(i, j, des, sign)) {
            cells[i][des+sign].setModify(true);   //ref to moveVertically
            cells[i][j].adder(cells[i][des + sign]);
        } else if (des != j) {
            x=cells[i][j].getNumber(); // variable x would the value of cells[i][j].getNumber
            y=cells[i][des].getNumber();
            cells[i][j].changeCell(cells[i][des]);
            //if the position of cells[i][j].getNumber() != cells[i][des].getNumber(), that would mean that the cells have
            //legally moved in the boundary of n*n;
            if (x!=cells[i][j].getNumber() && y!=cells[i][des].getNumber() || cells[i][j].getNumber()!=0){ // cells[i][j].getNumber()!=0 was added
                // because originally the program would not spawn as the majority of the rows/columns of the board was already filled, after adding this, it would
                // spawn legally(only when added or when there's a possibility of adding)
                return 1;
            }
        }
        return 0;
    }

    /**
     * Method used to determine whether the destination of a cell is another cell with the same number
     * @param i row index of cell that is being checked
     * @param j column index of cell that is being checked
     * @param des destination of cell after moving
     * @param sign indication of which movement, up and left is -1, down and right is 1
     * @return true if destination cell = current cell, return false otherwise
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            return cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0;
        return false;
    }

    /**
     *
     * Method ensures that: cells are moved correctly according to user input, and method detects movement,
     * if there's movement(ref to line 353) and the cell does not equal to 0, then return 1
     *
     * @param i row index
     * @param j column index
     * @param des destination of celling after moving
     * @param sign indication of which movement, up and left is -1, down and right is 1
     * @return 1 if moved, 0 otherwise
     */
    private int moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[des+sign][j].setModify(true);      // replaced [des+sign] instead of cells[des] , and swapped it to the top, this was to capture
            cells[i][j].adder(cells[des + sign][j]); // everytime the adder method was called to += to score
        } else if (des != i) {
            int a=cells[i][j].getNumber(); // variable "a" would the value of cells[i][j].getNumber
            int b=cells[des][j].getNumber(); // variable "b" would store the value of cells[des][j].getNumber()
            cells[i][j].changeCell(cells[des][j]);

            //if the position of cells[i][j].getNumber() != cells[i][des].getNumber(), that would mean that the cells have
            //legally moved in the boundary of n*n;
            if (a != cells[i][j].getNumber() && b != cells[des][j].getNumber()  || cells[i][j].getNumber()!=0){
                return 1;
            }
        }
        return 0;
    }

    /**
     * Method checks whether the adjacent cells are the same
     * @param i row index
     * @param j column index
     * @return true if the adjacent cells are the same, false if not
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            return cells[i][j + 1].getNumber() == cells[i][j].getNumber();
        }
        return false;
    }

    /**
     * Method checks if there are any possible moves anymore on the board
     * @return true if there's adjacent cells are the same, false if not
     */
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                 return false;
                }
            }
        }
        return true;
    }

    /**
     * Method accumulates score according to cells that were added, eg:- Score is 8 when two "4" cells are added
     *
     */
    private void sumCellNumbersToScore() {
        int empty_cell = GameScene.this.haveEmptyCell();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(cells[i][j].getModify() && empty_cell==1) {  //added limiters that fixed scoring system
                    score += cells[i][j].getNumber(); //accumulate score
                    cells[i][j].setModify(false); //set it back to false that indicates cells cannot be added
                }
            }
        }
    }

    /**
     * Method are properties of the word, and also adds the word in GameScene
     *
     * @param text new text "SCORE"
     */
    public void TextScore(Text text){
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
    }

    /**
     * Method are properties of the word, and also adds the word in GameScene
     *
     * @param scoreText new text "0"
     */
    public void ShowScore(Text scoreText){
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");
    }


    /**
     * Method contains initial settings for the start of the game, and logic such as: you can only
     * move if you were to press Up, Down, Left, Right and when would spawning occur (ref to line 460 onwards)
     *
     * @param gameScene get Instance of GameScene class
     * @param root node that contains all the components in GameScene class
     * @param primaryStage stage used to switch scenes around the game
     * @param color color from colorPicker in main menu
     */
    public void game(Scene gameScene, Group root, Stage primaryStage, Color color) {

        //creates all the cells for the grid
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        Text scoreText = new Text();
        TextScore(text);
        ShowScore(scoreText);

        randomFillNumber(); //removed turn
        randomFillNumber(); //removed turn

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> Platform.runLater(() -> { //refactored code to make it simpler
            int haveEmptyCell;
            if (key.getCode() == KeyCode.DOWN) {
                this.dl = GameScene.this.moveDown(); // this.dl will initially =0;
            } else if (key.getCode() == KeyCode.UP) {
                this.ul = GameScene.this.moveUp(); // this.ul will initially =0;
            } else if (key.getCode() == KeyCode.LEFT) {
                this.ll = GameScene.this.moveLeft(); // this.ll will initially =0;
            } else if (key.getCode() == KeyCode.RIGHT) {
                this.lr = GameScene.this.moveRight(); // this.lr will initially =0;
            }
            sc1 = score;
            GameScene.this.sumCellNumbersToScore();
            scoreText.setText(score + "");
            // variable sc1 will check whether the score has been incremented or not --> sc1 would always be lesser than score,
            // because it would only start incrementing after adding two cells n+1 times. For example cells 2 and 2 would generate a score
            // of 4, but sc1 will remain 0.
            added = sc1 != score;  // sc1 is not equals to true, that would mean that there was a change in scores
            haveEmptyCell = GameScene.this.haveEmptyCell();
            switch (haveEmptyCell)
            {
                case 0:
                    try {
                        EndGame.getInstance().endGameShow(primaryStage, score, color);// implemented try and catch as method endGameShow is now done in javafx
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    root.getChildren().clear();
                    score = 0;
                    break;

                case -1:
                    if(GameScene.this.canNotMove()) {
                        try {
                            EndGame.getInstance().endGameShow(primaryStage, score, color);// implemented try and catch as method endGameShow is now done in javafx
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        root.getChildren().clear();
                        score = 0;
                    }
                    break;
                case 1:
                    if (key.getCode() == KeyCode.DOWN || key.getCode() == KeyCode.UP || key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.RIGHT) {
                        if (added) { // if two cells were added, generate a cell
                            GameScene.this.randomFillNumber();
                        } else { //else check whether any of the below methods returned a 1
                            if (this.ll == 1 || this.lr == 1 || this.ul == 1 || this.dl == 1) { //when either moveUp/moveDown/moveLeft/moveRight returns 1,
                                GameScene.this.randomFillNumber();// then generate new cells.
                            }
                        }
                        // after generating new cells, set all back to 0 -> so that it can ready for detection of whether ( cells have been added or whether there are
                        // empty spaces
                        this.ll = 0;
                        this.lr = 0;
                        this.ul = 0;
                        this.dl = 0;
                        this.x = 0;
                        this.y = 0;
                    }
                    break;
            }
        }));
    }
}
