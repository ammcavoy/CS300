import java.util.*;

public class Sett extends Object{
  private Badger topBadger;
  Sett(){
    
  }
  
  public Badger getTopBadger() {
    return topBadger;
  }
  
  public boolean isEmpty() {
    return topBadger == null;
  }
  
  public void settleBadger(int size) throws IllegalArgumentException{
    Badger newBadger = new Badger(size);
    if(topBadger == null) {
      topBadger = newBadger;
      return;
    }
    settleHelper(topBadger, newBadger);
  }
  
  private void settleHelper(Badger current, Badger newBadger) throws IllegalArgumentException{
    if(newBadger.getSize() < current.getSize()) {
      if(current.getLeftLowerNeighbor() == null)
        current.setLeftLowerNeighbor(newBadger);
      else
        settleHelper(current.getLeftLowerNeighbor(), newBadger);
    }
    else if(newBadger.getSize() > current.getSize()) {
      if(current.getRightLowerNeighbor() == null)
        current.setRightLowerNeighbor(newBadger);
      else
        settleHelper(current.getRightLowerNeighbor(), newBadger);
    }
    else
      throw new IllegalArgumentException("WARNING: failed to settle the badger with size " 
          + newBadger.getSize() + ", as there is already a badger with the same size in this sett");
  }
  
  public Badger findBadger(int size) throws NoSuchElementException{
    if(this.isEmpty())
      throw new NoSuchElementException("WARNING: failed to find a badger with size"+ size
          + "in the sett");
    Badger goal = findHelper(topBadger, size);
    return goal;
  }
  
  private Badger findHelper(Badger current, int size) throws NoSuchElementException{
    return topBadger;
  }
  
  public int countBadger() {
    if(this.isEmpty())
      return 0;
    return countHelper(topBadger);
  }
  
  private int countHelper(Badger current) {
    int count = 1;
    if(current.getLeftLowerNeighbor() != null)
      count += countHelper(current.getLeftLowerNeighbor());
    if(current.getRightLowerNeighbor() != null)
      count += countHelper(current.getRightLowerNeighbor());
    return count;
  }
  
  public List<Badger> getAllBadgers(){
     List<Badger> temp = new ArrayList<Badger>();
     getAllHelper(topBadger, temp);
     return temp;
  }
  
  private void getAllHelper(Badger current, List<Badger> allBadgers) {
    if(current.getLeftLowerNeighbor() != null)
      getAllHelper(current.getLeftLowerNeighbor(), allBadgers);
    allBadgers.add(current);
    if(current.getRightLowerNeighbor() != null)
      getAllHelper(current.getRightLowerNeighbor(), allBadgers);
  }
  
  public int getHeight() {
    if(this.isEmpty())
      return 0;
    return getHeightHelper(topBadger);
  }
  
  private int getHeightHelper(Badger current) {
    int height = 1;
    int leftHeight = 0;
    int rightHeight = 0;
    if(current.getLeftLowerNeighbor() != null)
      leftHeight = getHeightHelper(current.getLeftLowerNeighbor());
    if(current.getRightLowerNeighbor() != null)
      rightHeight = getHeightHelper(current.getRightLowerNeighbor());
    if(leftHeight >= rightHeight)
      return height + leftHeight;
    else
      return height + rightHeight;
  }
  
  public Badger getLargestBadger() {
    Badger temp = topBadger;
    while(temp.getRightLowerNeighbor() != null) {
      temp = temp.getRightLowerNeighbor();
    }
    return temp;
  }
  
  public void clear() {
    topBadger = null;
  }
  
}
