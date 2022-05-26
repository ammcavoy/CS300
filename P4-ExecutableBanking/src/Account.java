//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Account
// Files: Account.java
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
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Account {
  
  private static final int MAX_GROUPS = 10000; //max transactions per account
  private static int nextUniqueId = 1000; 
  
  private String name;
  private final int UNIQUE_ID; 
  private TransactionGroup[] transactionGroups;
  private int transactionGroupsCount; 
  /**
   * creates a new account
   * @param the name of the account
   */
  public Account(String name) {
   this.name = name; //sets name
   this.UNIQUE_ID = Account.nextUniqueId; //sets ID
   Account.nextUniqueId++;
   this.transactionGroups = new TransactionGroup[MAX_GROUPS];  //creates transaction array
   this.transactionGroupsCount = 0;
  }
  /**
   * creates a new account and reads the transactions from the same file
   * @param the file that will be read from 
   * @throws FileNotFoundException
   */
  public Account(File file) throws FileNotFoundException {
    if(file == null) { //checks and throws exception
      throw new FileNotFoundException("The file passed to Account must be a valid file.");
    }
    Scanner in = new Scanner( file); //creates new file scanner

    this.name = in.nextLine();  //sets name and ID
    this.UNIQUE_ID = Integer.parseInt( in.nextLine() );
    Account.nextUniqueId = this.UNIQUE_ID + 1;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;    
    String nextLine="";
    while(in.hasNextLine()) {  //reads in the transactions 
      try {
        this.addTransactionGroup(in.nextLine());
      }
      catch(DataFormatException e) {  //catches any errors provided by the files transactions 
        System.out.println("There was an error in the file.\n" + e.getMessage());
      }
    }
    in.close(); //closes the scanner
  }
  /**
   * gets the unique ID
   * @return the unique ID
   */
  public int getId() { 
    return this.UNIQUE_ID;
  }
  /**
   * creates an array and passed it to a new TransactionGroup(TG) and added the TG to the array.
   * @param the string that will be the transaction values 
   * @throws DataFormatException 
   */
  public void addTransactionGroup(String command) throws DataFormatException {
    String[] parts = command.split(" "); //splits the string
    int[] newTransactions = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      boolean canParse;
      try {  //checks that the values of array are valid for int parse
        Integer.parseInt(parts[i]);
        canParse = true;
      }
      catch(NumberFormatException e) {
        canParse = false;
      }
      if(!canParse)  //throws error if the values in the sting don't work for int parsing
        throw new DataFormatException("addTransactionGroup requires string commands that "
            + "contain only space separated integer values");
      newTransactions[i] = Integer.parseInt(parts[i]); //sets value to new array
    }
   
    TransactionGroup t = new TransactionGroup(newTransactions); //creates new TG
    try {
      this.transactionGroups[this.transactionGroupsCount] = t;  //checks and notifies of error
    }
    catch(IndexOutOfBoundsException e) {  //program can keep running after
      System.out.println("WARNING: OutOfMemoryError, the capacity for this Account array ["
           + MAX_GROUPS + "] has been reached no more transactions can be added.");   
    }
    this.transactionGroupsCount++;
  }
  /**
   * gets the transaction count for the account using the TG methods and TG's in array
   * 
   * @return the transaction count in the account
   */
  public int getTransactionCount() {
      int transactionCount = 0;
      for(int i=0;i<this.transactionGroupsCount;i++)  //loops through array of TG's
          transactionCount += this.transactionGroups[i].getTransactionCount();  // gets the 
          //transaction count for a transaction group
      return transactionCount;
  }
  /**
   * determines the amount of a transaction, at a cerin index passes as a parameter.
   * @param the index that the amount will determined at
   */
  public int getTransactionAmount(int index) {    
      if(index >= this.getTransactionCount()) { //checks and throws error
        throw new IndexOutOfBoundsException("WARNING: IndexOutOfboundsException \nIndex being accessed: "
            + index + "\nTotal accessable transactions in this account: " 
            + this.transactionGroupsCount);
      }
      int transactionCount = 0;
      for(int i=0;i<this.transactionGroupsCount;i++) {  //loops through array if TG's
        int prevTransactionCount = transactionCount;  
        transactionCount += this.transactionGroups[i].getTransactionCount();
        if(transactionCount > index) {  //if the count if past the desired index we return the value
          index -= prevTransactionCount;
          return this.transactionGroups[i].getTransactionAmount(index);
        }
      }
      
    return -1; //if something went wrong -1 is returned 
  }
  /**
   * gets the current balance of the account using the get count and get amount methods
   * @returns the current balance of the account
   */
  public int getCurrentBalance() {
    int balance = 0;
    int size = this.getTransactionCount();  //gets the amount of transactions
    for(int i=0;i<size; i++)
      balance += this.getTransactionAmount(i);  //loops through getting amount of transaction 
    return balance; //returns the calulated balance
  }
  /**
   * gets the number of overdrafts by the account
   * @return the number of overdrafts
   */
  public int getNumberOfOverdrafts() {
    int balance = 0;
    int overdraftCount = 0;
    int size = this.getTransactionCount(); //loops throught the same as in getCurrentBalance
    for(int i=0;i<size; i++) {
      int amount = this.getTransactionAmount(i); 
      balance += amount; //calulates the balance same as in getCurrentBalance
      if(balance < 0 && amount < 0) // determines if each transaction was an overdraft 
        overdraftCount++;
    }
    return overdraftCount;  //returns the number of overdrafts.
  }
    
}
