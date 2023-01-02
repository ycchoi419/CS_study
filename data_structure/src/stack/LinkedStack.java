package stack;
import list.Node;

public class LinkedStack<E> implements StackInterface<E> {
	
	public static void main(String[] args) throws Exception {
		LinkedStack<String> s = new LinkedStack<>();
		s.push("test 1");
		s.push("test 2");
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
	private Node<E> topNode;
	public LinkedStack() {
		topNode = null;
	}
	
	@Override
	public void push(E newItem) throws Exception {
		topNode = new Node(newItem, topNode);
	}

	@Override
	public E pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("pop from empty stack");
		} else {			
			E currNode = topNode.item;
			topNode = topNode.next;
			return currNode;
		}
	}

	@Override
	public E top() throws Exception {
		if (isEmpty()) {
			throw new Exception("empty stack" );
		} else {
			return topNode.item;
		}
	}

	@Override
	public boolean isEmpty() {
		return topNode==null;
	}

	@Override
	public void popAll() {
		topNode = null;
	}

}
