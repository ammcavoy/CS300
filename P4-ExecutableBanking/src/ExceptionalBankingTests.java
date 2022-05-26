//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Exceptional Banking Tests
// Files: ExceptionalBankingTests.java
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;

public class ExceptionalBankingTests {
  public static void main(String[] args) throws DataFormatException {
    testAccountBalance(); //calls all the test methods
    testOverdraftCount();
    testTransactionGroupEmpty();
    testTransactionGroupInvalidEncoding();
    testAccountAddNegativeQuickWithdraw();
    testAccountBadTransactionGroup();
    testAccountIndexOutOfBounds();
    testTransactionGroupIndexOutOfBounds();
    testAccountMissingFile();

  }

  
  /**
   * creates an int[][] transactions full of values, and an int transactionsCount, these are then
   * passes to calculateCurrentBalance, and if the method returns the correct balance for the 
   * first transaction it passes the first test. transactionsCount is then changed to 4 and passed
   * to calculateCurrentBalance again, and if it returns the correct balance again it has passed 
   * the second test.
   * 
   * @return true if a problem was found false if no problem was found. 
   * @throws DataFormatException 
   */
  public static boolean testAccountBalance() throws DataFormatException {
    boolean problemFound = false;
    Account a = new Account("account1"); //creates new testAccount
    String transaction1 = "1 10 -20 30 -10 30"; //creates new string to be used as new transaction
    a.addTransactionGroup(transaction1); //creates new transaction
    int balance = a.getCurrentBalance();  //gets the program calculated balance
    if(balance != 40) { //determines if expected value was returned and prints response
      System.out.println("FAILURE: getCurrentBalance did not return 40 after "
          + transaction1 + " was added as a transaction group.");
      problemFound = true;
    } else
      System.out.println("PASSED TESTS 1/2 of TestAccountBalance!!!");
    //runs the same as test one but different values
    String transaction2 = "0 1 1 1 0 0 1 1 1 1";
    a.addTransactionGroup(transaction2);
    balance = a.getCurrentBalance();
    if(balance != 45) {
      System.out.println("FAILURE: getCurrentBalance did not return 45 after "
          + transaction1 + " was added as a transaction group.");
      problemFound = true;
    } else
      System.out.println("PASSED TESTS 2/2 of TestAccountBalance!!!");
    
    return !problemFound;
  }
  /**
   * creates an int[][] transactions full of values, and an int transactionsCount, these are then
   * passes to calculateNumberOfOverdrafts, and if the method returns the correct number of 
   * overdrafts for the first transaction it passes the first test. transactionsCount is then 
   * changed to 4 and passed to calculateNumberOfOverdrafts again, and if it returns the correct
   * number of overdrafts again it has passed the second test.
   * 
   * @return true if a problem was found false if no problem was found. 
   * @throws DataFormatException 
   */

