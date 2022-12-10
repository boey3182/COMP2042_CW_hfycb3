package com.coursework.cw1dms.Game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CellTest {

    @Mock
    private Group TestRoot = new Group();

    private Cell cellTest;


    @BeforeEach
    void setUp() {
        cellTest = new Cell(1, 2, 10, TestRoot);
    }



    @Test
    void testSetTextClass() {
        final Text textClass = new Text(0.0, 0.0, "s");
        cellTest.setTextClass(textClass);
    }

    @Test
    void testChangeCell() {

        final Cell cell = new Cell(2,1,0,TestRoot);
        cell.setTextClass(TextMaker.madeText("8",cell.getX(),cell.getY(),TestRoot,10));
        cellTest.changeCell(cell);
        assertEquals(0,cell.getNumber());
        assertEquals(8,cellTest.getNumber());

    }

    @Test
    void testAdder() {
        final Cell cell =new Cell(2,4,0,TestRoot);
        cellTest.adder(cell);
    }

    @Test
    void testSetColorByNumber() {
        cellTest.setTextClass(TextMaker.madeText("2", cellTest.getX(), cellTest.getY(), TestRoot,10));
        cellTest.setColorByNumber(2);
    }

    @Test
    void testGetX() {
        final double result = cellTest.getX();
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    void testGetY() {

        final double result = cellTest.getY();
        assertEquals(1.0, result-1, 0.0001);
    }

    @Test
    void testGetNumber() {

        final Cell cell = new Cell(2,1,0,TestRoot);
        assertEquals(0, cell.getNumber());

    }
}
