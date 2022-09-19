# 04 관계 대수와 SQL

- 관계 데이터 모델에서 지원되는 두 가지 정형적인 언어
  - 관계 해석(relational calculus)
    - 원하는 데이터만 명시하고 질의를 어떻게 수행할 것인가는 명시하지 않는 선언적인 언어(what)
  - 관계 대수(relational algebra)
    - 어떻게 질의를 수행할 것인가를 명시하는 절차적 언어(what + how)
    - 관계 대수는 상용 관계 DBMS들에서 널리 사용되는 SQL의 이론적인 기초
    - 관계 대수는 SQL을 구현하고 최적화하기 위해 DBMS의 내부 언어로서도 사용됨
- SQL
  - 상용 관계 DBMS들의 사실상의 표준 질의어인 SQL을 이해하고 사용할 수 있는 능력은 매우 중요함
  - 사용자는 SQL을 사용하여 관계 데이터베이스에 릴레이션을 정의하고, 관계 데이터베이스에서 정보를 검색하고, 관계 데이터베이스를 갱신하며, 여러 가지 무결성 제약조건들을 명시할 수 있음



## 관계 대수

- 관계 대수 (relational algebra)
  - 연산자(관계 연산자), 피연산자(관계)가 존재 
  - relation을 input으로 받아서 새로운 relation을 output으로 내보냄
  - 기존의 릴레이션들로부터 새로운 릴레이션을 생성함
  - 릴레이션이나 관계 대수식(이것의 결과도 릴레이션임)에 연산자들을 적용하여 보다 복잡한 관계 대수식을 점차적으로 만들 수 있음
  - 기본적인 연산자들의 집합으로 이루어짐
  - 산술 연산자와 유사하게 단일 릴레이션이나 두 개의 릴레이션을 입력으로 받아 하나의 결과 릴레이션을 생성함
  - 결과 릴레이션은 또 다른 관계 연산자의 입력으로 사용될 수 있음
- 관계 연산자들의 종류
  - 필수적인 연산자
    - selection
    - projection
    - union
    - difference
    - Cartesian product
  - 편의를 위해 유도된 연산자
    - intersection
    - theta join
    - equijoin
    - natural join
    - semijoin
    - division
- 실렉션 연산자
  - 한 릴레이션(=table)에서 실렉션 조건(selection condition)을 만족하는 투플들의 부분 집합을 생성
  - predicate : 조건인 참인지 거짓인지를 판단하는 논리식. 실렉션 조건을 predicate라고도 함
  - 단항 연산자
  - 결과 릴레이션의 차수는 입력 릴레이션의 차수와 같음
  - 결과 릴레이션의 카디날리티는 항상 원래 릴레이션의 카디날리티보다 작거나 같음
  - 카디날리티 : 릴레이션 튜플의 개수
  - 실렉션 조건은 일반적으로 릴레이션의 임의의 애트리뷰트와 상수, 비교연산자(<, >, == 등), 부울 연산자(AND, OR, NOT 등)를 포함할 수 있음

- 프로젝션 연산자
  - 한 릴레이션의 애트리뷰트들의 부분 집합을 구함
  - 특정 column을 선택해서 구함
  - 중복 제거의 경우 시간이 매우 오래걸림
  - 실렉션의 결과 릴레이션에는 중복 튜플이 존재할 수 없지만, 프로젝션 결과 릴레이션의 경우 중복 튜플이 존재할 수 있음
- 집합 연산자
  - 릴레이션이 튜플들의 집합이기 때문에 기존의 집합 연산이 릴레이션에 적용됨
  - 세 가지 집합 연산자 : 합집합, 교집합, 차집합
  - 두 릴레이션이 집합 연산을 수행할 수 있는 조건(합집합 호환 : union compatible)을 만족해야 함
  - 합집합 호환 : 두 릴레이션 R1(A1, A2, ..., An)과 R2(B1, B2, ..., Bm)이 합집합 호환일 필요 충분 조건은 n=m이고 모든 1<=i<=n에 대해 domain(Ai)=domain(Bi)
  - 이항 연산자
- 카티션 곱 연산자
  - 카디날리티가 i인 릴레이션R(A1, A2, ... An)과 카디날리티가 j인 릴레이션 S(B1, B2, ..., Bm)의 카티션 곱 R × S는 차수(degree)가 n+m이고, 카디날리티가 i*j이고 애트리뷰터가 (A1, A2, ..., An, B1, B2, ..., Bm)이며 R과 S의 투플들의 모든 가능한 조합으로 이루어진 릴레이션
  - 카티션 곱의 결과 릴레이션의 크기가 매우 클 수 있으며, 사용자가 실제로 원하는 것은 카티션 곱의 결과 릴레이션의 일부인 경우가 대부분이므로 카티션 곱 자체는 유용한 연산자가 아님
  - 실제 응용에서는 사용되지 않고 join을 위한 연산일 뿐

