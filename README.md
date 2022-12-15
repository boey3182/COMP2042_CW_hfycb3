# COMP2042_CW_hfycb3
> Enhanced 2048 by Boey Chun Hong / hfycb3 / 20299272

## Setup
Download this repository as a zip file

`<>Code -> Download as Zip`

- Scenario 1

Unzip file, right click on COMP2042ChunHongBoey and click on "Open Folder as
IntelliJ IDEA Community Edition Project". After that make sure to change the "Run Configurations", 
make sure to run on Java Version: 19.0.1. 

!-[Just to be safe, do not extract the folder in another folder with the same name as the zip file to avoid directory issues make sure that when you extract there is no complications, This is an example of the directory of a successfully extracted file:- C:\Users\boey\Desktop\COMP2042BoeyChunHong]-!

or

Open IntelliJ, and click on open, and find the folder "COMP2042ChunHongBoey". Make sure that you change the Run Configurations again, running on Java Version: 19.0.1

- Scenario 2

Unzip 2048.zip and Run the launch file given under the 2048 folder, (Ensure you have JRE and JDK installed!) --> Need to download zip file that I have handed in on Moodle.


## JavaDocs Documentation 
Link: https://laughing-newton.103-233-0-73.plesk.page/

or


Path to Javadoc documentation:COMP2042BoeyChunHong\javadoc --> Need to download zip file that I have handed in on Moodle.


## A list of features that are implemented and are working properly
- Users can save their score with any username they want
- Working Leaderboards with Permenant HighScore List -> Saved in .txt file
- Leaderboards that would only show when you play that level (eg:- if you play 4x4 grid, it would only show you the 4x4 High Scores)
- Adaptive Theme System(Texts and Labels would adapt to the change of color theme chosen by user, if users were to chose navy, then the texts and labels would
turn yellow)
- Functioning MediaView, useful for users who do not know how to play the game.
- Account System, which gives the user the choice to save their username and score into the Leaderboards
- Easter egg, that spins the whole GameScene! [Press the "S" key].
- Bug-free gameplay, have made the game as close as possible to the original game(View version control to see what I have fixed!!)

              -->Fixed the bug where the score would continue to accumulate regardless of you having empty cells or not.
	      -->Fixed the bug where you could press any button and random cells would generate.
	      -->Fixed bug where the score would increment when u spawned cells.
	      -->Fixed major bug where cells would generate regardless of whether there were cells that could be added or not.
	      
- Added a feature where the game would show you the previously added value, and I added a pulsing animation to it.
- Executable JAR file with batch(Open "launch.bat" to play the game without importing it into your IDE!!), Need to download zip file that I have handed in on Moodle.

## A list of features that are implemented and are not working properly
- None

## A list of new Java classes that I have introduced for the assignment
- Leaderboard.java


## A list of Java classes that you modified from the given code base
- Account.java
- Controller.java
- EndGame.java
- Cell.java
- GameScene.java
- TextMaker.java

## Highlight of Refactoring and Design Patterns
- Applied MVC Pattern -> Each of my Controller Classes is tied to its respective FXML files
- Applied Singleton Design Pattern -> Used in EndGame and TextMaker class
- Have broken down methods in this project to uphold the single responsibility rule where each method should only do one thing

