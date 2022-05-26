import java.util.Random;

public class GameNode {
  private int number;    // the number held within this node
  private GameNode next; // the next GameNode in the list, or null for the last node
   
  public GameNode(Random rng) {
    
  } // initializes number to random 1-9 value, and next to null
  
  public int getNumber() {
    return this.number;
  } // accessor for the number field
  
  public GameNode getNext() {
    return this.next;
  } // accessor for the next field
  
  public void setNext(GameNode next) {
    this.next = next;
  } // mutator for the next field
  
  public void applyOperator(GameOperator operator) { 
    this.number = operator.apply(this.number, this.next.getNumber());
    this.next = this.next.getNext();
  } // (described below)
}