- 관계 대수의 완전성
  - 실렉션, 프로젝션, 합집합, 차집합, 카티션 곱은 관계 대수의 필수적인 연산자
  - 다른 관계 연산자들은 필수적인 관계 연산자를 두 개 이상 조합하여 표현할 수 있음
  - 임의의 질의어(e.g. SQL)가 적어도 필수적인 관계 대수 연산자들만큼의 표현력을 갖고 있으면 **관계적으로 완전(relationally complete)**하다고 말함
- 조인 연산자
  - 두 개의 릴레이션으로부터 연관된 튜플을 결합하는 연산자
  - 관계 데이터베이스에서 두 개 이상의 릴레이션들의 관계를 다루는데 매우 중요한 연산자
  - theta join, equijoin, natural join, outer join, semijoin 등

- 세타 조인과 동등 조인
  - 세타 연산자 {=, !=, <=, <, >=, >} 를 이용한 조인
  - 동등 조인의 경우 연산자가 =인 경우
- 자연 조인
  - 동등 조인의 결과 같은 값의 attribute가 중복해서 발생하므로 하나로 합친 join
  - 가장 많이 사용됨
- 디비전 연산자
  - 차수가 n+m인 릴레이션 R(A1, A2, ..., An, B1, B2, ..., Bm)과 차수가 m인 릴레이션 S(B1, B2, ..., Bm)의 디비전 R÷S는 차수가 n이고, S에 속하는 모든 투플 u에 대하여 투플 tu(투플 t와 u를 결합한 것)가 R에 존재하는 투플 t의 집합
  - 예시 : 전공 과목 5과목이 있고 학생들의 수강 정보를 모두 포함하는 테이블이 있을 때 모든 전공과목을 수강 중인 학생을 선택하기 위해서 전공과목 5개의 릴레이션으로 나누어주면 됨

- 관계 대수의 한계
  - 관계 대수는 산술 연산(덧셈, 뺄셈 등)을 할 수 없음
  - 집단 함수(aggregate function)를 지원하지 않음 : 개별적인 항목은 연산할 수 있지만 통계적인 값을 구할 수는 없음
  - 정렬을 나타낼 수 없음
  - 데이터베이스를 수정할 수 없음
  - 프로젝션 연산의 결과에 중복된 투플을 나타내는 것이 필요할 때가 있는데, 이를 명시하지 못함

- 추가된 관계 대수 연산자
  - 집단 함수 : 평균, 합계 등
  - 그룹화 : 부서 별 평균 등
  - 외부 조인
    - 상대 릴레이션에서 대응되는 투플을 갖지 못하는 투플이나 조인 attribute에 NULL 값이 들어있는 투플들을 다루기 위해서 조인 연산을 확장한 조인
    - 두 릴레이션에서 대응되는 투플들을 결합하면서, 대응되는 투플을 갖지 않는 투플과 join attribute에 NULL 값을 갖는 투플도 결과에 포함시킴
    - left outer join, right outer join, full outer join
  - left outer join
    - 릴레이션 R과 S의 조인을 할 때  R의 모든 투플들을 결과에 포함시키고, 만일 릴레이션 S에 관련된 투플이 없으면 결과 릴레이션에서 릴레이션 S의 attribute 들은 NULL 값으로 채움
  - 완전 외부 조인
    - left outer join + right outer join



## SQL 개요

- SQL 개요
  - SQL은 현재 DBMS 시장에서 관계 DBMS가 압도적 우위를 차지하는데 중요한 요인 중 하나
  - SQL은 IBM 연구소에서 1974년에 System R이라는 관계 DBMS 시제품을 연구할 때 관계 대수와 관계 해석을 기반으로 집단 함수, 그룹화, 갱신 연산 등을 추가하여 개발된 언어
  - 1986년에 ANSI(미국 표준 기구)에서 SQL 표준을 채택함으로써 SQL이 널리 사용되는데 기여
  - 다양한 상용 관계 DBMS마다 지원하는 SQL 기능에 다소 차이가 있음 
  - SQL은 비절차적 언어(선언적 언어)이므로 사용자는 자신이 원하는 바(what)만 명시하며, 원하는 것을 처리하는 방법(how)은 명시할 수 없음
  - 관계 DBMS는 사용자가 입력한 SQL문을 번역하여 사용자가 요구한 데이터를 찾는데 필요한 모든 과정을 담당
  - 자연어에 가까운 구문을 사용하여 질의를 표현할 수 있음
  - 두 가지 인터페이스
    - 대화식 SQL(interactive SQL)
    - 내포된 SQL(embedded SQL)
