package TicTacToe;


import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions


/** This class ...
  *
  * @author Aman Braich
  * @version 1.0 March 19th, 2019                                                        */

public class TicTacToe {
  //Declaring the Variables and Basic io UI
  private ASCIIDisplayer boardDisplayer;
  private ASCIIPrompter playerInput;
  private char [][] board = new char[3][3];
  int moveNum = 0;
  
  public TicTacToe ( ) {
    //Creating instances and calling meathods 
    boardDisplayer = new ASCIIDisplayer();
    buildArray();
    printBoard();
    playerInput = new ASCIIPrompter();
    
    // forever loop that keeps game going and breaks after either win or tie 
    for ( ; ; ){
      playO();
      moveNum++;
      if (win('O') == true){
        boardDisplayer.writeString("O WINS!!!!");
        break;
      }
      playX(); 
      moveNum++;
      if (win('X') == true){
        boardDisplayer.writeString("X WINS!!!!");
        break;
      }
      
      if (moveNum > 9){
        boardDisplayer.writeString("TIE GAME!!!");
          break;
      }
    }
    
  };//TicTacToe
  
  
  //Meathod for the cpu to make the best move based off the weight recived back from the moves done by the player 
  private void playX ( ) {
    int max = -1000000000;
    int val;
    int row = 0;
    int column = 0;
    for (int r=0 ; r<3 ; r++ ){
      for (int c=0 ; c<3 ; c++ ){
        if (board[r][c] ==  ' '){
          board[r][c] = 'X';
          val = eval(moveNum , 1000000000 , 'O');
          board[r][c] = ' ';
          if ( val > max ){
            max = val;
            row = r;
            column = c;
          }
        }
      }
    }
    board[row][column] = 'X';
    printBoard(); 
  }//PlayX
  
  //meathod that evaluates the weight and returns the new one based off the old moves 
  private int eval ( int move, int weight, char player ) {
    int newWeight = 0;
    if (win('X')){
      return weight;
    }else if (win(player)){
      return -weight;
    }else if (move>9){
      return 0;
    }else {
      for (int r=0 ; r<3 ; r++ ){
        for (int c=0 ; c<3 ; c++ ){
          if (board[r][c]==' '){
            if (player == 'O'){
              board[r][c] = 'O';
              newWeight += eval(move + 1, weight/10, 'X');
            }else if (player == 'X'){
              board[r][c] = 'x';
              newWeight += eval(move + 1, weight/10, 'O');
            }
            board[r][c]= ' ';
          }
        }
      }
    }
    return newWeight;
  }//Eval
  
  //Checks the wins on the boards for the given player either X or O 
  private boolean win ( char player ) {
    //Checks Rows 
    for (int i=0; i<=2; i++){
      int j = 0;
      if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player){
        return true;
      }
    }
    //Checks Columns 
    for (int j=0; j<=2; j++){
      int i = 0;
      if (board[i][j] == player && board[i+1][j] == player && board[i+2][j] == player){
        return true;
      }
    }
    //Checks top left down Diagonal 
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player){
      return true;
    }
    //Checks top right down Diagonal 
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player){
      return true;
    }
    return false;
  }//Win 
  
  //Meathod that uses the prompter for the player to enter the coordinates to play if they are in the correct range
  private void playO ( ) {
    int r, c;
    playerInput.setLabel("O Turn-Y");
    r = playerInput.readInt();
    playerInput.setLabel("O Turn-X");
    c = playerInput.readInt();
    if ( r <= 2 && r >= 0 && c <= 2 && c >= 0){
      if (board[r][c] == ' '){ 
        board[r][c]='O'; 
        printBoard();
      }else playO();
    }else playO();
  }//PlayO
  
  //Builds 3x3 char array 
  private char[][] buildArray(){
    for (int i = 0; i <= 2; i++){
      for (int j = 0; j <= 2; j++) {
        board[i][j] = ' ';
      }
    }
    return board;
  }//buildArray 
  
  //Prints visual board with empty char values where either X or O can be placed 
  private void printBoard () {
    boardDisplayer.writeString(board[0][0]+" | " + board[0][1] + " | " + board[0][2]);
    boardDisplayer.newLine();
    boardDisplayer.writeString("- + - + -");
    boardDisplayer.newLine();
    boardDisplayer.writeString(board[1][0]+" | " + board[1][1] + " | " + board[1][2]);
    boardDisplayer.newLine();
    boardDisplayer.writeString("- + - + -");
    boardDisplayer.newLine();
    boardDisplayer.writeString(board[2][0]+" | " + board[2][1] + " | " + board[2][2]);
    boardDisplayer.newLine();
    boardDisplayer.newLine();
  }
  
  public static void main ( String[] args ) { TicTacToe c = new TicTacToe(); };  
} //TicTacToe
