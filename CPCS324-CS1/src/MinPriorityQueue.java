
 
 public abstract class MinPriorityQueue {
    
    
    
    public abstract Edge getMin();
    public abstract Edge extractMin();
    public abstract void insert(Edge val);
    public abstract boolean isEmpty();

}
enum QueueType {
  ARRAY,
  HEAP,
}