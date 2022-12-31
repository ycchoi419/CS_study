package list;

public interface ListInterface<E> {
	public void add(int i, E x) throws Exception;
	public void append(E x) throws Exception;
	public E remove(int i);
	public boolean removeItem(E x);
	public E get(int i);
	public void set(int i, E x) throws Exception;
	public int indexOf(E x);
	public int len();
	public boolean isEmpty();
	public void clear();
}
