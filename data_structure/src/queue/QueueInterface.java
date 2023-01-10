package queue;

public interface QueueInterface<E> {
    public void enqueue(E x) throws Exception;
    public E dequeue() throws Exception;
    public E front() throws Exception;
    public boolean isEmpty();
    public void dequeueAll();
}
