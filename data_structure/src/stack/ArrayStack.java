package stack;

public class ArrayStack<E> implements StackInterface<E> {
	private E stack[];
	private int topIndex;
	private static final int DEFAULT_CAPACITY = 64;
	
	public ArrayStack() {
		stack = (E[]) new Object[DEFAULT_CAPACITY];
		// 자바에서는 제네릭 타입의 객체를 생성하지 못한다 따라서 아래와 같이 표현할 수 없고 편법으로 Object 타입 배열을 만들어준 다음 타입캐스팅한다.
		// stack = new E[DEFAULT_CAPACITY];
		topIndex = -1;
	}
	
	public ArrayStack(int n) {
		stack = (E[]) new Object[n];
		topIndex = -1;
	}
	
	@Override
	public void push(E newItem) throws Exception {
		if (isFull()) {
			throw new Exception("out of capacity");
		} else {
			topIndex++;
			stack[topIndex] = newItem;
		}
	}
	@Override
	public E pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("pop from empty stack");
		} else {
			return stack[topIndex--];
		}
	}
	@Override
	public E top() throws Exception {
		if (isEmpty()) {
			throw new Exception("empty stack");
		} else {
			return stack[topIndex];
		}
	}
	@Override
	public boolean isEmpty() {
		return topIndex < 0;
	}
	public boolean isFull() {
		return(topIndex == stack.length);
	}
	@Override
	public void popAll() {
		stack = (E[]) new Object[stack.length]; 
		// stack 에 새 배열을 할당하지 않고 topIndex 값만 변경할 경우 배열에 저장되어 있는 ㄱ밧이 그대로 있어서 메모리가 낭비될 수 있다. 
		topIndex = -1;
	}
}
