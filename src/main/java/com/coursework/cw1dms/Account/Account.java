package com.coursework.cw1dms.Account;
import com.coursework.cw1dms.ControllerClasses.Leaderboard;
import com.coursework.cw1dms.Game.GameScene;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Account class stores the user's username and score into an arraylist.
 *
 * @author Chun Hong Boey-modified
 */

public class Account implements Comparable<Account> {

    /**
     * score that would be updated when user saves their username
     */
    private long score = 0;

    /**
     * variable that would store the username of the user
     */
    private String username ;

    /**
     * An object arraylist which stores username and score;
     */
    private static final ArrayList<Account> accounts = new ArrayList<>();


    /**
     * Empty constructor for class Account
     */
    public Account(){
    }

    /**
     * Changes the score and the username of a specific user
     *
     * @param score Changes the score of the user
     * @param username Changes the username of the user
     */
    public Account(Long score, String username){ //overloaded constructor with score and username
        this.score=score;
        this.username=username;
    }
    /**
     * Gives programmer the access to access the ArrayList from other classes.
     *
     * @return the ArrayList account, as it is set to private
     */
    public static ArrayList<Account> getAccounts(){
        return accounts;
    }
    /**
     *This method compares this.account scores with another account score.
     *
     * @param o the object to be compared.
     * @return 0 if it's the same , return > 0 if its bigger , return < 0 if its smaller
     *
     */
    @Override
    public int compareTo(@NotNull Account o) {
        return Long.compare(o.getScore(), score);
    }

    /**
     * This method returns variable: score.
     *
     * @return score
     */
    public long getScore() {
        return score;
    }

    /**
     * Setter for variable score
     *
     * @param score new value that would overwrite this class's score.
     */
    public void setScore(long score){
        this.score=score;
    }
    /**
     * This method returns variable: username.
     *
     * @return username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Setter for variable username
     *
     * @param username new String value that would overwrite this class's variable: username
     */
    public void setUsername(String username){
        this.username=username;
    }

    /**
     * Method used to grab N value from GameScene
     *
     * @return N value from GameScene
     */
    public int getLevelValue(){ //method used to grab "N" value from GameScene
        GameScene gs = new GameScene();
        return gs.getN();
    }

    /**
     *  Method Adds user's username and score into the ArrayList<Account> accounts
     *
     *
     *
     * @param username Scenario 1: If txt file is empty
     *                 Variable passed from EndGame -> user inputs into textfield and is captured and then set into this.username.
     *                 Scenario 2: If txt file is not empty
     *                 Variable passed from st1 -> Scanner that detects the username from a specific txt file, for example: 4-AccountList.txt
     *
     * @param score    Scenario 1: If txt file is empty
     *                 Variable passed from EndGame -> user inputs into textfield and is captured and then set into this.username.
     *                 Scenario 2: If txt file is not empty
     *                 Variable passed from st2-> Scanner that detects the score from a specific txt file, for example: 4-AccountList.txt
     */
    public void makeNewAccount(String username, long score) { //add username and score into ArrayList
            Account acc = new Account();
            acc.setUsername(username);
            acc.setScore(score);
            accounts.add(acc);
    }

    /**
     * Method is used to read from a txt file, and it will call method makeNewAccount to put it in ArrayList accounts
     *
     * @param leader_ctrl controller instance that was brought forward by EndGame
     */
    public void readAccount(Leaderboard leader_ctrl){ //

            File find = new File("Accounts/"+getLevelValue()+"-AccountList.txt"); // eg:- find file from directory Accounts/4-AccountList
            Scanner reader;
            String st1, st2 = null;
            try {
                reader = new Scanner(find);
                reader.useDelimiter(","); // new Line would be represented by "," for the reader

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (reader.hasNext()) {
                st1 = reader.next(); //reads the username

                if (reader.hasNext())
                    st2 = reader.next(); //reads the score

                if (st1 != null && st2 != null) {
                    makeNewAccount(st1, Long.parseLong(st2)); //values from st1 and st2(if not null) would be added into ArrayList
                }
            }
            reader.close(); //if null -> close the reader, and sort the array, and then clear the arrayList.
            sortAccount(accounts,leader_ctrl); //sorting ArrayList using bubble-sort from the highest score to lowest
            accounts.clear(); //clear the array, this was done to prevent any junk or null values;
    }

    /**
     *Method that sorts the ArrayList<Account> accounts using BubbleSort, and calls method writeAccount
     *
     * @param accounts ArrayList that contains username/score either from user's input in textfield in EndGame or from the txt-file.
     * @param leader_ctrl controller instance that was brought forward by EndGame
     */
    public void sortAccount(ArrayList<Account> accounts,Leaderboard leader_ctrl){

        for(int i=0;i< accounts.size();i++){ // simple implementation of bubble-sorting to sort arraylist from the highest to the lowest score
            for(int j=i+1;j< accounts.size();j++) {
                if (accounts.get(i).compareTo(accounts.get(j)) > 0){
                            Account temp = accounts.get(i);
                            accounts.set(i,accounts.get(j));
                            accounts.set(j,temp);
                    }
                }
            }
        writeAccount(leader_ctrl);
    }

    /**
     * Method which writes into the respective txt file from an arraylist and uses the controller instance leader_ctrl to call Method: "leaderboardShow"
     *
     * @param leader_ctrl controller instance that was brought forward by EndGame
     */
    public void writeAccount(Leaderboard leader_ctrl){
        try {
            FileWriter writeAccount = new FileWriter("Accounts/"+getLevelValue()+"-AccountList.txt"); //after sorting the arraylist, write it to its respective file
            for(int i = 0; i< Account.accounts.size(); i++) {
                writeAccount.write(String.valueOf(Account.accounts.get(i))); //write all values of accounts arraylist until empty
            }
            leader_ctrl.leaderboardShow(getLevelValue()); // using the controller instance to call a method from Leaderboard.java
            writeAccount.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overriding toString() method to let us read/write everything in our ArrayList in the form of String instead of the memory address
     *
     * @return this.getUserName() and this.getScore() in String form
     */
    @Override
    public String toString() {
        return this.getUserName()+","+this.getScore()+",";
    }

}
