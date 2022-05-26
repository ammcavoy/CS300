
public class Badger extends Object{
  private Badger leftLowerNeighbor;
  private Badger rightLowerNeighbor;
  private int size;
  
  Badger(int size){
    this.size = size;
  }
  
  public Badger getLeftLowerNeighbor() {
    return leftLowerNeighbor;
  }
  
  public Badger getRightLowerNeighbor() {
    return rightLowerNeighbor;
  }
  
  public int getSize() {
    return size;
  }
  
  public void setLeftLowerNeighbor(Badger badger) {
    this.leftLowerNeighbor = badger;
  }
  
  public void setRightLowerNeighbor(Badger badger) {
    this.rightLowerNeighbor = badger;
  }
}
