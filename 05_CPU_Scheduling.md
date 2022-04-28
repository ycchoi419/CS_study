# CPU Scheduling

## CPU and I/O Burst in Program Execution

- 프로세스는 CPU를 사용하는 단계(CPU burst)와 I/O 작업을 수행하는 단계(I/O burst)가 번갈아서 진행됨

- 어떤 프로그램은 CPU를 많이 쓰고 (CPU bound job), 어떤 프로그램은 I/O 작업이 많음(I/O bound job)
  - I/O bound process
    - CPU를 잡고 계산하는 시간보다 I/O에 많은 시간이 필요한 job
    - many short CPU bursts
  - CPU-bound process
    - 계산 위주의 job
    - few very long CPU bursts
- 여러 종류의 job(=process)이 섞여 있기 때문에 CPU 스케줄링이 필요하다
  - interactive job에게 적절한 response 제공 요망
  - CPU와 I/O 장치 등 시스템 자원을 골고루 효율적으로 사용

- 사람과 상호작용하는 프로그램(I/O bound job)에게 더 CPU를 많이 분배할 필요가 있다. 



## CPU Scheduler and Dispatcher

- CPU Scheduler

  - 누구에게 CPU를 줄지를 결정
  - Ready 상태의 프로세스 중에서 이번에 CPU를 줄 프로세스를 고른다. 
  - CPU scheduler는 하드웨어? 프로그램?
    - 운영체제 안에서 CPU 스케줄링을 하는 코드를 그냥 CPU scheduler라고 부름 (운영체제 내의 기능)

- Dispatcher

  - 누구에게 CPU를 줄지 결정했으면 프로세스를 넘겨주는 친구가 dispatcher
  - CPU의 제어권을 CPU scheduler 에 의해 선택된 프로세스에게 넘긴다. 
  - 이 과정을 context switch(문맥 교환)이라고 한다. 
  - 마찬가지로 운영체제 내의 기능

- CPU 스케줄링은 언제 필요한가?

  - Running -> Blocked : I/O 요청하는 시스템 콜
  - Running -> Ready : 할당 시간 만료로 timer interrupt(CPU를 빼앗는 것)
  - Blocked -> Ready : I/O 완료 후 interrupt
  - Terminate : 프로세스가 끝났을 때

  > 첫번째, 네번째는 nonpreemptive(강제로 빼앗지 않고 자진 반납)
  >
  > 두번째, 세번째는 preemptive(강제로 빼앗음 )