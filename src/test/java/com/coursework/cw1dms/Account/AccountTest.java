package com.coursework.cw1dms.Account;

import com.coursework.cw1dms.ControllerClasses.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    private Account accountTest;

    @BeforeEach
    void setUp() {
        accountTest = new Account(400L, "username");
    }

    @Test
    void testCompareTo() {
        // Setup
        final Account o = new Account(400L, "username");

        // Run the test
        final int result = accountTest.compareTo(o);

        // Verify the results
        assertEquals(400, result);
    }

    @Test
    void testMakeNewAccount() {

        accountTest.makeNewAccount("username", 400L);

    }

    @Test
    void testSortAccount() {

        final ArrayList<Account> accounts = new ArrayList<>(List.of(new Account(400L, "username")));

        final Leaderboard leader_ctrl = new Leaderboard();

        accountTest.sortAccount(accounts, leader_ctrl);

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
