package list;

public class CircularDoublyLinkedList<E> implements ListInterface<E> {
	private BidirectionalNode<E> head;
	private int numItems;
	
	public CircularDoublyLinkedList(){
		numItems = 0;
		head = new BidirectionalNode<>(null);
		head.next = head.prev = null;
	}
	
	@Override
	public void add(int i, E x) throws Exception {
		if (i >= 0 && i <= numItems) {
			BidirectionalNode<E> prevNode = getNode(i - 1);
			BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, prevNode.next);
			newNode.next.prev = newNode;
			prevNode.next = newNode;
			numItems++;
		} else {
			throw new Exception("out of index error");
		}
	}

	@Override
	public void append(E x) throws Exception {
		BidirectionalNode<E> endNode = head.prev;
		BidirectionalNode<E> newNode = new BidirectionalNode<>(endNode, x, head);
		head.prev = newNode;
		endNode.next = newNode;
		numItems++;
	}

	@Override
	public E remove(int i) {
		if (i >= 0 && i < numItems) {
			BidirectionalNode<E> currNode = getNode(i);
			currNode.prev.next = currNode.next;
			currNode.next.prev = currNode.prev;
			numItems--;
			return currNode.item;
		}
		return null;
	}

	@Override
	public boolean removeItem(E x) {
		BidirectionalNode<E> currNode = head;
		for(int i = 0; i < numItems; i++) {
			currNode = currNode.next;
			if (((Comparable)(currNode.item)).compareTo(x) == 0) {
				currNode.prev.next = currNode.next;
				currNode.next.prev = currNode.prev;
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
	
	public BidirectionalNode<E> getNode(int i){
		BidirectionalNode<E> currNode = head;
		if (i >= -1 && i < numItems/2) {
			for (int j = -1; j < i; j++) {
				currNode = currNode.next;
			}
			return currNode;
		} else if (i < numItems) {
			for (int j = numItems; j > i; j--) {
				currNode = currNode.prev;
			}
			return currNode;
		} else {			
			return null;
		}
	}
	

	@Override
	public void set(int i, E x) throws Exception {
		if(i >= 0 && i < numItems) {
			getNode(i).item = x;
		} else {
			throw new Exception("out of index error");
		}
		
	}

	public final int NOT_FOUND = -1;
	@Override
	public int indexOf(E x) {
		BidirectionalNode<E> currNode = head;
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
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return (numItems==0);
	}

	@Override
	public void clear() {
		numItems = 0;
		head.prev = head.next = head;
	}

}