- SQL의 구성 요소
  - 데이터 정의어(DDL)
  - 데이터 조작어(DML)
  - 데이터 제어어(DCL)



## 데이터 정의어와 무결성 제약조건

- 데이터 정의어(DDL)
  - CREATE : 생성
  - ALTER : 변경
  - DROP : 제거
  - 스키마의 생성과 제거
    - SQL2에서는 동일한 데이터베이스 응용에 속하는 릴레이션, 도메인, 제약조건, 뷰, 권한 등을 그룹화하기 위해서 스키마 개념을 지원
- 릴레이션 정의

```sql
CREATE TABLE DEPARTMENT 
	(DEPTNO INTEGER NOT NULL,
     DEPTNAME CHAR(10),
     FLOOR INTEGER,
     PRIMARY KEY(DEPTNO));
```

- 릴레이션의 정의에 사용되는 데이터 타입
  - INTEGER 또는 INT : 정수형
  - SMALL INT : 작은 정수형
  - NUMBER(n, s) 또는 DECIMAL(n, s) : n개의 숫자에서 소수 아래 숫자가 s개인 십진수
  - REAL : 실수형
  - FLOAT(n) : 적어도 n개의 숫자가 표현되는 실수형
  - CHAR(n) 또는 CHARACTER(n) : n바이트 문자열. n을 생략하면 1
  - VARCHAR(n) 또는 CHARACTER VARYING(n) : 최대 n바이트까지의 가변 길이 문자열
  - BIT(n) 또는 BIT VARYING(n) : n 개의 비트열 또는 최대 n개까지의 가변 비트열
  - DATE : 날짜형
  - BLOB : Binary LargeObject. 멀티미디어 데이터 등을 저장
- 릴레이션 제거

```sql 
DROP TABLE DEPARTMENT;
```

- ALTER TABLE

```sql
ALTER TABGLE EMPLOYEE ADD PHONE CHAR(13);
```

- 인덱스 생성
  - 검색을 빠르게 하기 위해 인덱스 추가

```sql
CREATE INDEX EMPDNO_IDX ON EMPLOYEE(DNO);
```

- 도메인 생성

```sql
CREATE DOMAIN DEPTNAME CHAR(10) DEFAULT '개발';
```

- 제약 조건

  - 릴레이션 정의에서 다양한 제약 조건을 명시
  - NOT NULL, CHECK를 이용해 조건을 명시할 수 있다

  ```SQL
  CREATE TABLE EMPLOYEE
  	(EMPNO INTEGER NOT NULL
       SALARY INTEGER CHECK (SALARY < 6000000),
       DNO INTEGER CHECK (DNO IN (1,2,3,4)) DEFAULT 1,
       PRIMARY KEY(EMPNO));
  ```

  - DELETE, UPDATE시에도 제약 조건 유지 가능
  - 무결성 제약조건의 추가 및 삭제

  ``` sql 
  ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_PK PRIMARY KEY (STNO);
  -- 제약조건의 이름을 STUDENT_PK로 지정함 
  -- 제약 조건을 위배했을 경우 어떤 조건인지 알기 쉬움
  ```

  ```SQL
  ALTER TABLE STUDENT DROP CONSTRAINT STUDENT_PK;
  ```

  

## SELECT문

- SELECT문

  - 관계 데이터베이스에서 정보를 검색하는 SQL문
  - 관계 대수와 실렉션과 의미가 완전 다름
  - 관계 대수의 실렉션, 프로젝션, 조인, 카디션 곱 등을 결합한 것
  - 관계 데이터베이스에서 가장 자주 사용됨 
  - SELECT 결과 새로운 릴레이션이 생성됨

