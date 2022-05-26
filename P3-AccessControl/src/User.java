//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control
// Files: User.java
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
public class User {
  private final String username; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  /*
   * the constructor of a new user
   * @param username the username of the new user
   * @param password the password of the new user
   * @isAdmin the boolean value of the users admin status
   */
  User(String username, String password, boolean isAdmin) {
    this.username = username;  //sets this users values to passed values
    this.password = password;
    this.isAdmin = isAdmin;
  } 
  /*
   * checks if the password passed is this users password
   * @param password, the password being tested
   */
  public boolean isValidLogin(String password) {
    if(this.password.equals(password)) {
      return true;
    }
    return false;
  } 
  /*
   * @return this users username
   */
  public String getUsername() {
    return this.username;
  } 
  /*
   * @return this users admin status as a boolean
   */
  public boolean getIsAdmin() {
    return this.isAdmin;
  } 
  /*
   * sets this users passord to be whatever is passed
   * @param password, the new password for this user
   */
  public void setPassword(String password) {
    this.password = password;
  } 
  /*
   * sets this users admin status to valued passed 
   * @param isAdmin, the value that this user will have as admin status
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  } 
}
