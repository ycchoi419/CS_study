package list;

public class CircularLinkedList<E> implements ListInterface<E> {
	private Node<E> tail;
	private int numItems;
	
	public CircularLinkedList() {
		numItems = 0;
		tail = new Node(-1);
		tail.next = tail;
	}
	@Override
	public void add(int i, E x) throws Exception {
		// TODO Auto-generated method stub
		if (i >= 0 && i <= numItems) {
			Node<E> prevNode = getNode(i - 1);
			Node<E> newNode = new Node(x, prevNode.next);
			prevNode.next = newNode;
			if (i == numItems) {
				tail = newNode;
			}
			numItems++;
		} else {
			throw new Exception("out of index error");
		}
	}

	@Override
	public void append(E x) throws Exception {
		// TODO Auto-generated method stub
		Node<E> prevNode = tail;
		Node<E> newNode = new Node(x, tail.next);
		prevNode.next = newNode;
		tail = newNode;
		numItems++;
	}

	@Override
	public E remove(int i) {
		// TODO Auto-generated method stub
		if (i >= 0 && i < numItems) {
			Node<E> prevNode = getNode(i-1);
			Node<E> currNode = prevNode.next;
			prevNode.next = currNode.next;
			numItems--;
			return currNode.item;
		}
		return null;
	}

	@Override
	public boolean removeItem(E x) {
		// TODO Auto-generated method stub
		Node<E> currNode = tail.next; // 더미 헤드
		Node<E> prevNode;
		for (int i = 0; i < numItems; i++) {
			prevNode = currNode;
			currNode = currNode.next;
			if (((Comparable)(currNode.item)).compareTo(x) == 0) {
				prevNode.next = currNode.next;
				numItems--;
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int i) {
		// TODO Auto-generated method stub
		if (i >= 0 && i < numItems) {
			return getNode(i).item;
		}
		return null;
	}
	
	public Node<E> getNode(int i) {
		if (i >= -1 && i <= numItems) { // -1일 때는 dummy head
			Node<E> newNode = tail.next;
			for (int j = 0; j <= i; j ++) {
				newNode = newNode.next;
			}
			return newNode;
		}
		return null;
	}

	@Override
	public void set(int i, E x) throws Exception {
		// TODO Auto-generated method stub
		if (i >= 0 && i < numItems) {
			getNode(i).item = x;
		} else {
			throw new Exception("out of index error");
		}
	}

	public final int NOT_FOUND = -1;
	@Override
	public int indexOf(E x) {
		// TODO Auto-generated method stub
		Node<E> currNode = tail.next;
		for (int i = 0; i < numItems; i++) {
			currNode = currNode.next;
			if (((Comparable)(currNode.item)).compareTo(x) == 0) {
				return i;
			}
		}
		return NOT_FOUND;
	}

	@Override
	public int len() {
		// TODO Auto-generated method stub
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (numItems == 0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		tail = new Node(-1);
		tail.next = tail;
		numItems = 0;
	}
	
}
