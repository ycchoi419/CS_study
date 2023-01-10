package queue;

public class ArrayQueue <E> implements QueueInterface<E> {

    private E queue[];
    private int front, tail, numItems;
    private static final int DEFAULT_CAPACITY = 64;

    public ArrayQueue() {
        queue = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        tail = DEFAULT_CAPACITY - 1;
        numItems = 0;
    }

    public ArrayQueue(int n) {
        queue = (E[]) new Object[n];
        front = 0;
        tail = n - 1;
        numItems = 0;
    }

    @Override
    public void enqueue(E x) throws Exception {
        if (isFull()) {
            throw new Exception("queue is full");
        } else {
            queue[(++tail)%queue.length] = x;
            numItems++;
        }
    }
    
    public boolean isFull() {
        return (numItems == queue.length);
    }
    @Override
    public E dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("empty queue");
        } else {
            E queueFront = queue[front];
            front = (front + 1)% queue.length;
            numItems--;
            return queueFront;
        }
    }

    @Override
    public E front() throws Exception {
        if (isEmpty()) {
            throw new Exception("empty queue");
        } else {
            return queue[front];
        }
    }

    @Override
    public boolean isEmpty() {
        return (numItems==0);
    }

    @Override
    public void dequeueAll() {
        queue = (E[]) new Object[queue.length];
        front = 0;
        tail = queue.length - 1;
        numItems = 0;
    }
    
}
