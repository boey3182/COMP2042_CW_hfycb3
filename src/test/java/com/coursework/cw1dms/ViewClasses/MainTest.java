package com.coursework.cw1dms.ViewClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

    @BeforeEach
    void setUp() {
        Main mainUnderTest = new Main();
    }


    @Test
    void testMain() {
        // Setup
        // Run the test
        Main.main(new String[]{"args"});

        // Verify the results
    }
}
