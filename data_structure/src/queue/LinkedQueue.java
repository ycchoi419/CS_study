package queue;
import list.Node;

public class LinkedQueue<E> implements QueueInterface<E> {

  private Node<E> tail;

  public LinkedQueue() {
    tail = null;
  }

  @Override
  public void enqueue(E x) throws Exception {
    Node<E> newNode = new Node<>(x);
    if (isEmpty()) {
      newNode.next = newNode;
      tail = newNode;
    } else {
      newNode.next = tail.next;
      tail.next = newNode;
      tail = newNode;
    }
  }

  @Override
  public E dequeue() throws Exception {
    if (isEmpty()) {
      throw new Exception("empty queue");
    } else {
      Node<E> front = tail.next;
      if (front==tail) {
        tail = null;
      } else {
        tail.next = front.next;
      }
      return front.item;
    }
  }

  @Override
  public E front() throws Exception {
    if (isEmpty()) {
      throw new Exception("empty queue");
    } else {
      return tail.next.item;
    }
  }

  @Override
  public boolean isEmpty() {
    return tail==null;
  }

  @Override
  public void dequeueAll() {
    tail = null;
  }
  
}