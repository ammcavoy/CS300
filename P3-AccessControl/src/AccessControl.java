//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control
// Files: AccessControl.java
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
public class AccessControl {
  private static ArrayList<User> users; // An ArrayList of valid users
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "password"; // Default password given to new users 

  /*
   * no parameter constructor of an AccessControl
   */
  AccessControl() {
    users  = new ArrayList<User>();
    users.add(new User("admin", "root", true));
    currentUser = null;
  } 
  
  public static void main(String[] args) {
    AccessControl ac = new AccessControl();
    Scanner userIn = new Scanner(System.in);
    ac.loginScreen(userIn);
  } 
  /*
   * asks the user for username and password and if they are valid logs into user 
   * @param userInputScanner is the scanner that will be used to take user input
   */
  public void loginScreen(Scanner userInputScanner) {
    while(true) {
      System.out.print("username: ");
      String username = userInputScanner.next(); 
      System.out.print("password: ");
      String password = userInputScanner.next();
      if(isValidLogin(username, password))  //calls isValidLogin to see if the username and password
        //are valid
        sessionScreen(username, userInputScanner);  //if they are valid the sessionScreen is called
      else
        System.out.println("\nusername or password was invalid, please try again.\n");
    }
  } 
  /*
   * this method runs continuosly until the user logs out, and reads in and processes user commands
   * @param username the username of the current user
   * @param userInputScanner is the scanner that will be used to get user input
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    setCurrentUser(username);
    String cleanUp = userInputScanner.nextLine();  //takes in useless string so the first scanner 
    // isn't just run past not taking in a users input.
    while(currentUser != null) {
      print();  //calls the print method to print user interface
      String userInput = userInputScanner.nextLine();  //takes user input
      String[] userInputParts = userInput.split(" ");  //splits input into parts so they can be 
      //processed
     
      if(userInputParts[0].equals("logout")) {  //checks if the user logs out
        logout();
        break;  // leaves loop because user is logged out
      } 
      if(userInputParts.length > 1) {  //checks to make sure there is a valid command entered
        if(userInputParts[0].equals("newpw"))  // checks is the users first word was a valid command
          // and if so it executes accordingly, with respect to every command 
          changePassword(userInputParts[1]);
        else if(userInputParts[0].equals("adduser") && userInputParts.length == 2) 
          addUser(userInputParts[1]);
        else if(userInputParts[0].equals("adduser") && userInputParts.length == 3) 
          addUser(userInputParts[1], Boolean.parseBoolean(userInputParts[2]));
        else if(userInputParts[0].equals("rmuser")) 
          removeUser(userInputParts[1]);
        else if(userInputParts[0].equals("giveadmin")) 
          giveAdmin(userInputParts[1]);
        else if(userInputParts[0].equals("rmadmin")) 
          takeAdmin(userInputParts[1]);
        else if(userInputParts[0].equals("resetpw")) 
          resetPassword(userInputParts[1]);
        else  // if no valid command was entered this executes 
          System.out.println("\nPlease enter a valid command\n");
      } else
        System.out.println("\nPlease enter a valid command\n");
      
    }
    
    
  } 
  // Set the currentUser 
  // Allows them to changePassword or logout 
  // If they are an admin, gives access to admin methods
  /*
   * prints out the command menu
   */
  private void print() {
    if(currentUser.getIsAdmin()) { //checks if current user is admin or not
      System.out.println("\nlogout");
      System.out.println("newpw [newpassword]");
      System.out.println("adduser [username]");
      System.out.println("adduser [username] [true or false]");
      System.out.println("rmuser [username]");
      System.out.println("giveadmin [username]");
      System.out.println("rmadmin [username]");
      System.out.println("resetpw [username]");
      System.out.print("Enter the command and the corresponding values: ");
    } else {
      System.out.println("\nlogout");
      System.out.println("newpw [newpassword]");
      System.out.print("Enter the command and the corresponding values: ");
    }
  }
  /*
   * tests if the user with "username" exists and the password is correct
   * @username is the username being searched for
   * @password is the password the user "username" being tested
   * @return true if there is user "username" with matching password
   * @return false if user "username" doesn't exist, or password is incorrect
   */
  public static boolean isValidLogin(String username, String password) {
    for(int index = 0; index < users.size(); index ++) {  //searched for user, and checks if 
      //password is the same
      if(users.get(index).getUsername().equals(username) && users.get(index).isValidLogin(password)){
         return true; 
      }
    }
    return false;
  } 
  /*
   * logs the current user out
   */
  public void logout() {
    currentUser = null;
  } 
  /*
   * changes the password of a user
   * @param the new password
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);  //sets the new password
  } 
  /*
   * sets a new current user
   * @param the username of user that is being set as current user
   */
  public void setCurrentUser(String username) {
    for(int index = 0; index < users.size(); index ++) { //searches for the desired user
      if(users.get(index).getUsername().equals(username))
        currentUser = users.get(index);  //sets the current user as the desired user
    }  
  }
  
  
  /*
   * creates a new user for the users arraylist
   * @param the username of the new user being added 
   * @return true if user doesn't already exist and is now added
   * @return false if current user is not admin, or if the new user already exists
   */
  public boolean addUser(String username) {
    if(currentUser.getIsAdmin()) {  //checks if current user is admin
      for(int index = 0; index < users.size(); index ++) {  //searches to see if user already exist
        if(users.get(index).getUsername().equals(username)) {
          System.out.println("\nUser Already exists please enter valid new user."); 
          return false;
        }
      }
      users.add(new User(username, DEFAULT_PASSWORD, false));  //creates new user thats not admin
      return true;
    } else
      return false;
  }
  /*
   * creates a new user for the users arrayList
   * @param the username of the new user
   * @param the admin status  of the new user
   * @return true if user doesn't already exist and is now added
   * @return false if current user is not admin, or if the new user already exists
   */
  public boolean addUser(String username, boolean isAdmin) {
    if(currentUser.getIsAdmin()) {  //checks if current user is admin
      for(int index = 0; index < users.size(); index ++) {  //searches to see if user already exist
        if(users.get(index).getUsername().equals(username)) {
          System.out.println("\nUser Already exists please enter valid new user.");         
          return false;
        }
      }
      users.add(new User(username, DEFAULT_PASSWORD, isAdmin));  //create new user
      return true;
    } else
      return false;
  } 
  /*
   * removes a user from the arrayList
   * @param the username of user that is being removed. 
   * @return true if user found and removed from arrayList
   * @return false if current user is not admin, or if the desired user not found.
   */
  public boolean removeUser(String username) {
    if(currentUser.getIsAdmin()) { //checks if current user is admin
      for(int index = 0; index < users.size(); index ++) { //searches for the desired user
        if(users.get(index).getUsername().equals(username)) {
          users.remove(index);  //removes desired user from the arrayList
          return true;
        }
      }
      System.out.println("\nCould not find user please enter a valid user");  //prints if desires
      //user not found
      return false;
    } else
      return false;
  } 
  /*
   * Gives a users admin power
   * @param the username of the user gaining admin powers
   * @return true if the user was found and is now an admin
   * @return false if the current user is not admin, or user gaining power not found
   */
  public boolean giveAdmin(String username) {
    if(currentUser.getIsAdmin()) {  //checks if current user is admin
      for(int index = 0; index < users.size(); index ++) { //searches for the desired user
        if(users.get(index).getUsername().equals(username)) {
          users.get(index).setIsAdmin(true);   //gives the admin power from desired user
          return true;
        }
      }
      System.out.println("\nCould not find user please enter a valid user"); //prints if desired
      //user not found
      return false;
    } else
      return false;
  } 
  /*
   * Removes a users admin power
   * @param the username of the user losing admin powers
   * @return true if the user was found and is no longer an admin
   * @return false if the current user is not admin, or user losing power not found
   */
  public boolean takeAdmin(String username) {
    if(currentUser.getIsAdmin()) { //checks if current user is admin
      for(int index = 0; index < users.size(); index ++) { //searches for desired user
        if(users.get(index).getUsername().equals(username)) {
          users.get(index).setIsAdmin(false);  //takes the admin power from desired user
          return true;
        }
      }
      System.out.println("\nCould not find user please enter a valid user"); //prints if desired 
      //user not found
      return false;
    } else
      return false;
  } 
  /*
   * resets the password to the dafault for a user
   * @param the username of the user getting password reset
   * @return true if user found and password is now default 
   * @return false if the current user is not admin, or user getting password reset not found
   */
  public boolean resetPassword(String username) {
    if(currentUser.getIsAdmin()) {  //checks is current user is admin
      for(int index = 0; index < users.size(); index ++) { //searches for user with desired username
        if(users.get(index).getUsername().equals(username)) {
          users.get(index).setPassword(DEFAULT_PASSWORD);  //resets password of desired user
          return true;
        }
      }
      System.out.println("\nCould not find user please enter a valid user"); //prints if desired 
      //user not found
      return false;
    } else
      return false;
  } 
  
}
