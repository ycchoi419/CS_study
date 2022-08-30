# 25_Collections

배열과 같이 자료(데이터)를 효율적으로 관리하기 위한 방법



### 25-1 List

List는 인터페이스로 이를 구현한 클래스는 인덱스를 이용해서 데이터를 관리한다. 

List를 이용해 구현한 객체는 크게 Vector, ArrayList, LinkedList가 있으며 인덱스를 이용하고 데이터 중복이 가능하다는 특징이 있다. 

```java
// ArrayList 객체 생성
ArrayList<String> list = new ArrayList<String>();

// 데이터 추가 
list.add("Hello");
list.add("Java");
list.add("World");
System.out.println("list.size : " + list.size()); // list.size : 3

list.add(2,"Programming"); // [Hello, Java, Programming, World]

list.set(1, "C"); // [Hello, C, Programming, World]

// 데이터 추출
String str = list.get(2); // Programming

// 데이터 제거
str = list.remove(2); // 리슨트에서 제거하고 제거한 값을 반환

// 데이터 전체 제거
 list.clear();

// 데이터 유무
boolean b = list.isEmpty();
```



### 25-2 Map

Map은 인터페이스로 이를 구현한 클래스는 key를 이용해서 데이터를 관리한다. 

Map을 이용해 구현한 객체는 HashMap이 있다. 

특징은 중복될 수 없는 key를 이용하며, 데이터는 중복이 가능하다. 

```java
// HashMap 객체 생성
HashMap<Integer, String> map = new HashMap<Integer, String>(); //key, value
System.out.println("map.size() : " + map.size); //map.size() : 0

// 데이터 추가
map.put(5, "Hello");
map.put(6, "Java");
map.put(7, "World");
System.out.println("map : " + map); // map : {5=Hello, 6=Java, 7=World}

map.out(8, "!!");

// 데이터 교체
map.put(6, "C");
System.out.println("map : " + map); // map : {5=Hello, 6=C, 7=World, 8=!!}

// 데이터 추출
str = map.get (5);
System.out.println("str : " + str); // str : Hello

// 데이터 제거
map.remove(8);

// 특정 데이터 포함 유무
b = map.containKey(7); //true

b = map.containsValue("World"); //true

// 데이터 전체 제거
map.clear();

// 데이터 유무
b = map.isEmpty(); //true
```