- 기본적인 SQL 질의

  - SELECT절과 FROM 절만 필수적이고 나머지는 선택 사항

    ```SQL
    SELECT 		(DISTINCT) 어트리뷰트(를)
    FROM 		리레이션(들)
    (WEHRE		조건
    					(중첩 질의))
    (GROUP BY	애트리뷰트(들))
    (HAVING		조건)
    (ORDER BY 	어트리뷰트(들) (ASC|DESC));
    ```

  - 별칭(alias)

    - 서로 다른 릴레이션에 동일한 이름을 가진 애트리뷰터가 속해 있을 때 애트리뷰트의 이름을 구분하는 방법

    ```sql
    EMPLOYEE.DNO
    
    FROM EMPLOYEE AS E, DEPARTMENT AS D
    ```

  - 릴레이션의 모든 애트리뷰트나 일부 애트리뷰트 검색 : *

  - 문자열 비교 

    - % : 길이 상관 없음

    ```SQL
    WHERE EMPNAME LIKE '이%';
    -- '이'로 시작하는 모든 이름
    ```

  - 연산자들의 우선 순위

    1. 비교 연산자
    2. NOT
    3. AND
    4. OR

  - 범위를 사용한 검색

  ```SQL
  SELECT		EMPNAME, TITLE, SALARY
  FROM		EMPLOYEE
  WHERE		SALARY BETWEEN 3000000 AND 4500000;
  -- 아래와 같음
  SELECT		EMPNAME, TITLE, SALARY
  FROM		EMPLOYEE
  WHERE		SALARY >= 3000000 AND SALARY <= 4500000;
  ```

  - 리스트를 사용한 검색 : IN

  ```SQL
  SELECT 		*
  FROM 		EMPLOYEE
  WHERE 		DNO IN (1, 3);
  ```

  - SELECT절에서 산술연산자(+, -, *, /) 사용

  ```SQL
  SELECT 		EMPNAME, SALARY, SALARY * 1.1 AS NEWSALARY
  FROM		EMPLOYEE
  WHERE		TITLE = '과장';
  ```

  - NULL 값
    - NULL 값을 포함한 다른 값과 NULL 값을 +, - 등을 연산하면 결과는 NULL
    - COUNT(*)를 제외한 집단 함수들은 NULL 값을 무시함
    - 어떤 애트리뷰트에 들어 있는 값이 NULL인가 비교하기 위해서 'DNO=NULL'처럼 나타내면 안됨
    - 'DNO IS NULL'이 맞는 표현. NULL은 값이라고 보기 어려움.

- ORDER BY 절

  - 사용자가 SELECT문에서 질의 결과의 순서를 명시하지 않으면 릴레이션에 투플들이 삽입된 순서대로 사용자에게 제시됨
  - ORDER BY 절에서 하나 이상의 애트리뷰트를 사용하여 검색 결과를 정렬할 수 있음
  - SELECT 문에서 가장 마지막에 사용되는 절
  - 디폴트 정렬 순서는 오름차순(ASC)
  - DESC를 지정하여 정렬 순서를 내림차순으로 지정 가능
  - NULL 값은 오름차순에서는 가장 마지막에 나타나고, 내림차순에서는 가장 앞에 나타남
  - SELECT절에 명시한 애트리뷰트들을 사용해서 정렬해야 함

- 집단 함수
  - 데이터베이스에서 검색된 여러 투플들의 집단에 적용되는 함수
  - 한 릴레이션의 한 개의 애트리뷰트에 적용되어 단일 값을 반환함
  - SELECT절과 HAVING 절에만 나타날 수 있음
  - COUNT(*)를 제외하고는 NULL 값을 제거한 후 남아 있는 값들에 대해서 집단 함수의 값을 구함
  - COUNT(*)는 결과 릴레이션의 모든 행들의 총 갯수를 구하는 반면에 COUNT(애트리뷰트)는 해당 애트리뷰트에서 NULL 값이 아닌 값들의 갯수를 구함 
  - 키워드 DISTINCT가 집단 함수 앖에 사용되면 집단 함수가 적용되기 전에 먼저 중복을 제거함
  - 집단 함수의 기능
    - COUNT : 투플이나 값들의 개수
    - SUM : 값들의 합
    - AVG : 값들의 평균값
    - MAX : 값들의 최대값
    - MIN : 값들의 최소값

- 그룹화

  - GROUP BY 절에 사용된 애트리뷰트에 동일한 값을 갖는 투플들이 각각 하나의 그룹으로 묶임

  - 이 애트리뷰트를 그룹화 애트리뷰트(group attribute)라고 함

  - 각 그룹에 대하여 결과 릴레이션에 하나의 투플이 생성됨

  - SELECT절에는 각 그룹마다 하나의 값을 갖는 애트리뷰트, 집단 함수, 그룹화에 사용된 애트리뷰트들만 나타날 수 있음

    