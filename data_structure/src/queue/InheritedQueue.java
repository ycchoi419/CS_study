package queue;
import list.LinkedList;

public class InheritedQueue<E> extends LinkedList<E> implements QueueInterface<E> {

  public InheritedQueue() {
    super();
  }

  @Override
  public void enqueue(E x) throws Exception {
    super.append(x);
  }

  @Override
  public E dequeue() throws Exception {
    return super.remove(0);
  }

  @Override
  public E front() throws Exception {
    return get(0);
  }

  @Override
  public boolean isEmpty() {
    return super.isEmpty();
  }

  @Override
  public void dequeueAll() {
    super.clear();
  }
  
}
