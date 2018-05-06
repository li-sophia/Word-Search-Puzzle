/*File Name: WordSearchPuzzle.java
 * Description: This program generates and solves word search puzzles based on given word input/output files (with GUI)
 * Author: Stephanie Kwong and Sophia Li
 * Date: Jan 13, 2017
 */

import java.io.PrintWriter; //libraries
import java.io.File;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordSearchPuzzle extends JFrame implements ActionListener{    
  JButton genPuzzleButton = new JButton ("GENERATE PUZZLE"); //adding buttons
  JButton solvePuzzleButton = new JButton ("SOLVE PUZZLE");
  JButton displayWords = new JButton ("DISPLAY WORDS");
  JButton okButton = new JButton ("OK");
  JButton solveButton = new JButton ("Solve!");
  JButton genButton = new JButton ("Generate!");
  JButton quit = new JButton("QUIT");
  JButton reset = new JButton("RESET");
  JLabel options = new JLabel ("OPTIONS:");
  JLabel enterWordFile = new JLabel ("Word List File (input): "); //adding labels
  JLabel enterSolutionFile = new JLabel ("Solution File (output):");
  JLabel enterPuzzleFile = new JLabel ("Generated Puzzle File (output):");
  JLabel enterPuzzleFile2 = new JLabel ("Generated Puzzle File (input):");
  JLabel htmlFile = new JLabel ("HTML Solution File (output):");
  JLabel enterRow = new JLabel("# of Rows:");
  JLabel enterColumn = new JLabel("# of Columns:");
  JLabel showWords[] = new JLabel[10];
  JLabel endMessage = new JLabel();
  JLabel endMessage2 = new JLabel();
  JLabel greeting = new JLabel("Welcome to Stephanie and Sophia's Word Search Puzzle!");
  JLabel message = new JLabel("The rows and columns must have a minimum length of 10 and a maximum length of 20. To change the grid size, click 'GENERATE PUZZLE' again.");
  JLabel message2 = new JLabel("NOTE: The word list file must contain 1 to 10 words, with each word containing 4 to 8 letters in length. All file extensions must be '.txt' except the HTML solution file must be '.html'.");
  JLabel message3 = new JLabel();
  JTextField nameWordFile = new JTextField (6); //adding text fields
  JTextField nameSolutionFile = new JTextField (6);
  JTextField namePuzzleFile = new JTextField (6);
  JTextField namePuzzleFile2 = new JTextField(6);
  JTextField htmlFileField = new JTextField(6);
  JTextField getRow =new JTextField(5);
  JTextField getColumn=new JTextField (5);
  JPanel pan1 = new JPanel();
  JPanel pan2 = new JPanel(); //adding panels
  JPanel pan3 = new JPanel();
  JPanel pan4 = new JPanel();
  JPanel pan5 = new JPanel();
  JPanel pan6 = new JPanel();
  JPanel pan7 = new JPanel();
  JPanel pan8 = new JPanel();
  JPanel pan9 = new JPanel();
  JPanel pan10 = new JPanel();
  JPanel pan11 = new JPanel();
  JPanel pan12 = new JPanel();
  JPanel pan13 = new JPanel();
  
  public WordSearchPuzzle (){   //constructor 
    GridLayout layout1= new GridLayout(13,1);   
    FlowLayout layout2 = new FlowLayout();
    
    Font font1 = new Font("Calibri Light", Font.ITALIC, 14);
    
    setSize(1100,700);  //sets size of screen
    setLayout(layout1);  //sets the default layour to grid layout
    pan13.setLayout(layout2);
    pan1.setLayout(layout2);
    pan2.setLayout(layout2);
    pan3.setLayout(layout2);
    pan4.setLayout(layout2);
    pan5.setLayout(layout2);
    pan7.setLayout(layout2);
    pan12.setLayout(layout2);
    pan8.setLayout(layout2);
    pan9.setLayout(layout2);
    pan10.setLayout(layout2);
    pan11.setLayout(layout2);
    
    pan13.add(greeting);
    greeting.setFont(new Font("Calibri Light", Font.ITALIC, 22));
    
    pan11.add(options);
    options.setFont(new Font("Calibri Light", Font.BOLD, 16));
    pan1.add(message);
    pan7.add(enterWordFile);
    pan7.add(nameWordFile);
    pan12.add(message2);
    message2.setFont(font1);
    pan8.add(enterPuzzleFile);
    pan8.add(namePuzzleFile);
    pan8.add(enterSolutionFile);
    pan8.add(nameSolutionFile);
    pan2.add(enterPuzzleFile2);
    pan2.add(namePuzzleFile2);
    pan2.add(htmlFile);
    pan2.add(htmlFileField);
    
    pan10.add(quit);
    pan10.add(reset);
    
    quit.addActionListener(this); //check to see if button is pressed
    reset.addActionListener(this);
    displayWords.addActionListener(this); 
    genPuzzleButton.addActionListener(this); 
    solvePuzzleButton.addActionListener(this);
    okButton.addActionListener(this); 
    genButton.addActionListener(this);
    solveButton.addActionListener(this); 
    
    pan3.add(displayWords);
    pan3.add(genPuzzleButton);
    pan3.add(solvePuzzleButton);
    pan4.add(enterRow);
    pan4.add(getRow);
    pan4.add(enterColumn);
    pan4.add(getColumn);
    pan4.add(okButton);
    pan9.add(genButton);
    genButton.setFont(font1);
    pan9.add(solveButton);
    solveButton.setFont(font1);
    pan4.add(message3);
    
    for (int i=0;i<10;i++){
      String blank="";
      showWords[i]=new JLabel(blank);  //making it a blank space
      pan5.add(showWords[i]);
    }
    
    pan6.add(endMessage2);
    pan6.add(endMessage);
    endMessage2.setFont(font1);
    endMessage.setFont(font1);
    
    nameWordFile.setEnabled(false);   //disables buttons
    nameSolutionFile.setEnabled(false);   
    namePuzzleFile.setEnabled(false); 
    okButton.setEnabled(false);
    getRow.setEnabled(false);
    getColumn.setEnabled(false);
    displayWords.setEnabled(false);
    namePuzzleFile2.setEnabled(false);
    htmlFileField.setEnabled(false);
    solveButton.setEnabled(false);
    genButton.setEnabled(false);
    reset.setEnabled(false);
    
    genPuzzleButton.setBackground(Color.CYAN); //adding colour to buttons
    displayWords.setBackground(Color.ORANGE);
    solvePuzzleButton.setBackground(Color.MAGENTA);
    
    add(pan13); //adding panels
    add(pan11);
    add(pan3);
    add(pan5);
    add(pan1);
    add(pan4);
    add(pan12);
    add(pan7);
    add(pan8);
    add(pan2);
    add(pan9);
    add(pan6);
    add(pan10);
    
    setVisible(true);  
  }
  
  public void actionPerformed(ActionEvent event){   //check to see if button is pressed
    String command = event.getActionCommand();
    //declare variables
    ArrayList<String> words= new ArrayList<String>(); //DECLARING AN ARRAYLIST 
    int high;
    int row;
    int column;
    int ran1;
    int ran2;
    int randRow;
    int randCol;
    int wordSize;
    String currentWord;
    char randLetters[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}; //alphabet
    
    row = 0;
    column = 0; //initializing values
    
    if (command.equals("OK")){    
      
      String rowDim = getRow.getText();  //get row
      String columnDim = getColumn.getText();  //get column
      row= Integer.parseInt(rowDim);  //converts string to integer value
      column = Integer.parseInt(columnDim);//converts string to integer value
      
      if ((row<10)||(row>20)||(column<10)||(column>20)){ //if answer is invalid
        message3.setText("Invalid entry! Please try again.");     
      }
      else{ //if answer is valid
        message3.setText("Valid.");
        getRow.setEnabled(false);
        getColumn.setEnabled(false);
        namePuzzleFile2.setEnabled(false);
        htmlFileField.setEnabled(false);
        genButton.setEnabled(true);
        nameWordFile.setEnabled(true);   
        nameSolutionFile.setEnabled(true);   
        namePuzzleFile.setEnabled(true);  
        solveButton.setEnabled(false);
        okButton.setEnabled(false);
        reset.setEnabled(true);
        displayWords.setEnabled(false);  
        quit.setEnabled(true);
      }     
    }
    
    if (command.equals("QUIT")) //if quit button is pressed
    {
      genPuzzleButton.setEnabled(false); //disable buttons
      solvePuzzleButton.setEnabled(false);
      displayWords.setEnabled(false);
      okButton.setEnabled(false);
      solveButton.setEnabled(false);
      genButton.setEnabled(false);
      quit.setEnabled(false);
      reset.setEnabled(false);
      getRow.setEnabled(false);
      getColumn.setEnabled(false);
      nameWordFile.setEnabled(false);
      nameSolutionFile.setEnabled(false);
      namePuzzleFile.setEnabled(false);
      namePuzzleFile2.setEnabled(false);
      htmlFileField.setEnabled(false);
    }
    
    if (command.equals("RESET")) //if reset button is pressed, reset whole program
    { //empty all text fields, messages, disable buttons
      getRow.setEnabled(false); 
      getRow.setText("");
      getColumn.setEnabled(false);
      getColumn.setText("");
      okButton.setEnabled(false);
      nameWordFile.setEnabled(false);   
      nameSolutionFile.setEnabled(false);   
      namePuzzleFile.setEnabled(false);   
      displayWords.setEnabled(false);  
      namePuzzleFile2.setEnabled(false);
      htmlFileField.setEnabled(false);
      solveButton.setEnabled(false);
      genButton.setEnabled(false);
      reset.setEnabled(false);
      endMessage.setText("");
      endMessage2.setText("");
      message3.setText("");
      nameWordFile.setText("");
      nameSolutionFile.setText("");
      namePuzzleFile.setText("");
      namePuzzleFile2.setText("");
      htmlFileField.setText("");
      
      for(int i=0;i<showWords.length;i++){
        showWords[i].setText("");  
      }    
    }
    
    if(command.equals("DISPLAY WORDS")){       //if 'display word' button is pressed
      displayWords.setEnabled(false);
      String wordFile = nameWordFile.getText();  //gets word file name
      try{  
        //saving file in an arraylist
        File file = new java.io.File (wordFile); //create a file instance
        Scanner scan = new Scanner(file);
        while (scan.hasNext()){  //while there are still more words
          String getWord = scan.next();
          words.add(getWord); //adds the word to the arraylist        
        }
        scan.close(); //closes file
      }catch (IOException ie)
      {
        ie.printStackTrace();    
      }
      high=words.size(); //saves the size of the array
      
      for(int i=0;i<high;i++){
        showWords[i].setFont(new Font("Calibri Light", Font.ITALIC, 16)); 
        showWords[i].setText(words.get(i));  //prints words in the file
      }      
    }
    
    if (command.equals("GENERATE PUZZLE")){   //if 'generate puzzle' button is pressed
      
      namePuzzleFile2.setEnabled(false);
      htmlFileField.setEnabled(false);
      genButton.setEnabled(false);
      nameWordFile.setEnabled(false);   
      nameSolutionFile.setEnabled(false);   
      namePuzzleFile.setEnabled(false);  
      solveButton.setEnabled(false); 
      okButton.setEnabled(true);
      getRow.setEnabled(true);
      getColumn.setEnabled(true);  
      endMessage.setText("");
      endMessage2.setText(""); //reset previous messages to blank spaces
      message3.setText("");
      displayWords.setEnabled(false);
      
      for(int i=0;i<showWords.length;i++){
        showWords[i].setText("");  
      }  
    }
    
    if (command.equals("Generate!")){             //is generate puzzle is pressed
      
      nameWordFile.setEnabled(false);   
      nameSolutionFile.setEnabled(false);   
      namePuzzleFile.setEnabled(false);
      genButton.setEnabled(false);
      
      String wordFile = nameWordFile.getText();  //gets word file name
      String solutionFile = nameSolutionFile.getText(); //get solution file name
      String puzzleFile = namePuzzleFile.getText(); //get puzzle file name
      
      String rowDim2 = getRow.getText();  //get row
      String columnDim2 = getColumn.getText();  //get column
      row= Integer.parseInt(rowDim2);  //converts string to integer value
      column = Integer.parseInt(columnDim2);//converts string to integer value
      
      displayWords.setEnabled(true);
      endMessage.setText("Please check your files to view the generated puzzle.");
      endMessage2.setText("");
      try{  
        //saving file in an arraylist
        File file = new java.io.File (wordFile); //create a file instance
        Scanner scan = new Scanner(file);
        while (scan.hasNext()){  //while there are still more words
          String getWord = scan.next();
          words.add(getWord); //adds the word to the arraylist         
        }
        scan.close(); //closes file
        
      }catch (IOException ie)
      {
        ie.printStackTrace();      
      }
      high=words.size(); //saves the size of the array
      
      String word[]=new String[high];
      String changeToUpper;
      char grid[][]=new char[row][column];  //declare the board
      for (int i=0;i<word.length;i++){
        changeToUpper=words.get(i);
        word[i]=changeToUpper.toUpperCase(); //change word to uppercase
      }
      
      //initialize puzzle board
      for (int i=0;i<grid.length;i++){
        for (int j=0;j<grid[0].length;j++){
          grid[i][j]='*';  //entire board is stars
        }      
      }
      
      //writing solution puzzle to a file
      try{
        File myFile1 = new File(solutionFile); //creating a file (puzzle file)
        PrintWriter output1 = new PrintWriter(myFile1);   
        File myFile2 = new File(puzzleFile); //creating solution file
        PrintWriter output2 = new PrintWriter(myFile2);
        
        for(int i=0;i<words.size();i++){
          
          ran1=(int)(Math.random()*8)+1;  //generates random number
          randRow = (int)(Math.random()*row)+0;  //generates a random row number
          randCol = (int)(Math.random()*column)+0;  //generates a random column number
          
          currentWord=word[i];
          wordSize=currentWord.length();
          
          if (ran1==1){
            if (checkLeftToRightHorizontal(randRow, randCol, wordSize, currentWord, row, column, grid)==false){   // if word cannot be placed from left to right horizontally  
              i--; //go back to the same word
            }
            else{
              placeLeftToRightHorizontal (randRow,randCol, wordSize,currentWord, row,  column, grid);  //call method
            }
          }
          else if (ran1==2){
            if (checkRightToLeftHorizontal (randRow,randCol,  wordSize, currentWord, row, column, grid)==false){ // if word cannot be placed from right to left horizontally  
              i--;  //go back to the same word
            }
            else{
              placeRightToLeftHorizontal (randRow,randCol, wordSize,currentWord, row,  column, grid); //call method
            }       
          }
          else if (ran1==3){ 
            if (checkUpToDownVertically (randRow,randCol,wordSize, currentWord, row,column, grid)==false){ // if word cannot be placed from up to down vertically
              i--; //find another way to place the word
            }
            else{
              placeUpToDownVertically (randRow,randCol, wordSize,currentWord, row,column, grid);  //call method
            }  
          }
          else if (ran1==4){
            if (checkDownToUpVertically( randRow, randCol, wordSize,  currentWord, row, column,  grid)==false){  // if word cannot be placed from down to up vertically
              i--;   //find another way to place the word
            }
            else{
              placeDownToUpVertically (randRow,randCol, wordSize,currentWord, row,  column, grid);  //call method
            }
          }       
          else if (ran1==5){
            if (checkTopLeftToBottomRightDiagonally(randRow,randCol, wordSize, currentWord,row, column, grid)==false){  // if word cannot be placed from top left to bottom right diagonally
              i--;  //find another way to place the word
            }
            else{
              placeTopLeftToBottomRightDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid);   //call method
            }    
          }
          else if (ran1==6){
            if(checkTopRightToBottomLeftDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid)==false){  // if word cannot be placed from top right to bottom left diagonally   
              i--;  //find another way to place word
            }
            else{
              placeTopRightToBottomLeftDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid);  //call method
            }          
          }        
          else if(ran1==7){           
            if (checkBottomLeftToTopRightDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid)==false){ // if word cannot be placed from bottom left to top right diagonally     
              i--; //find another way to place word
            }
            else{
              placeBottomLeftToTopRightDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid); //call method
            }
            
          }          
          else{  //if random number is 8           
            if (checkBottomRightToTopLeftDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid)==false){  // if word cannot be placed from bottom right to top left diagonally  
              i--; //find another way to place word
            }
            else{
              placeBottomRightToTopLeftDiagonally(randRow,randCol, wordSize,currentWord, row,  column, grid);  //call method
            }
          }
        }
        
        //without stars
        for (int x=0;x<grid.length;x++){
          for (int j=0;j<grid[0].length;j++){
            if (grid[x][j]=='*'){
              grid[x][j]='\0';  //add a blank if the grid space is a star
            }           
          }
        }
        
        //writing to a file
        for (int x=0;x<grid.length;x++){
          for (int j=0;j<grid[0].length;j++){
            output1.print(grid[x][j]); 
          }
          output1.println("");
        }
        
        //adding random letters to the blank spaces (fill up board)
        for (int x=0;x<grid.length;x++){
          for (int j=0;j<grid[0].length;j++){
            ran2=(int)(Math.random()*25)+0;  //generates random number
            if (grid[x][j]=='\0'){  //if the space is a blank
              grid[x][j]=randLetters[ran2]; //place a random letter in the space
            }
          }
          
        }
        
        //write new grid to a file
        for (int x=0;x<grid.length;x++){
          for (int j=0;j<grid[0].length;j++){
            output2.print(grid[x][j]);  //write to a file
          }
          output2.println("");
        }
        output1.close();  //closes file
        output2.close();
      }catch (IOException ie)
      {
        ie.printStackTrace();     
      }
    }
    
    if (command.equals("SOLVE PUZZLE")){    //if 'solve puzzle' is pressed
      
      displayWords.setEnabled(false); 
      nameWordFile.setEnabled(true);  
      nameSolutionFile.setEnabled(false);   
      namePuzzleFile.setEnabled(false);   
      namePuzzleFile2.setEnabled(true);
      htmlFileField.setEnabled(true);
      solveButton.setEnabled(true);
      genButton.setEnabled(false);
      quit.setEnabled(true);
      endMessage.setText(""); //reset previous messages to blank spaces
      endMessage2.setText("");
      reset.setEnabled(true);      
      okButton.setEnabled(false);
      getRow.setEnabled(false);
      getColumn.setEnabled(false);
      
      for(int i=0;i<showWords.length;i++){
        showWords[i].setText("");  
      }  
      
    }
    
    if (command.equals("Solve!")){     //if solve puzzle is pressed
      
      nameWordFile.setEnabled(false); //disable text fields
      namePuzzleFile2.setEnabled(false);
      htmlFileField.setEnabled(false);
      solveButton.setEnabled(false);
      displayWords.setEnabled(true); //enable 'display words' button
      
      String wordFile = nameWordFile.getText();  //gets word file name 
      String puzzleHtml = namePuzzleFile2.getText();  //get puzzle file to write to html file
      String writeToHtmlFile = htmlFileField.getText();  //get file name of html
      
      long startTime, endTime;
      
      int rows = 0, columns = 0;
      int times = 0;
      
      ArrayList<String> rowinputs = new ArrayList<String>();
      
      startTime = Calendar.getInstance ().getTimeInMillis ();
      
      try //FILE HANDLING
      {      
        BufferedReader input, puzzleinput;
        String line, line2;
        puzzleinput = new BufferedReader (new FileReader (puzzleHtml)); //words puzzle (puzzle to write to html file)
        line2 = puzzleinput.readLine();
        columns = line2.length();
        
        while (line2!=null)
        {
          rowinputs.add(line2.toUpperCase());
          line2 = puzzleinput.readLine();
          rows++;      
        }
        
        puzzleinput.close();
        
        input = new BufferedReader (new FileReader (wordFile)); //words file
        line = input.readLine(); //getting input from file
        
        while (line!=null) //while line is not empty
        {
          words.add(line.toUpperCase()); //add line to array list
          line = input.readLine(); //read next line
        }
        
        input.close(); //close input
        
      } catch (IOException ie)
      {
        ie.printStackTrace();
      }
      
      char[][] puzzlegrid = new char [rows][columns];
      char puzzlesolutions[][]= new char [rows][columns];
      
      for (int i = 0; i < rows; i++)
      {
        for (int j = 0; j < columns; j++)
        {
          puzzlegrid[i][j] = rowinputs.get(i).charAt(j); 
        }
      }
      
      //HORIZONTAL FROM LEFT TO RIGHT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i < puzzlegrid.length; i ++)
        {
          for (int j = 0; j < puzzlegrid[0].length; j++)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if ((puzzlegrid[0].length-(j+1))+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i][j+m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i][j+k] = Character.toLowerCase(puzzlegrid[i][j+k]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      //HORIZONTAL FROM RIGHT TO LEFT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word    
        for (int i = 0; i < puzzlegrid.length; i ++)
        {
          for (int j = puzzlegrid[0].length-1; j >=0; j--)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if ((j+1) >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i][j-m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i][j-k] = Character.toLowerCase(puzzlegrid[i][j-k]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      
      //VERTICAL CHECK UP TO DOWN
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i < puzzlegrid.length; i++)
        {
          for (int j = 0; j < puzzlegrid[0].length; j++)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if ((puzzlegrid.length-(i+1))+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i+m][j] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i+k][j] = Character.toLowerCase(puzzlegrid[i+k][j]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      // VERTICAL CHECK DOWN TO UP
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = puzzlegrid.length-1; i>=0; i--)
        {
          for (int j = 0; j < puzzlegrid[0].length; j++)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if (i+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i-m][j] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i-k][j] = Character.toLowerCase(puzzlegrid[i-k][j]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      
      //DIAGONAL CHECK FROM TOP LEFT TO BOTTOM RIGHT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i< puzzlegrid.length; i++)
        {
          for (int j = 0; j < puzzlegrid[0].length; j++)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if ((puzzlegrid.length-(i+1))+1 >= words.get(s).length() && (puzzlegrid.length-(j+1))+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i+m][j+m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i+k][j+k] = Character.toLowerCase(puzzlegrid[i+k][j+k]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      //DIAGONAL CHECK FROM TOP RIGHT TO BOTTOM LEFT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i< puzzlegrid.length; i++)
        {
          for (int j = puzzlegrid[0].length-1; j >= 0 ; j--)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if ((puzzlegrid.length-(i+1))+1 >= words.get(s).length() && (j+1) >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i+m][j-m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }
                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i+k][j-k] = Character.toLowerCase(puzzlegrid[i+k][j-k]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      //DIAGONAL CHECK FROM BOTTOM RIGHT TO TOP LEFT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i< puzzlegrid.length; i++)
        {
          for (int j = puzzlegrid[0].length-1; j >= 0 ; j--)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if (i+1 >= words.get(s).length() && j+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i-m][j-m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }             
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i-k][j-k] = Character.toLowerCase(puzzlegrid[i-k][j-k]); //assign solution to the solution array 
                  }
                  times = 0;
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      //DIAGONAL CHECK FROM BOTTOM LEFT TO TOP RIGHT
      
      for (int s = 0; s < words.size(); s++)
      {
        times = 0; //counter needs to reset after each word
        for (int i = 0; i< puzzlegrid.length; i++)
        {
          for (int j = puzzlegrid[0].length-1; j >= 0 ; j--)
          {
            if (puzzlegrid[i][j] == words.get(s).charAt(0))
            {
              if (i+1 >= words.get(s).length() && (puzzlegrid[0].length-(j+1))+1 >= words.get(s).length()) //validates that there is enough space for a word to be placed in this position
              {
                for (int m= 1; m < words.get(s).length(); m++)
                {
                  if (puzzlegrid[i-m][j+m] == words.get(s).charAt(m))
                  {
                    times++;
                  }
                }                
                if (times == words.get(s).length()-1)
                {
                  for (int k = 0; k <= times; k++)
                  {
                    puzzlesolutions[i-k][j+k] = Character.toLowerCase(puzzlegrid[i-k][j+k]); //assign solution to the solution array 
                  }
                  times = 0; 
                }
                else
                {
                  times = 0;
                }
              }
            }
          }
        }
      }
      
      for (int i = 0; i < puzzlesolutions.length; i ++)
      {
        for (int j = 0; j < puzzlesolutions[0].length; j++)
        {
          if (puzzlesolutions[i][j] == 0)
          {
            puzzlesolutions[i][j] = puzzlegrid[i][j];
          }
        }
      }   
      
      try
      {  
        File sol = new java.io.File (writeToHtmlFile);
        PrintWriter html_output = new PrintWriter(sol);
        
        html_output.println ("<html>");
        html_output.println("<head>");
        html_output.println("<style>");
        html_output.println("\t\t\ttable, th, td { border: 1px solid black; border-collapse: collapse; }");
        html_output.println("</style>");
        html_output.println("<h1>" + writeToHtmlFile + "</h1>");
        html_output.println("</head>");
        html_output.println("<body>");
        html_output.println("<table>");
        
        //Go through the 2D array of letters   
        for (int i = 0 ; i < puzzlesolutions.length; i++)
        {
          html_output.println ("<tr>");//Table row tag
          for (int j = 0 ; j < puzzlesolutions[0].length; j++)
          {
            //if solution if lowercase, bold and highlight character
            if (Character.isLowerCase(puzzlesolutions[i][j]))
            {
              html_output.print("<td>" + "<b>" + "<mark style=background-color:plum>" + Character.toUpperCase(puzzlesolutions[i][j]) + "</mark>" + "</b>" + "</td>");
            }  
            else
            {
              html_output.print("<td>" + Character.toUpperCase(puzzlesolutions[i][j]) + "</td>");  
            }
          }
          html_output.println("</tr>");
        }
        html_output.println ("</table></body></html>");        
        html_output.close();
        
        endTime = Calendar.getInstance().getTimeInMillis() - startTime; //calculates runtime
        
        endMessage2.setText ("The time taken in milli seconds to solve the word search problem is: "+ endTime); //outputs runtime
        endMessage.setText("Please check your files to view the solved puzzle.");
        
        endTime = 0; 
        
      }catch (IOException ie)
      {
        ie.printStackTrace();
      }
    }
  }
  
  public static void main(String args[]) {
    WordSearchPuzzle frame1 = new WordSearchPuzzle();
  }
  public static boolean checkLeftToRightHorizontal(int randRow, int randCol, int wordSize,String currentWord, int row, int column, char grid[][]){
    boolean check;
    int sum=0;
    check=false;
    int checkCol=randCol;
    sum=randCol+wordSize;
    
    if (sum<=column){
      
      check=true;   //if there is enough space to place word 
      for (int y=0;y<wordSize;y++){  
        if (grid[randRow][checkCol]=='*'){ //checks to see if a word is already placed
          check=true;   //if there is enough space to place word 
          checkCol++;
        }
        else{
          check=false; 
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
      
    }
    if (check==true){
      return true;   //returns true if the word can be placed in the grid
    }
    else{
      return false;   //returns false if the word cannot be placed in the grid
    }
  }
  
  public static void placeLeftToRightHorizontal (int randRow, int randCol, int wordSize,String currentWord, int row, int column, char grid[][]){ 
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randCol++;
    }  
  }
  
  public static boolean checkRightToLeftHorizontal (int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check;
    int sum=0;
    int checkCol=randCol;
    check=false;
    sum=randCol-wordSize;
    if (sum>=0){
      for (int y=0;y<wordSize;y++){   
        if (grid[randRow][checkCol]=='*'){ //checks to see if a word is already placed
          checkCol--;
          check=true;   //if there is enough space to place word 
        }
        else{
          check=false;
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
    }
    if (check==true){
      return true; //word can be placed
    }
    else{
      return false;  //word cannot be placed 
    }
  }
  public static void placeRightToLeftHorizontal (int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randCol--; //goes back a column
    }
  }
  public static boolean checkUpToDownVertically(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check;
    int sum=0;
    int checkRow=randRow;
    check=false;
    sum=randRow+wordSize;
    if (sum<=row){
      for (int y=0;y<wordSize;y++){   
        if (grid[checkRow][randCol]=='*'){ //checks to see if a word is already placed
          checkRow++;
          check=true;   //if there is enough space to place word 
        }
        else{
          check=false;
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
    }
    
    if (check==true){
      return true; //if the word can be placed      
    }
    else{
      return false; //if the word cannot be placed in the grid
    }   
  }
  
  public static void placeUpToDownVertically(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){   
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow++; //goes down a row
    }   
  }
  
  public static boolean checkDownToUpVertically(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check=false;
    int sum=0;
    int checkRow=randRow;
    sum=randRow-wordSize;
    if (sum>=0){
      for (int y=0;y<wordSize;y++){   //checks to see if a word is already placed
        if (grid[checkRow][randCol]=='*'){ 
          checkRow--;  //moves up a row
          check=true;   //if there is enough space to place word 
        }
        else{
          check=false;   //if the word cannot be placed on the grid
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
      
    }
    if (check==true){
      return true; //if the word can be placed in the grid 
    }
    else{
      return false; //if the word cannot be placed in the grid 
    }
  }
  
  public static void placeDownToUpVertically(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){  
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow--; //moves 1 row up
    }
  }
  
  public static boolean checkTopLeftToBottomRightDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check=false; 
    
    int sum1=0;
    int sum2=0;
    int checkRow=randRow;
    int checkCol=randCol;
    
    sum1=randRow+wordSize;
    sum2=randCol+wordSize;
    
    if ((sum1<=row) && (sum2<=column)){
      for (int y=0;y<wordSize;y++){   //checks to see if a word is already placed
        if (grid[checkRow][checkCol]=='*'){ 
          checkRow++;  //moves up a row
          checkCol++;
          check=true;   //if there is enough space to place word 
        }
        else{
          check=false;   //if the word cannot be placed on the grid
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word     
    }
    if (check==true){
      return true; //word can be placed into the grid 
    }
    else{
      return false; //word cannot be placed into the grid
    }
  }
  
  public static void placeTopLeftToBottomRightDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){   
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow++; //moves 1 row up
      randCol++;
    }
  }
  
  public static boolean checkTopRightToBottomLeftDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check=false;
    int sum1=0;
    int sum2=0;
    int checkRow=randRow;
    int checkCol=randCol;
    
    sum1=randRow+wordSize;
    sum2=randCol-wordSize;
    
    if ((sum1<=row) && (sum2>=0)){
      for (int y=0;y<wordSize;y++){   //checks to see if a word is already placed
        if (grid[checkRow][checkCol]=='*'){ 
          checkRow++;  //moves up a row
          checkCol--;
          check=true;   //if there is enough space to place word 
        }
        else{
          check=false;   //if the word cannot be placed on the grid
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word 
    }
    if (check==true){
      return true; //if there is enough space place word 
    }
    else{
      return false; //not enough space to place word 
    }
  }
  
  public static void placeTopRightToBottomLeftDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){ 
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow++; //moves 1 row up
      randCol--;
    }    
  }
  
  public static boolean checkBottomLeftToTopRightDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    
    boolean check=false;
    
    int sum1=0;
    int sum2=0;
    int checkRow=randRow;
    int checkCol=randCol;
    
    sum1=randRow-wordSize;
    sum2=randCol+wordSize;
    
    if ((sum1>=0) && (sum2<=column)){
      for (int y=0;y<wordSize;y++){   //checks to see if a word is already placed
        if (grid[checkRow][checkCol]=='*'){ 
          checkRow--;  //moves up a row
          checkCol++;
          check=true;   //if there is enough space to place word(No letters already in the spaces that the word will be placed) 
        }
        else{
          check=false;   //if the word cannot be placed on the grid
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
    }
    if (check==true){
      return true; //word can be placed in grid 
    }
    else{
      return false; //word cannot be placed in grid 
    }
  }
  
  public static void placeBottomLeftToTopRightDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){  
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow--; //moves 1 row up
      randCol++;
    }
  }
  
  public static boolean checkBottomRightToTopLeftDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    boolean check=false;
    int sum1=0;
    int sum2=0;
    int checkRow=randRow;
    int checkCol=randCol;
    
    sum1=randRow-wordSize;
    sum2=randCol-wordSize;
    
    if ((sum1>=0) && (sum2>=0)){
      for (int y=0;y<wordSize;y++){   //checks to see if a word is already placed
        if (grid[checkRow][checkCol]=='*'){ 
          checkRow--;  //moves up a row
          checkCol--;
          check=true;   //if there is enough space to place word (No letters already in the spaces that the word will be placed) 
        }
        else{
          check=false;   //if the word cannot be placed on the grid
          break;
        }
      }
    }
    else {
      check=false;  //if there is not enough space to place word
    }
    if (check==true){
      return true; //word can be placed in grid 
    }
    else{
      return false; //word cannot be placed in grid 
    }
  }
  
  public static void placeBottomRightToTopLeftDiagonally(int randRow, int randCol, int wordSize, String currentWord, int row, int column, char grid[][]){
    for (int y=0;y<wordSize;y++){
      grid[randRow][randCol]=currentWord.charAt(y); //writes word on grid
      randRow--; //moves 1 row up
      randCol--; //moves 1 column left
    }
  }
}