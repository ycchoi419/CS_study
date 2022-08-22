# FSM

## Asynchronous vs Synchronous

- Synchronous (동기) 회로
  - 모든 F/F이 동시에 동작(e.g. clock의 rising edge에 동시 시작)
  - 모든 F/F의 clk 단자에 같은 clock 신호가 입력
  - 설계가 쉽다. 
- Asynchronous (비동기) 회로
  - 모든 F/F이 동시에 동작하지 않음
  - 모든 F/F의 clk 단자에 같은 clock 신호가 입력되지 않음
  - 설계가 어렵다. 
  - 경우에 따라서는 속도 향상 효과가 있을 수 있음

일반적으로는 다 동기 회로로 설계함. 



### 비동기 카운터의 단점

- 원인 : 카운터의 모든 F/F이 동시에 변하지 않음(비동기)
- 단점
  - 변하는 중간에 다른 값들이 섞여 있어 잘 못 사용될 우려가 있음
  - 지연시간 때문에 Clock 속도가 빠를 경우 사용하기 어려움
- 해결책
  - 동기 카운터를 사용
  - F/F의 clock 단자에는 항상 clock만을 연결하는 동기식 설계가 바람직함



## FSM (Finite State Machine)

한정된 상태를 갖는 유한 상태 머신

### 순차 회로의 종류

- FSM(한정된 상태를 갖는 머신)
  - Counter(계수기)
  - Clock divider (클럭 분주기)
  - FSM
- Register(레지스터)
  - Shift register
  - Parallel register

### FSM 예시

- TV 채널 버튼
  - 위 아래 채널 버튼을 눌렀을 때 다음 상태는 입력 + 현재 상태에 의해 결정됨
  - 현재 상태를 저장하느순차회로로만 가능하며 조합회로로는 불가능

### FSM 개념

- FSM (finite state machine), 유한 상태 머신
  - 유한한 개수의 상태를 가지고 있으며
  - 입력과 현재 상태에 따라 다음 상태가 결정되고
  - 입력과 현재 상태에 따라 출력이 결정되는 머신