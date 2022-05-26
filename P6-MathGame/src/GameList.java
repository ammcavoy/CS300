
public class GameList {
  private GameNode list; // reference to the first GameNode in this list
  
  public GameList() {
    this.list = null;
  } // initializes list to start out empty
  public void addNode(GameNode newNode) {
    if(this.list == null)
      this.list = newNode;
    else {
      GameNode current = this.list;
      while(current.getNext() != null)
        current = current.getNext();
      current.setNext(newNode);    
    }
  } // adds the new node to the end of this list
  
  public boolean contains(int number) {
    GameNode current = this.list;
    while(this.list != null && this.list.getNext() != null) {
      if(current.getNumber() == number)
        return true;
    }
    return false;
  } // only returns true when this list contains a node with the specified number
  
  public String toString() {
    String listString = "List: ";
    GameNode current = this.list;
    while(this.list != null && this.list.getNext() != null) {
      listString = listString + " -> " + current.getNumber();
      current = current.getNext();
    }
    listString = listString + " -> END";
    return listString;
  } // returns a string with each number in the list separated by " -> "s, and ending with " -> END"
  public void applyOperatorToNumber(int number, GameOperator operator) {
    GameNode current = this.list;
    while(current.getNumber() != number)
      current = current.getNext();
    current.applyOperator(operator);
  }// (described below)
}
