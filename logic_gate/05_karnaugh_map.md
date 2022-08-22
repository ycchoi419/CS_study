# 카르노 맵(Karnaugh Map)

- 논리식의 간소화 방법
  - Boolean Algebra(algebraic method)
  - Karnaugh map (map method)
  - Quine-McCluskey(tabular method)
- 카르노맵 간소화 방법
  - 변수의 갯수에 따라 2**n개의 표를 생성
  - 표에 1 또는 0의 값을 채워줌
  - 묶을 수 있는 규칙에 따라 그룹화
  - 그룹을 논리식으로 표현

## 카르노 맵의 기본 성질

- 2-변수 카르노 맵
  - 하나의 변수를 행, 하나의 변수를 열에 배치
- 3-변수 카르노 맵
  - 하나의 변수를 행 또는 열, 나머지 변수들을 조합해서 나머지 열 또는 행에 배치

- 4-변수 카르노 맵
  - 변수를 2, 2로 나누어서 행, 열에 배치(4*4)

- 1이 나온 칸들을 직사각형으로 묶어서 축약 가능 
- 여러 개의 직사각형에 묶여도 됨. 

### BCD to Segment Decoder

