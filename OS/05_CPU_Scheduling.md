# CPU Scheduling

> 1. CPU를 누구에게 줄지 결정
> 2. 한번 줬으면 계속 쓰게 할지 중간에 뺏어올지 결정 

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



## Scheduling Algorithm

- Nonpreemptive : 비선점형
  - CPU를 한번 줬으면 끝날때까지 보장해주는 방법
- Preemptive : 선점형
  - 중간에 다시 빼앗아 오는 방법
  - 현대에는 대부분 선점형 알고리즘을 사용



### FCFS (First-Come First-Served)

- 비선점형 방법
- 먼저 온 프로세스를 먼저 처리함
- 처음 온 프로세스가 처리 시간이 오래 걸리는 경우 그 다음에 온 프로세스의 waiting time이 매우 길어지게 된다. 
- Convoy effect : 처리시간이 짧은 프로세스가 긴 프로세스 이후에 오는 경우 대기시간이 길어짐 



### SJF ( Shortest Job First )

- 각 프로세스의 다음번 CPU burst time을 가지고 스케줄링에 활용
- CPU burst time 이 가장 짧은 프로세스를 제일 먼저 스케줄 
- Two schemes: 
  - Nonpreemptive 
    - 일단 CPU를 잡으면 이번 CPU burst가 완료될 때까지 CPU를 선점(preemption) 당하지 않음
  - Preemptive
    - 현재 수행중인 프로세스의 남은 burst time보다 더 짧은 CPU burst time을 가지는 새로운 프로세스가 도착하면 CPU를 빼앗김
    - 이 방법을 Shortest-Remaining-Time-First(SRTF)라고도 부름
- SJF is optimal
  - 주어진 프로세스들에 대해 minimum average waiting time을 보장

- 문제점 

  - long process의 starvation 문제
  - CPU의 burst time을 정확하게 알 수 없음 : 실제 프로그램에서는 추정해서 사용

  > 다음 CPU Burst Time의 예측 
  >
  > - 다음번 PCU burst time을 어떻게 알 수 있는가
  > - 추정(estimate)만 가능
  > - 과거의 CPU burst time을 이용해서 추정 ( Exponential Averaging )
  >   - 후속 term(최근의 CPU burst time)이 더 많은 가중치가 붙음



### Priority Scheduling

- 우선순위가 가장 높은 프로세스에게 CPU를 주는 것 ( 작은 숫자가 높은 우선순위를 의미함 )
- Preemptive와 Nonpreemptive 두 가지 방법이 있을 수 있다. 
- SJF는 일종의 priority scheduling이다. 
  - priority = predicted next CPU burst time
- 문제점 
  - Starvation : priority가 낮은 프로세스는 계속 실행되지 않을 수 있다. 
- 해결 방법
  - Aging : 시간이 지날 수록 높은 우선순위를 부여하는 방법



### Round Robin (RR)

- 현대적인 시스템에서 사용하는 스케줄링이 RR 기반
- 각 프로세스는 동일한 크기의 할당 시간(time quantum)을 가짐  (일반적으로 10-100 ms)
- 할당 식산이 지나면 프로세스는 선점(preempted)당하고 ready queue의 제일 뒤에 가서 다시 줄을 선다. 
- n개의 process 가 ready queue에 있고 할당 시간이 q time unit인 경우 각 프로세스는 최대 q time unit 단위로 CPU 시간의 1/n을 얻는다. 
  - 어떤 프로세스도 (n-1)q time unit 이상 기다리지 않는다. 
- 장점
  - 응답시간(최초 CPU를 얻는데 걸리는 시간)이 짧아짐
  - 오래 걸리는 프로세스는 자동으로 오래 기다리게 됨 
- 단점 
  - 지나치게 여러 번 뺏었다가 줬다가 하면 그 과정에서 자원이 낭비될 수 있다. 



### Multilevel Queue

- Ready queue를 여러 개로 분할
  - foreground(interactive, 사용자와 주고 받는게 많음)
  - backgroudn(batch - no human interaction)
- 각 큐는 독립적인 스케줄링 알고리즘을 가짐
  - foreground - RR
  - background - FIFO
- 큐에대한 스케줄링이 필요
  - Fixed priority scheduling
    - serve all from foreground then from background
    - Possibility of starvation : priority가 낮으면 영원히 CPU를 얻지 못할 수 있다. 
  - Time slice
    - 각 큐별로 적당한 시간을 할당해줌 (priority가 높을 수록 많은 시간)



### Multilevel Feedback Queue

