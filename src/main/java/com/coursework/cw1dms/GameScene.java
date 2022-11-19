package com.coursework.cw1dms;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

class GameScene{
    private static int HEIGHT = 700;
    private static int n = 4; //<- change the grid size (n=4, 4*4) -> ref to myself (ignore this)
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    private long score = 0;
    private long sc1=0; // another variable that worked side by side with boolean added.
    private boolean added=false; // variable added to check whether the score was incremented
    private int ll,lr,ul,dl=0; // all variables here were used in cooperation with their methods , so if moveLeft returned 1 then ll.moveLeft would ==1
    public int x,y=0;

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    static double getLENGTH() {
        return LENGTH;
    }

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
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
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

    private int haveEmptyCell() {
        for (int i= 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    // removed cells[i][j].setModify from methods: moveLeft(), moveRight(), moveUp(), moveDown() -> pushed to sumCellNumberstoScore(), so that
    // it would only be set back to false after I add to score
    private int moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                x=moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
                if(x==1){  // if moveHorizontally were to return 1
                    y=x; //then y would =1
                }
            }
        }
        return y; // then this method will return 1 , and this logic would reoccur with every up, down, left, right else return 0
    }

    private int moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                x=moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
                if(x==1){ // if moveHorizontally were to return 1
                    y=x; // then y would =1
                }
            }
        }
        return y; // then this method will return 1 , and this logic would reoccur with every up, down, left, right
    }

    private int moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                x=moveVertically(i, j, passDestination(i, j, 'u'), -1); // if moveVertically were to return 1
                if(x==1){ //then x would =1
                    y=x; // which in turn make y=1 as well
                }

            }
        }
        return y;
    }

    private int moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                x=moveVertically(i, j, passDestination(i, j, 'd'), 1); // if moveVertically were to return 1
                if(x==1){ //then x would =1
                    y=x; // which in turn make y=1 as well
                }
            }
        }
        return y;
    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }


    //below method and the method moveVertically have been fixed, cells would only spawn iff there's a possibility of adding or if two cells have
    //already been added.
    private int moveHorizontally(int i, int j, int des, int sign) {
        int x,y;
        x=y=0; // x and y variables are cleared to store the b
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

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

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

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

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

    private void sumCellNumbersToScore() {
        int empty_cell = GameScene.this.haveEmptyCell();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(cells[i][j].getModify() && empty_cell==1) {  //added limiters that fixed scoring system
                    score += cells[i][j].getNumber();
                    cells[i][j].setModify(false); //set it back to false that indicates cells cannot be added
                }
            }
        }
    }

    void game(Scene gameScene, Group root, Stage primaryStage, Scene endGameScene, Group endGameRoot) {

        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");


        randomFillNumber(); //removed turn
        randomFillNumber(); //removed turn


        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            Platform.runLater(() -> { //refactored code to make it simpler
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
                if (sc1 != score) { // variable sc1 will check whether the score has been incremented or not --> sc1 would always be lesser than score,
                    // because it would only start incrementing after adding two cells n+1 times. For example cells 2 and 2 would generate a score
                    // of 4, but sc1 will remain 0.

                    added = true;  // sc1 is not equals to true, that would mean that there was a change in scores
                } else {
                    added = false;
                }
                haveEmptyCell = GameScene.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (GameScene.this.canNotMove()) {
                        primaryStage.setScene(endGameScene);
                        EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, score);
                        root.getChildren().clear();
                        score = 0;
                    }
                } else if (haveEmptyCell == 1) {
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
                }
            });
        });
    }
}
