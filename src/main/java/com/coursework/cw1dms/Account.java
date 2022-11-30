package com.coursework.cw1dms;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Account implements Comparable<Account> {

    private long score = 0;
    private String username ;
    static ArrayList<Account> accounts = new ArrayList<>();

    public Account(){
    }

    public Account(Long score, String username){ //overloaded constructor with score and username
        this.score=score;
        this.username=username;
    }


    @Override
    public int compareTo(@NotNull Account o) {
        return Long.compare(o.getScore(), score);
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score){
        this.score=score;
    }

    public String getUserName() {
        return username;
    }



    public void setUsername(String username){
        this.username=username;
    }

    public int getLevelValue(){ //method used to grab "N" value from GameScene
        GameScene gs = new GameScene();
        return gs.getN();
    }


    public void makeNewAccount(String username, long score) { //add username and score into ArrayList
            Account acc = new Account();
            acc.setUsername(username);
            acc.setScore(score);
            accounts.add(acc);
    }


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




    public void sortAccount(ArrayList<Account> accounts,Leaderboard leaderctrl){

        for(int i=0;i< accounts.size();i++){ // simple implementation of bubble-sorting to sort arraylist from the highest to lowest score
            for(int j=i+1;j< accounts.size();j++) {
                if (accounts.get(i).compareTo(accounts.get(j)) > 0){
                            Account temp = accounts.get(i);
                            accounts.set(i,accounts.get(j));
                            accounts.set(j,temp);
                    }
                }
            }

        try {
            FileWriter myWriter = new FileWriter("Accounts/"+getLevelValue()+"-AccountList.txt"); //after sorting the arraylist, write it to its respective file
            for(int i=0;i<accounts.size();i++) {
                myWriter.write(String.valueOf(Account.accounts.get(i))); //write all values of accounts arraylist until empty
            }
            leaderctrl.leaderboardShow(getLevelValue()); // using the controller instance to call a method from Leaderboard.java
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {  //Overriding toString() method to let us read/write everything in our ArrayList in the form of String instead of the memory address
        return this.getUserName()+","+this.getScore()+",";
    }

}
