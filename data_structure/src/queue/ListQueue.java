package queue;
import list.ArrayList;
import list.ListInterface;

public class ListQueue<E> implements QueueInterface<E> {
  private ListInterface<E> list;

  public ListQueue() {
    list = new ArrayList<E>();
  }

  @Override
  public void enqueue(E x) throws Exception {
    list.append(x);
  }

  @Override
  public E dequeue() throws Exception {
    return list.remove(0);
  }

  @Override
  public E front() throws Exception {
    return list.get(0);
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public void dequeueAll() {
    list.clear();
  }
  
}
