//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Jungle Park 2000
// Files: ParkGUI.java
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
// TODO Add javadoc interface header

public interface ParkGUI {
  public void draw(); // draws a ParkGUI object (either an animal or a button) to the display window

  public void mousePressed(); // called each time the mouse is Pressed

  public void mouseReleased(); // called each time the mouse is Pressed

  public boolean isMouseOver(); // checks whether the mouse is over a ParkGUI object

}
