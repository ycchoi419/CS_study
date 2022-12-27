package list;

public class Main {
	public static void main(String[] args) throws Exception {
		IntegerArrayList list = new IntegerArrayList();
		list.add(0, 300);
		System.out.println(list.get(0));
		list.append(100);
		System.out.println(list.get(11));
	}
}
