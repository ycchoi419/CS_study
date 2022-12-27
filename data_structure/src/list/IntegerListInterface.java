package list;

public interface IntegerListInterface {
	public void add(int i, Integer x) throws Exception;
	public void append(Integer x) throws Exception;
	public Integer remove(int i);
	public boolean removeItem(Integer x);
	public Integer get(int i);
	public void set(int i, Integer x) throws Exception;
	public int indexOf(Integer x);
	public int len();
	public boolean isEmpty();
	public void clear();
}
