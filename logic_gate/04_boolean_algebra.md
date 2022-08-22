# 부울 대수

## 부울 대수(Boolean Algebra)란?

- George Boole, 1854 저서에서 발표
- Claude Shannon, 1938년 부울의 이론을 논리 회로에 적용
- {(1,0), variables, (NOT, AND, OR), Theorems}
- 논리 회로를 표현하고 분석하기 위한 수학적 도구임

## 부울 대수 법칙

### 교환법칙 (Commutative Law)

- 부울 합 : A | B = B | A

- 부울 곱 : A & B = B & A

### 결합 법칙 (Assoociative Law)

- A | (B | C) = (A | B) | C
- A & (B & C) = (A & B) & C

### 분배 법칙 (Distributive Law)

- A & (B | C) = (A&B) | (A&C)

- A | (B&C) = (A|B)&(A|C)

  > A or (B and C) = (A or B) and (A or C)

## 부울 대수의 규칙

1. A | 0 = A
2. A | 1 = 1
3. A | A = A
4. A | ~A = 1
5. A & 0 = 0
6. A & 1 = 1
7. A & A = A
8. A & ~A = 0
9. ~(~A) = A

## 드모르간의 정리 (De Morgan's Theorem)

~(A & B) = ~A | ~B

~(A | B) = ~A & ~B

## 곱의합(Sum-of-Product)과 합의곱(Product-of-Sum)

- 곱의 합(SOP)
  - ~은 따로따로 풀어주어야 함
  - AND-OR logic으로 구현됨 
  - 모든 부울 수식은 SOP로 구현 가능
  - 각항을 계산한 다음 항 중 하나라도 1이면 1

- 합의 곱(POS)
  - SOP 와 POS는 dual 관계
  - POS는 OR-AND logic으로 표현 가능
  - 각 인수 중 하나라도 0이면 0

