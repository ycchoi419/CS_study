package stack;
import list.LinkedList;
import list.ListInterface;

public class ListStack<E> implements StackInterface<E> {
	private ListInterface<E> list;

	public ListStack() {
		// LinkedList가 아닌 다른 리스트를 사용할 수도 있다.
		list = new LinkedList<E>();
	}
	
	@Override
	public void push(E newItem) throws Exception {
		list.add(0, newItem);
	}

	@Override
	public E pop() throws Exception {
		return list.remove(0);
	}

	@Override
	public E top() throws Exception {
		return list.get(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void popAll() {
		list.clear();
	}
	
	
}
