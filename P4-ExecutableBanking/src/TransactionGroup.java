//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Transaction Group
// Files: TransactionGroup.java
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
import java.util.zip.DataFormatException;

public class TransactionGroup {
  
  private enum EncodingType { BINARY_AMOUNT, INTEGER_AMOUNT, QUICK_WITHDRAW };
  private EncodingType type;
  private int[] values;
  /**
   * creates a new TransactionGroup based on the values in the array passed
   * @param int[] the values that will be interpruted for the transaction group
   * @throws DataFormatException 
   */
  public TransactionGroup(int[] groupEncoding) throws DataFormatException {
    if(groupEncoding == null || groupEncoding.length == 0) //checks and throws error if empty
      throw new DataFormatException("transaction group encoding cannot be null or empty"); 
    else if(groupEncoding[0] != 0 && groupEncoding[0] != 1 && groupEncoding[0] != 2) //checks
      //and throws error if invalid command
      throw new DataFormatException("the first element within a transaction group "
          + "must be 0, 1, or 2");
    else if(groupEncoding[0] == 0) { //checks and throws error for invalid transaction
      for(int index = 0; index < groupEncoding.length; index++) {
        if(groupEncoding[index] != 0 && groupEncoding[index]  != 1)
          throw new DataFormatException("binary amount transaction groups "
              + "may only contain 0s and 1s");
      }
    } 
    else if(groupEncoding[0] == 1) { //checks and throws error for invalid entries
      for(int index = 0; index < groupEncoding.length; index++) {
        if(groupEncoding[index] == 0)
          throw new DataFormatException("integer amount transaction groups may not contain 0s");
      }
    } 
    else if(groupEncoding[0] == 2) { //checks and throws errors for invalid entries
      if(groupEncoding.length != 5)
        throw new DataFormatException("quick withdraw transaction groups must contain 5 elements");
      for(int index = 0; index < groupEncoding.length; index++) {
        if(groupEncoding[index]  < 0)
          throw new DataFormatException("quick withdraw transaction groups"
              + " may not contain negative numbers");
      }
    }
    
    this.type = EncodingType.values()[groupEncoding[0]]; //sets type according to command
    this.values = new int[groupEncoding.length-1]; //sets values array to corresponding values
    for(int i=0;i<values.length;i++)
      this.values[i] = groupEncoding[i+1];
  }
  /**
   * goes through the value array depending on type and gets the transaction count. 
   * @return the transaction count
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    switch(this.type) {
      case BINARY_AMOUNT:  //if type is binary 
        int lastAmount = -1;
        for(int i=0;i<this.values.length;i++) {  //searches through array
          if(this.values[i] != lastAmount) {  
            transactionCount++;  //increase transaction count
            lastAmount = this.values[i];            
          }
        }
        break;
      case INTEGER_AMOUNT:
        transactionCount = values.length;
        break;
      case QUICK_WITHDRAW:
        for(int i=0;i<this.values.length;i++)
          transactionCount += this.values[i];        
    }
    return transactionCount;
  }
  /**
   * gets the amount of the transaction at the parameter index
   * @return the amount in the transaction
   * @param the index of the transaction
   * @throws IndexOUtOfBoundsExeption
   */
  public int getTransactionAmount(int transactionIndex) {
    if(transactionIndex > values.length) { //checks and throw error if out of bounds
      throw new IndexOutOfBoundsException("WARNING: IndexOutOfboundsException \nIndex being "
          + "accessed: " + transactionIndex + "\nTotal transactions in this group: " 
          + this.values.length);
    }
    int transactionCount = 0;
    switch(this.type) {
      case BINARY_AMOUNT:  //determines the amount if binary transaction
        int lastAmount = -1;
        int amountCount = 0;
        for(int i=0;i<=this.values.length;i++) { //searches through array 
          if(i == this.values.length || this.values[i] != lastAmount)  { 
            if(transactionCount-1 == transactionIndex) { 
              if(lastAmount == 0)
                return -1 * amountCount;  //returns the transactions amount
              else 
                return +1 * amountCount;  //returns the transactions amount
            }
            transactionCount++;       
            lastAmount = this.values[i];
            amountCount = 1;
          } else
            amountCount++;
          lastAmount = this.values[i];
        } 
        break; 
      case INTEGER_AMOUNT:  //determines amount if integer transaction
        return this.values[transactionIndex];
      case QUICK_WITHDRAW:  //determines amount if QW transaction
        final int[] QW_AMOUNTS = new int[] {-20, -40, -80, -100};
        for(int i=0;i<this.values.length;i++)  //searched through array 
          for(int j=0;j<this.values[i];j++)
            if(transactionCount == transactionIndex) 
              return QW_AMOUNTS[i]; //returns the transactions amount
            else 
              transactionCount++;
    }
    return -1;
  }  
}