  public static boolean testOverdraftCount() throws DataFormatException {
    boolean problemFound = false;  
    Account a = new Account("account1");  //creates a new testAccount
    String transaction1 = "1 10 -20 30 -10 30";  //new transaction string 
    a.addTransactionGroup(transaction1);  //adds transaction 
    int overdrafts = a.getNumberOfOverdrafts(); //calls the overdraft method
    if(overdrafts != 1) {  //determines if the expected value was returned
      System.out.println("FAILURE: getNumberOfOverdrafts did not return 1 after " 
          + transaction1 + " was added as a transaction group.");
      problemFound = true;
    } else
      System.out.println("PASSED TESTS 1/3 of testOverdraftCount!!!");
    //runs the same as test one but with different values
    String transaction2 = "1 -40 -10 -5 15";
    a.addTransactionGroup(transaction2);
    overdrafts = a.getNumberOfOverdrafts();
    if(overdrafts != 3) {
      System.out.println("FAILURE: getNumberOfOverdrafts did not return 3 after " 
          + transaction1 + " was added as a transaction group.");
      problemFound = true;
    } else
      System.out.println("PASSED TESTS 2/3 of testOverdraftCount!!!");
    //runs the same as test one but with different values
    String transaction3 = "0 0 0 1 1 0 1 0 0 0 0";
    a.addTransactionGroup(transaction3);
    overdrafts = a.getNumberOfOverdrafts();
    if(overdrafts != 6) {
      System.out.println("FAILURE: getNumberOfOverdrafts did not return 6 after " 
          + transaction3 + " was added as a transaction group.");
      problemFound = true;
    } else
      System.out.println("PASSED TESTS 3/3 of testOverdraftCount!!!");
    
    return !problemFound;
  }
  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when it is passed an empty int[].
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupEmpty() { 
    int[] empty = null; //creates empty test array
    try {
      TransactionGroup t = new TransactionGroup(empty); //passes empty array to new TG
    }
    catch(DataFormatException e) { //catches expected error and checks message and prints response 
      if(e.getLocalizedMessage().equals("transaction group encoding cannot be null or empty")) {
        System.out.println("PASSED TEST of testTranactionGroupEmpty!!!");
        return true;
      } else {
        System.out.println("wrong error message was recieved from exception.");
        return false;
      }
    }
    return false; 
  }
   
  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when then first int in the provided array is not 0, 1, or 2.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupInvalidEncoding() { 
    int[] test = {4, 5}; //creates test array
    try {
      TransactionGroup t = new TransactionGroup(test); //passes to new TG
    }
    catch(DataFormatException e) { //catches expected error and checks message and prints response 
      if(e.getLocalizedMessage().equals("the first element within a transaction group must be "
          + "0, 1, or 2")) {
        System.out.println("PASSED TEST of testTransactionGroupInvalidEncoding!!!");
        return true;
      } else {
        System.out.println("wrong error message was recieved from exception.");
        return false;
      }
    }
    return false; 
  }
  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a quick withdraw encoded group that contains negative
   * numbers of withdraws.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountAddNegativeQuickWithdraw() { 
    Account testAccount = new Account("account1"); //creates new testAccount
    String transaction1 = "2 -1 -1 -1 -1";  //creates new transaction string
    try {
      testAccount.addTransactionGroup(transaction1); // passes string for new addTransactionGroup
    }
    catch(DataFormatException e) { //catches expected error and checks message and prints response 
      if(e.getLocalizedMessage().equals("quick withdraw transaction groups may not contain negative numbers")) {
        System.out.println("PASSED TEST of testAccountAddNegativeQuickWithdraw!!!");
        return true;
      } else {
        System.out.println("wrong error message was recieved from exception.");
        return false;
      }
    }
    return false;  
  }
   
  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a string with space separated English words (non-int).
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBadTransactionGroup() { 
    Account testAccount = new Account("account1"); //creates new testAccount
    String transaction1 = "non-int"; //creates new bad transaction string
    try {
      testAccount.addTransactionGroup(transaction1); //passes bad string
    }
    catch(DataFormatException e) { //catches expected error and checks message and prints response 
      if(e.getLocalizedMessage().equals("addTransactionGroup requires string commands that contain only space separated integer values")) {
        System.out.println("PASSED TEST of testAccountBadTransactionGroup!!!");
        return true;
      } else {
        System.out.println("FAILED TEST: wrong error message was recieved from exception");
        return false;
      }
    }
    return false; 
  }
   
  /**
   * This method checks whether the Account.getTransactionAmount method throws an exception with an
   * appropriate message, when it is passed an index that is out of bounds.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountIndexOutOfBounds() { 
    Account testAccount = new Account("account1");  //creates new testAccount
    String transaction1 = "1 2"; //creates new 
    try {
      testAccount.getTransactionAmount(1);
    }
    catch(IndexOutOfBoundsException e) { //catch expected error, checks message and prints response 
        System.out.println("PASSED TEST of testAccountIndexOutOfBounds!!!");
        return true;
    }
    System.out.println("FAILED TEST: No exeption thrown from testAccountIndexOutOfBounds");
    return false;
  }
  /**
   * This method checks whether the TransactionGroup.getTransactionAmount method throws an exception
   *  with an appropriate message, when it is passed an index that is out of bounds.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupIndexOutOfBounds() { 
    int[] test = {1, 1};  //creates new test transaction array
    try {
      TransactionGroup t = new TransactionGroup(test); //passes array to new TG
      t.getTransactionAmount(3); //trys to get amount from invalid index
    } catch(DataFormatException e) { 
      System.out.println("FAILED TEST: Wrong error was thrown");
    }
    catch(IndexOutOfBoundsException e) {//catches expected error and checks message and 
      //prints response 
      System.out.println("PASSED TEST of testTransactionGroupIndexOutOfBounds!!!");
        return true;
    } 
    System.out.println("FAILED TEST: No exeption thrown from testAccountIndexOutOfBounds");
    return false; 
  }
  /**
   * This method checks whether the Account constructor that takes a File parameter throws an 
   * exception with an appropriate message, when it is passed a File object that does not correspond
   * to an actual file within the file system.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountMissingFile() { 
    try {  
      File testFile = new File("notAfile.txt"); //creates new bad file
      Account testAccount = new Account(testFile); //passes bad file to new testAccount
    }
    catch(FileNotFoundException e) { //catches expected error and checks message and prints response 
      System.out.println("PASSED TEST of testAccountMissingFile!!!");
      return true;
    }
    System.out.println("butts");
    return false; 
  } 
  
}
