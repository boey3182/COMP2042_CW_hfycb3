package com.coursework.cw1dms.Account;

import com.coursework.cw1dms.ControllerClasses.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account accountTest;

    @BeforeEach
    void setUp() {
        accountTest = new Account(400L, "username");
    }

    @Test
    void testCompareTo() {
        final Account o = new Account(300L, "username");
        final int result = accountTest.compareTo(o);
        assertEquals(-1, result);
    }

    @Test
    void testMakeNewAccount() {
        accountTest.makeNewAccount("username", 400L);
        assertNotNull(accountTest.getScore());
        assertNotNull(accountTest.getUserName());
    }

    @Test
    void testWriteAccount() {
        final Leaderboard leader_ctrl = new Leaderboard();
        accountTest.writeAccount(leader_ctrl);

    }

    @Test
    void testToString() {
        assertEquals("username,400,", accountTest.toString());
    }

}
