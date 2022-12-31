package list;

public class LinkedList<E> implements ListInterface<E> {
	private Node<E> head;
	private int numItems;
	
	public LinkedList() {
		numItems = 0; 					// 생성자
		head = new Node<>(null, null); 	// 더미 헤드
	}
	
	@Override
	public void add(int index, E x) throws Exception {
		if (index >= 0 && index <= numItems) {
			Node<E> prevNode = getNode(index - 1);
			Node<E> newNode = new Node<E>(x, prevNode.next);
			prevNode.next = newNode;
		}
	}

	@Override
	public void append(E x) throws Exception {
		// TODO Auto-generated method stub
		Node<E> newNode = new Node<E>(x, null);
		Node<E> prevNode = head;
		while (prevNode.next != null) {
			prevNode = prevNode.next;
		}
		prevNode.next = newNode;
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
		Node<E> prevNode = head;
		Node<E> currNode;
		while (prevNode.next != null) {
			currNode = prevNode.next;
			if (((Comparable)(currNode.item)).compareTo(x) == 0) {
				prevNode.next = currNode.next;
				numItems--;
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index >= 0 && index <= numItems - 1) { 
			return getNode(index).item;
		} else {
			return null;
		}
	}
	
	public Node<E> getNode(int index) {
		if (index >= -1 && index <= numItems - 1) {  // -1일 때는 dummy head(?)
			Node<E> currNode = head;
			for (int i = 0; i <= index; i++) {
				currNode = currNode.next;
			}
			return currNode;
		} else {
			return null;
		}
	}

	@Override
	public void set(int i, E x) throws Exception {
		// TODO Auto-generated method stub
		Node<E> currNode = getNode(i);
		if (currNode != null) {
			currNode.item = x;
		} else {
			throw new Exception("out of index error");
		}
	}

	public final int NOT_FOUND = -1;
	@Override
	public int indexOf(E x) {
		// TODO Auto-generated method stub
		Node<E> currNode = head;
		for (int i = 0; i< numItems; i++) {
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
		head = new Node<E>(null, null);
		numItems = 0;
	}
	
}