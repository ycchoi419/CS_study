package stack;
import list.LinkedList;

public class InheritedStack<E> extends LinkedList<E> implements StackInterface<E>{
	
	public InheritedStack() {
		super();
	}
	
	@Override
	public void push(E newItem) throws Exception {
		// super 키워드를 쓰지 않아도 자동으로 부모 클래스에서 찾게 된다.
		super.add(0, newItem);
	}

	@Override
	public E pop() throws Exception {
		if(!super.isEmpty()) {
			return super.remove(0);
		} else return null;
	}

	@Override
	public E top() throws Exception {
		return super.get(0);
	}

	@Override
	public void popAll() {
		super.clear();
	}

}