- 여러 줄로 스케줄링을 하면서 경우에 따라서는 프로세스가 큐 간에 이동을 할 수 있음 
- 에이징(aging)을 이와 같은 방식으로 구현할 수 있다. 
- Multilevel-feedback-queue scheduler를 정의하는 파라미터들
  - queue의 수
  - 각 큐의 스케줄링 알고리즘
  - 프로세스를 상위 큐 또는 하위 큐로 보내는 기준
  - 프로세스가 CPU 서비스를 받으려 할 때 들어갈 큐를 결정하는 기준 
- 우선순위가 높은 큐는 time quantum이 짧아서 빠르게 끝나는 작업을 처리하고 다 처리하지 못하면 아래 큐로 내려보냄. 아래 큐는 time quantum을 더 길게 준다. 하지만 상위 큐가 다 끝나지 않으면 아래 큐는 CPU를 못받음 

- CPU 사용시간이 짧은 프로세스가 CPU 우선순위를 받음 



## Multiple-Processor Scheduling

- CPU가 여러 개인 경우 스케줄링(더 복잡해짐)
- Homogeneous processor인 경우
  - queue에 한 줄로 세워서 각 프로세서가 알아서 꺼내가게 할 수 있다. 
  - 반드시 특정 프로세서에서 수행되어야 한느 프로세스가 있는 경우에는 문제가 더 복잡해짐
- Load sharing
  - 일부 프로세서에 job이 몰리지 않도록 부하를 적절히 공유하는 메커니즘이 필요
  - 별개의 큐를 두는 방법 vs 공동 큐를 사용하는 방법
- Symmetric Multiprocessing (SMP)
  - 각 프로세서가 각자 알아서 스케줄링 결정
- Asymmetric multiprocessing
  - 하나의 프로세서가 시스템 데이터의 접근과 공유를 책임지고 나머지 프로세서는 거기에 따름 



## Real-Time Scheduling

- 정해진 시간안에 끝내야 하도록 스케줄링
- Hard real-time systems
  - hard real-time task는 정해진 시간 안에 반드시 끝내도록 스케줄링 해야함
- Soft real-time systems
  - soft real-time task는 일반 프로세스에 비해 높은 priority를 갖도록 해야함



## Thread Scheduling

- 스레드를 구현하는 방식
- Local Scheduling
  - User level thread의 경우 사용자 수준의 thread library에 의해 어떤 thread를 스케줄할지 결정
  - 운영체제가 스레드의 존재를 모름
- Global Scheduling
  - Kernel level thread의 경우 일반 프로세스와 마찬가지로 커널의 단기 스케줄러가 어떤 thread를 스케줄할지 결정
  - 운영체제가 스레드의 존재를 알고 있음 



## Scheduling Criteria

### Performance Index ( = Performance Measure, 성능 척도 )

- 시스템 입장에서의 성능 척도
  - CPU utilization(이용률)
    - 전체 시간 중 CPU 가 놀지 않고 일한 시간
    - 가능한 한 CPU를 바쁘게 일을 시켜라
  - Throughput(처리량)
    - 주어진 시간동안 몇 개의 작업을 완료했는지
- 프로세스 입장에서의 성능 척도
  - Turnaround time(소요시간, 반환시간)
    - CPU를 쓰러 들어와서 다 쓰고 나갈 때 까지 걸린 시간 
  - Waiting time(대기 시간)
    - ready queue에서 기다리는 데 소모된 총 시간(뺏겼다가 얻었다가 할 때 여러번 ready queue에 가는데 그 시간을 다 합친 것)
  - Response time(응답 시간)
    - ready queue에 들어와서 처음으로 CPU를 얻는 데 걸리는 시간 ( 처음 CPU 얻었으면 그 다음에 기다리는 건 신경 안씀)
    - 처음으로 응답을 하는 것이 중요한 의미를 가지기 때문



## Algorithm Evaluation

- 어떤 알고리즘이 좋은지 판단하는 방법
- Queueing models
  - 확률 분포로 주어지는 arrival rate(단위 시간당 몇 개의 프로세스 요청이 들어오는지)와 service rate(단위 시간당 몇 개의 프로세스를 처리할 수 있는지)등을 통해 각종 performance index 값을 계산 
- Implementation(구현) & Measurement(성능 측정)
  - 실제 시스템에 알고리즘을 구현하여 실제 작업(workload)에 대해서 성능을 측정 비교
  - 실제로 코드 짜서 돌려보는 것
- Simulation (모의 실험)
  - 알고리즘을 모의 프로그램으로 작성 후 trace를 입력으로 하여 비교 

