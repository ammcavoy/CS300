//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Auditable Banking Tests
// Files: AuditableBankingTest
// Course: CS 300, Fall, 2018
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
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.*;

public class AuditableBankingTests {


  public static void main(String[] args) {
    testProcessCommand();
    testCalculateCurrentBalance();
    testCalculateNumberOfOverdrafts();
  }
  /**
   * creates an empty int[][] called transactions, a command string, and an int transactionCount
   * to keep track of the transcations. It then passes them to processCommand(). If the values in
   * the int[][] array match the intended values, and it properly increments transactoinsCount
   * it will have passed the first test. It then passes several other commands into the method
   * and on the fourth tests the array values, and the value of transactionsCount. If they are the
   * expected values again it has passed both test.  
   * 
   * @return true if a problem was found false if no problem was found. 
   */
  public static boolean testProcessCommand() {
    boolean foundProblem = false;
    int[][] transactions = new int[10][10];
    String command = "2 0 0 0 5";
    int transactionsCount = 0;
    transactionsCount = AuditableBanking.processCommand(command, transactions, transactionsCount);
    int testValue = transactions[0][0];
    if (transactionsCount != 1) {
      System.out.println("FAILURE: ProcessCommand did not return 1 "
          + "when transactionCount = 1, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else if (testValue != 2) {
      System.out.println("FAILURE: ProcessCommand did not change the test Value to 2 "
          + "when transactionCount = 1, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TEST 1/2 of TestProcessCommand!!!");
    command = "0 0 0 0 0 0 1 0 1";
    transactionsCount = AuditableBanking.processCommand(command, transactions, transactionsCount);
    transactionsCount = AuditableBanking.processCommand(command, transactions, transactionsCount);
    command = "1 22 33 44 55";
    transactionsCount = AuditableBanking.processCommand(command, transactions, transactionsCount);
    testValue = transactions[3][1];
    if (transactionsCount != 4) {
      System.out.println("FAILURE: ProcessCommand did not return 4 "
          + "when transactionCount = 4, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else if (testValue != 22) {
      System.out.println("FAILURE: ProcessCommand did not change the test Value to 22 "
          + "when transactionCount = 4, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TESTS 1/2 of TestProcessCommand!!!");
    return !foundProblem;
  }
  /**
   * creates an int[][] transactions full of values, and an int transactionsCount, these are then
   * passes to calculateCurrentBalance, and if the method returns the correct balance for the 
   * first transaction it passes the first test. transactionsCount is then changed to 4 and passed
   * to calculateCurrentBalance again, and if it returns the correct balance again it has passed 
   * the second test.
   * 
   * @return true if a problem was found false if no problem was found. 
   */
  public static boolean testCalculateCurrentBalance() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, 
        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, 
        {1, 115}, 
        {2, 3, 1, 0, 1}, 
    };
    int transactionsCount = 1;
    int balance = AuditableBanking.calculateCurrentBalance(transactions, transactionsCount);
    if (balance != -20) {
      System.out.println("FAILURE: calculateCurrentBalance did not return -10 "
          + "when transactionCount = 1, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TEST 1/2 of TestCalculateCurrentBalnace!!!");
    transactionsCount = 4;
    balance = AuditableBanking.calculateCurrentBalance(transactions, transactionsCount);
    if (balance != -100) {
      System.out.println("FAILURE: calculateCurrentBalnace did not return -100 "
          + "when transactionCount = 4, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TESTS 2/2 of TestCalculateCurrentBalance!!!");

    return !foundProblem;
  }
  /**
   * creates an int[][] transactions full of values, and an int transactionsCount, these are then
   * passes to calculateNumberOfOverdrafts, and if the method returns the correct number of 
   * overdrafts for the first transaction it passes the first test. transactionsCount is then 
   * changed to 4 and passed to calculateNumberOfOverdrafts again, and if it returns the correct
   * number of overdrafts again it has passed the second test.
   * 
   * @return true if a problem was found false if no problem was found. 
   */

  public static boolean testCalculateNumberOfOverdrafts() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, 
        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, 
        {1, 115}, 
        {2, 3, 1, 0, 1}, 
    };

    // test with a single transaction of the Integer Amount encoding
    int transactionCount = 1;
    int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 2) {
      System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 "
          + "when transactionCount = 1, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");

    // test with four transactions: including one of each encoding
    transactionCount = 4;
    overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 5) {
      System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 "
          + "when transactionCount = 4, and transactions contained: "
          + Arrays.deepToString(transactions));
      foundProblem = true;
    } else
      System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");

    return !foundProblem;
  }
}
