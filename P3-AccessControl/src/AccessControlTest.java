//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control
// Files: AccessControlTest.java
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
public class AccessControlTest {
 
  public static void main(String args[]) {
    int fails = 0;
    if (!testLogin1()) {  //calls test method and if false prints error statement, this is the same 
      //for every test
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
   
    if (!testAddUser2()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
   
    if (!testRemoveUser()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
    if (fails == 0) { //if non of the tests failed all tests have been passed
      System.out.println("All tests passed!");
    }
  }
  /*
   * This test tries to log in a user that doesn't exist
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !ac.isValidLogin(user, pw); // isValidLogin should return false
  }
  /*
   * This tests the AddUser method by adding a user that doesn't exist
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String username = "probablyNotInTheSystem1234";
    return ac.addUser(username);  //creates new user and will return true if it works
  }   
  /*
   * This test tries to create a user that already exists
   * @return boolean test passed
   */
  public static boolean testAddUser2() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String username = "probablyNotInTheSystem1234";  
    ac.addUser(username);  // creates new user
    return !ac.addUser(username);  //creates same user so it should return false
  }
  /*
   * This test tries the RemoveUser method 
   * @return boolean test passed
   */
  public static boolean testRemoveUser() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String username = "probablyNotInTheSystem1234";
    ac.addUser(username);  //creates new user 
    return ac.removeUser(username);  // removes new user and it should return true
    
  }
}
