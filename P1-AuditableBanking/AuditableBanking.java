//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Auditable Banking
// Files: AuditableBanking.java
// Course: CS 300, fall, 2018
//
// Author: Adam McAvoy
// Email: ammcavoy@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE /////////////////////////////// 
import java.util.*;

public class AuditableBanking {
  // Constant values for the quick withdrawals. 
  final public static int QUICK_WITHDRAW_ONE_CONSTANT = 20;
  final public static int QUICK_WITHDRAW_TWO_CONSTANT = 40;
  final public static int QUICK_WITHDRAW_THREE_CONSTANT = 80;
  final public static int QUICK_WITHDRAW_FOUR_CONSTANT = 100;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String userInput = "";
    boolean continueRunning = true;
    // empty oversized array where users transaction input will be stored.
    int[][] allTransactions = new int[50][50];
    //counter for each user transaction.
    int transactionsCount = 0;
    System.out.println("========== Welcome to the Auditable Banking App ==========");
    while (continueRunning) {
      //prints user interface
      printCommandMenu();
      userInput = in.nextLine();
      if (userInput.equalsIgnoreCase("Q"))
        continueRunning = false;
      else if (userInput.equalsIgnoreCase("B"))
        // determines the current balance using the allTransactions array and counter. 
        System.out.println(
            "Current Balance: " + calculateCurrentBalance(allTransactions, transactionsCount)); 
      else if (userInput.equalsIgnoreCase("O"))
        // determines the number of overdrafts using the allTransactions array and counter.
        System.out.println("Number of Overdrafts: "
            + calculateNumberOfOverdrafts(allTransactions, transactionsCount)); 
      else
        // sends the user input string to be processed and inputed to the array int values. 
        transactionsCount = processCommand(userInput, allTransactions, transactionsCount);
    }
    System.out.println("============ Thank you for using this App!!!! ============");
  }
  /**
   * Prints out the command menu for the user. 
   */
  public static void printCommandMenu() {
    System.out.println();
    System.out.println("COMMAND MENU:");
    System.out
        .println("\t Submit a Transaction (enter a sequence of integers seperated by spaces)"); 
    System.out.println("\t Show Current [B]alance");
    System.out.println("\t Show Number of [O]verdrafts");
    System.out.println("\t [Q]uit Program");
    System.out.print("ENTER COMMAND: ");
  }

  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full then this method will do nothing other than return allTransactionCount.          // should it increase the value of allTransactions count?
   * 
   * @param newTransactions is the new transaction group being added (perfect size).
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int submitTransactions(int[] newTransactions, int[][] allTransactions, 
      int allTransactionsCount) {
    int newTransactionsSize = newTransactions.length;
    if (allTransactionsCount < allTransactions.length) {
      // loops through oversized array adding values from newTransactions.
      for (int index = 0; index < newTransactionsSize; index++)
        allTransactions[allTransactionsCount][index] = newTransactions[index];
    }
    // adds a -1 at the end of transactions with a leading 0 to indicate the end of a transaction.
    if (newTransactions[0] == 0)
      allTransactions[allTransactionsCount][newTransactionsSize] = -1;
    allTransactionsCount++;
    return allTransactionsCount;
  }
  /**
   * Takes the user given command as a string, converts it to an integer array. It then passes the 
   * new array, allTransations, and allTransactionsCount to the submitTransations method, where 
   * it will determine if allTransactions has space for the new transaction and if it does will add
   * it to the allTransaction array. 
   * 
   * @param command is the user given command taken as a sting. 
   * @param allTransactions is the collection that will be passed to submitTransactions (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        the current transaction).
   * @return .
   */
  public static int processCommand(String command, int[][] allTransactions, 
      int allTransactionsCount) {
    command.trim();
    String[] stringTransactions = command.split(" ");
    int[] intTransactions = new int[stringTransactions.length];
    // transfers the string values of the transaction to an int array
    for (int index = 0; index < stringTransactions.length; index++) {
      String stringTransaction = stringTransactions[index];
      stringTransaction.trim();
      int intTransaction = Integer.parseInt(stringTransaction);
      intTransactions[index] = intTransaction;
    }
    // calls the submitTransactions method to put the ints into the oversized array.
    return submitTransactions(intTransactions, allTransactions, allTransactionsCount);
  }
  /**
   * searches through allTransactionsCount rows of the allTransactions array, reading the first
   * value in each row to determine how to interpret the following values. For each leading value
   * there is a different algorithm to determine the changes to the balance. 
   * 
   * @param allTransactions is the collection of all transactions where the balance will be 
   * calculated from (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions.
   * @return the calculated account balance.
   */
  public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {  
    int balance = 0;
    for (int index = 0; index < allTransactionsCount; index++) {
      if (allTransactions[index][0] == 0) {
        int counter = 1;
        while (counter < allTransactions[index].length && allTransactions[index][counter] != -1) {
          if (allTransactions[index][counter] == 1)
            balance++;
          else if (allTransactions[index][counter] == 0)
            balance--;
          counter++;
        }
      } else if (allTransactions[index][0] == 1) {
        int counter = 1;
        while (counter < allTransactions[index].length && allTransactions[index][counter] != 0) {
          balance = balance + allTransactions[index][counter];
          counter++;
        }
      } else if (allTransactions[index][0] == 2) {
        balance -= allTransactions[index][1] * QUICK_WITHDRAW_ONE_CONSTANT;
        balance -= allTransactions[index][2] * QUICK_WITHDRAW_TWO_CONSTANT;
        balance -= allTransactions[index][3] * QUICK_WITHDRAW_THREE_CONSTANT;
        balance -= allTransactions[index][4] * QUICK_WITHDRAW_FOUR_CONSTANT;
      }
    }
    return balance;
  }
  /**
   * Searches through the allTransactions array allTransactsionsCount times, and using the same 
   * search and calculations as calculateCurrentBalance, it determines the balance after each
   * transaction and if the transaction was a withdrawal and resulted in a negative balance then 
   * the OverdraftsCount value is increased. 
   * 
   * @param command is the user given command taken as a sting. 
   * @param allTransactions is the collection that will be passed to submitTransactions (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        the current transaction).
   * @return .
   */
  public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
    int OverdraftsCount = 0;
    int balance = 0;
    for (int index = 0; index < allTransactionsCount; index++) {
      if (allTransactions[index][0] == 0) {
        int counter = 1;
        while (counter < allTransactions[index].length && allTransactions[index][counter] != -1) {
          if (allTransactions[index][counter] == 1)
            balance++;
          else if (allTransactions[index][counter] == 0) {
            balance--;
            if (balance < 0)
              OverdraftsCount++;
          }
          counter++;
        }
      } else if (allTransactions[index][0] == 1) {
        int counter = 1;
        while (counter < allTransactions[index].length && allTransactions[index][counter] != 0) {
          balance = balance + allTransactions[index][counter];
          if (balance < 0 && allTransactions[index][counter] < 0)
            OverdraftsCount++;
          counter++;
        }
      } else if (allTransactions[index][0] == 2) {
        for (int counter = 0; counter < allTransactions[index][1]; counter++) {
          balance -= QUICK_WITHDRAW_ONE_CONSTANT;
          if (balance < 0)
            OverdraftsCount++;
        }
        for (int counter = 0; counter < allTransactions[index][2]; counter++) {
          balance -= QUICK_WITHDRAW_TWO_CONSTANT;
          if (balance < 0)
            OverdraftsCount++;
        }
        for (int counter = 0; counter < allTransactions[index][3]; counter++) {
          balance -= QUICK_WITHDRAW_THREE_CONSTANT;
          if (balance < 0)
            OverdraftsCount++;
        }
        for (int counter = 0; counter < allTransactions[index][4]; counter++) {
          balance -= QUICK_WITHDRAW_FOUR_CONSTANT;
          if (balance < 0)
            OverdraftsCount++;
        }
      }
    }
    return OverdraftsCount;
  }

}
