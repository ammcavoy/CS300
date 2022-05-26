//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Jungle Park 2000
// Files: AddAnimalButton.java
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
public class AddAnimalButton extends Button{

  private String type; // type of the animal to add
  /*
   * makes a new Animal button
   */
  public AddAnimalButton(String type, float x, float y, JunglePark park) {
      super(x, y, park); //calls button
      this.type = type.toLowerCase(); //sets type to all lower case
      this.label = "Add " + type; //creates label
  }
   
  @Override
  public void mousePressed() {
    if (isMouseOver()) { //checks if mouse is over button
      switch (type) {  //checks if button is tiger button
        case "tiger":
          this.processing.listGUI.add(new Tiger(this.processing)); // makes new tiger
          break;
        case "deer": //checks if deer
          this.processing.listGUI.add(new Deer(this.processing)); // makes new deer         
          break;
      }
    }
  }
}
