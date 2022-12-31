package list;

public class ArrayList<E> implements ListInterface<E>{
	private E item[];
	private int numItems;
	private static final int DEFAULT_CAPACITY = 64;

	public ArrayList(){
		item = (E[]) new Object[DEFAULT_CAPACITY];
		numItems = 0;
	}
	
	public ArrayList(int n){
		item = (E[]) new Object[n];
		numItems = 0;
	}
	
//	배열 리스트 k 번째 자리에 원소 x 삽입하기
	public void add(int index, E x) throws Exception {
		if (numItems >= item.length || index < 0 || index > numItems) {
//			에러 처리
			throw new Exception("인덱스 공간 초과");
		} else {
			for(int i = numItems - 1; i >= index; i--) 
				item[i + 1] = item[i];
			item[index] = x;
			numItems++;
		}
	}
	
// 배열 리스트의 맨 뒤에 원소 추가하기
	public void append(E x) throws Exception{
		if (numItems >= item.length) {
			throw new Exception("인덱스 공간 초과");
		}
		item[numItems] = x;
		numItems++;
	}
	

// 배열 리스트의 k번째 원소 삭제하기
	public E remove(int index) {
		if (isEmpty() || index >= numItems || index < 0) {
			return null;
		} else {
			E tmp = item[index];
			for (int i = index; i < numItems; i++) {
				item[i] = item[i + 1];
			}
			numItems--;
			return tmp;
		}
	}
	
// 배열 리스트에서 원소 x 삭제하기
	public boolean removeItem(E x) {
		int k = 0;
		while (k < numItems && ((Comparable)item[k]).compareTo(x) != 0)
			k++;
		if (k == numItems) {
			return false;
		} else {
			for (int i = k; i < numItems; i++) {
				item[i] = item[i + 1];
			}
			numItems--;
			return true;
		}
	}
	
// 배열 리스트의 i번쨰 원소 알려주기
	public E get(int index) {
		if (index >= 0 && index < numItems) {
			return item[index];
		} else {
			return null;
		}
	}
	
// 배열 리스트의 i번째 원소를 x로 대체하기
	public void set(int index, E x) throws Exception {
		if (index >= 0 && index < numItems) {
			item[index] = x;
		} else {
			throw new Exception("index out of range");
		}
	}
	
// 원소 x가 배열 리스트의 몇 번째 원소인지 알려주기
	private final int NOT_FOUND = -1;
	public int indexOf(E x) {
		int tmp = 0;
		while (tmp < numItems && item[tmp]!=x)
			tmp++;
		if (tmp!=numItems) {
			return tmp;
		}
		return NOT_FOUND;
	}
	
// 배열 리스트의 총 원소 수 알려주기
	public int len() {
		return numItems;
	}
	
//배열 리스트가 비었는지 알려주기
	public boolean isEmpty() {
		return numItems == 0;
	}
	
// 리스트 깨끗이 청소하기
	public void clear() {
		item = (E[]) new Object[item.length];
		numItems = 0;
	}
	
	public String toString() {
		return item.toString();
	}
}
