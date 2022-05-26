//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Jungle Park 1000
// Files: JunglePark.java
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
public class JunglePark {
  private static PApplet processing; // object that represents the graphic interface application
  private static PImage backgroundImage; // PImage object that represents the background image
  private static Tiger[] tigers; // array storing the current tigers present in the Jungle Park
  private static Random randGen; // Generator of random numbers
  public static void main(String[] args) {
    Utility.startApplication();
  }
  /**
   * Defines the initial environment properties of the application
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed into 
    // the input argument parameter
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width  
    // [resp. height] of the display window.
    randGen= new Random();
    tigers = new Tiger[8]; 
   
  }
  /**
   * is continuously called from the application package and refreshes the tigers and background.
   */
  public static void update() {
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width  
    // [resp. height] of the display window.
    for(int index = 0; index < tigers.length; index++) { 
      if(tigers[index] != null)
        tigers[index].draw();
    }
    
  }
  /**
   * Determines if the mouse if over the tiger passed in the parameters. 
   * @param tiger, is the tiger that is being checked.
   * @returns true if the mouse is over the tiger and false if not.
   */
  public static boolean isMouseOver(Tiger tiger) {
    float tigerLeft = tiger.getPositionX() - (tiger.getImage().width)/2;
    float tigerRight = tiger.getPositionX() + (tiger.getImage().width)/2;
    float tigerBottom = tiger.getPositionY() - (tiger.getImage().height)/2;
    float tigerTop = tiger.getPositionY() + (tiger.getImage().width)/2;
    float mouseX = (float)processing.mouseX;
    float mouseY = (float)processing.mouseY;
    if(tigerLeft <= mouseX && mouseX <= tigerRight && tigerBottom <= mouseY && mouseY <= tigerTop) { 
      return true;
    }
    return false;
  }
  /**
   * every time the mouse is pressed down it makes calls to isMouseOver() for every tiger that is 
   * in the tigers array, checking if any are under the mouse, and if there is a tiger under the 
   * mouse, that tiger begins dragging.
   */
  public static void mouseDown() {
    boolean foundTiger = false;
    for(int index = 0; index < tigers.length; index ++) {
      if(tigers[index] != null && !foundTiger) {
        foundTiger = isMouseOver(tigers[index]);
        tigers[index].setDragging(foundTiger);
      }
    }
  }
  /**
   * Every time the mouse is release it sets all the tigers to stop dragging, so that if a tiger 
   * was being dragged while the mouse was pressed, it is no longer binded to the mouse. 
   */
  public static void mouseUp() {
    for(int index = 0; index < tigers.length; index ++) {
      if(tigers[index] != null) 
        tigers[index].setDragging(false);
    }
  }
  /**
   * is called whenever a key is pressed and if the key is a 't' it adds a tiger if there are not
   * already 8 tigers in the jungle. If the key pressed is and 'r' and there is a tiger under the
   * mouse then the tiger will be removed from the jungle. 
   */
  public static void keyPressed() {
    if (processing.key == 't' || processing.key == 'T') {
      for(int index = 0; index < tigers.length; index ++) {
        if (tigers[index] == null) {
          tigers[index] = new Tiger(processing, (float)randGen.nextInt(processing.width),
              (float)randGen.nextInt(processing.height));
          tigers[index].draw(); // where i is the index of the created tiger in the tigers array.
          break;
        }
      }
    } else if(processing.key == 'r' || processing.key == 'R') {
      for(int index = 0; index < tigers.length; index ++) {
        if(tigers[index] != null) {
          boolean foundTiger = isMouseOver(tigers[index]);
          if(foundTiger) {
            tigers[index] = null;
            break;
          }
        }
      }
    }
  } 
}
