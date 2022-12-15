package com.coursework.cw1dms.ModelClasses;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.coursework.cw1dms.ModelClasses.GameScene.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

class GameSceneTest{

    private GameScene gameSceneTest;

    private final Group testRoot= new Group();


    @BeforeEach
    void setUp() {
        int n=4;
        gameSceneTest = new GameScene();
        cells = new Cell[n][n];
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * getLENGTH() + (j + 1) * 10,
                        (i) * getLENGTH() + (i + 1) * 10, getLENGTH(), testRoot);
            }
        }
    }

    @Test
    void testRandomFillNumber() {
        Cell[][] cells = new Cell[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(cells[i][j].getNumber()==0){
                    gameSceneTest.randomFillNumber();
                    assertEquals(2,cells[i][j].getNumber());
                    assertEquals(4,cells[i][j].getNumber());
                }
            }
        }
    }

    @Test
    void testHaveEmptyCell() {
        Cell[][] cells = new Cell[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(cells[i][j].getNumber()==0) {
                    gameSceneTest.haveEmptyCell();
                    assertEquals(1,gameSceneTest.haveEmptyCell());
                }

                if(cells[i][j].getNumber()==2048){
                    gameSceneTest.haveEmptyCell();
                    assertEquals(0,gameSceneTest.haveEmptyCell());
                }
            }
        }
        assertEquals(-1, gameSceneTest.haveEmptyCell());

    }

    @Test
    void testPassDestination() {
                cells[0][3].setTextClass(TextMaker.madeText("8",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
                cells[0][0].setTextClass(TextMaker.madeText("2",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
                int j = gameSceneTest.passDestination(0,3,'l');
                assertEquals(0,cells[0][j].getNumber());
    }

    @Test
    void testMoveLeft(){
                cells[0][3].setTextClass(TextMaker.madeText("4",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
                gameSceneTest.moveHorizontally(0, 3, gameSceneTest.passDestination(0, 3, 'l'), -1);
                assertEquals(4,cells[0][0].getNumber());
    }

    @Test
    void testMoveRight() {
                cells[0][0].setTextClass(TextMaker.madeText("4",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
                gameSceneTest.moveHorizontally(0, 0, gameSceneTest.passDestination(0, 3, 'r'), 1);
                assertEquals(4,cells[0][3].getNumber());
    }

    @Test
    void testMoveUp() {
                cells[3][0].setTextClass(TextMaker.madeText("4",cells[3][0].getX(),cells[3][0].getY(),testRoot,10));
                gameSceneTest.moveVertically(0,0, gameSceneTest.passDestination(3,0,'u'),-1 );
                assertEquals(4,cells[3][0].getNumber());
    }

    @Test
    void testMoveDown() {
        cells[0][0].setTextClass(TextMaker.madeText("4",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
        gameSceneTest.moveVertically(0,0, gameSceneTest.passDestination(3,0,'d'),1 );
        assertEquals(4,cells[3][0].getNumber());
    }

    @Test
    void testIsValidDesH() {
        GameScene.setN(4);
        cells[0][3].setTextClass(TextMaker.madeText("2",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
        cells[0][0].setTextClass(TextMaker.madeText("2",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
        assertTrue(gameSceneTest.isValidDesH(0,3, 1, -1));
        //for left

        cells[0][0].setTextClass(TextMaker.madeText("4",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
        cells[0][3].setTextClass(TextMaker.madeText("2",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
        assertFalse(gameSceneTest.isValidDesH(0,0,1,1));
        //for right
    }

    @Test
    void testMoveHorizontally() {
        cells[0][3].setTextClass(TextMaker.madeText("4",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
        gameSceneTest.moveHorizontally(0, 3, gameSceneTest.passDestination(0, 3, 'l'), -1);
        assertEquals(4,cells[0][0].getNumber());
    }

    @Test
    void testIsValidDesV() {
        GameScene.setN(4);
        cells[0][3].setTextClass(TextMaker.madeText("2",cells[0][3].getX(),cells[0][3].getY(),testRoot,10));
        cells[3][3].setTextClass(TextMaker.madeText("2",cells[3][3].getX(),cells[3][3].getY(),testRoot,10));
        assertTrue(gameSceneTest.isValidDesH(0,3, gameSceneTest.passDestination(0,3,'d'), 1));


        cells[3][0].setTextClass(TextMaker.madeText("4",cells[3][0].getX(),cells[0][3].getY(),testRoot,10));
        cells[3][3].setTextClass(TextMaker.madeText("2",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
        assertFalse(gameSceneTest.isValidDesH(0,3, 1, -1));
    }

    @Test
    void testMoveVertically() {
        cells[3][0].setTextClass(TextMaker.madeText("4",cells[3][0].getX(),cells[3][0].getY(),testRoot,10));
        gameSceneTest.moveVertically(0,0, gameSceneTest.passDestination(3,0,'u'),-1 );
        assertEquals(4,cells[3][0].getNumber());
    }

    @Test
    void testHaveSameNumberNearly() {
        GameScene.setN(4);
        cells[1][0].setTextClass(TextMaker.madeText("2",cells[1][0].getX(),cells[1][0].getY(),testRoot,10));
        //System.out.println(cells[0][0].getNumber());
        cells[1][1].setTextClass(TextMaker.madeText("2",cells[1][1].getX(),cells[1][1].getY(),testRoot,10));
        //System.out.println(cells[0][0].getNumber());
        assertTrue(gameSceneTest.haveSameNumberNearly(1,0));


    }

    @Test
    void testCanNotMove() {
        cells[0][0].setTextClass(TextMaker.madeText("4",cells[0][0].getX(),cells[0][0].getY(),testRoot,10));
        cells[0][1].setTextClass(TextMaker.madeText("2",cells[0][1].getX(),cells[0][1].getY(),testRoot,10));
        cells[1][0].setTextClass(TextMaker.madeText("8",cells[1][0].getX(),cells[1][0].getY(),testRoot,10));
        gameSceneTest.haveSameNumberNearly(0,0);
        assertTrue(gameSceneTest.canNotMove());
    }

}





