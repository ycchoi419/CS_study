# 15_생성자와 소멸자 그리고 this 키워드

### 15-1 디폴트 생성자

객체가 생성될 때 가장 먼저 호출되는 생성자로, 만약 개발자가 명시하지 않아도 컴파일 시점에 자동 생성된다. 

디폴트 생성자가 없는 경우 컴파일러가 자동으로 생성함. 



### 15-2 사용자 정의 생성자

디폴트 생성자 외에 특정 목적에 의해 개발자가 만든 생성자로, 매개변수에 차이가 있다. 



### 15-3 소멸자

객체가 GC에 의해서 메모리에서 제거될 때 finalize() 메서드가 호출된다. 

> System.gc();를 사용한다고 해서  GC가 바로 작동하는 것이 아니라, 가급적 빨리 작동하도록 요청하는 것이다. 
>
> java는 기본적으로 메모리를 개발자가 직접 관리하지 않으므로 일반적으로 System.gc();를 사용하는 경우는 드물다.

```java
@Override
protected void finalize() throws Throwable {
    System.out.println("-- finailize method --");
    
    super.finalize
}
```

```java
//소멸자
ObjectEx obj4;
obj4 = new ObjectEx();
obj4 = new ObjectEx();

System.gc();
```

거의 사용되지 않아서 그냥 있다는 것만 알고 있으면 됨 



### 15-4 this 키워드

작업을 하고 있는 현재 객체를 가리킬 때 this 키워드를 사용한다. 객체의 속성이나 함수를 가리킴. 