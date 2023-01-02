package stack;

public interface StackInterface<E> {
	public void push(E newItem) throws Exception;
	public E pop() throws Exception;
	public E top() throws Exception;
	public boolean isEmpty();
	public void popAll();
}
