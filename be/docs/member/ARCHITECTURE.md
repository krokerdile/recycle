# Member ARCHITECTURE

```
                          ____________
jwt/refresh (Reids) <--> |            |
                         | member-api | <--> db (Mysql)
session (Redis)     <--> |            | 
                         |____________| <--> event source (Rabitmq)
```

## JWT/REFRESH

### Refresh 토큰 로테이션(RTR)

멤버 서버의 경우 Refresh 토큰 로테이션 방식을 사용하여 토큰을 관리합니다.

Refresh 토큰 로테이션 방식은 아래와 같습니다.

1. Refresh, Access 토큰을 발급합니다.
2. Refresh 토큰을 Redis에 저장합니다.
3. Access 토큰을 사용합니다.
4. Access 토큰이 만료되면 Refresh 토큰을 사용하여 새로운 Access 토큰과 Refresh 토큰을 발급합니다.
5. 갱신된 Refresh 토큰을 Redis에 저장합니다.

#### 예상되는 토큰 로테이션 장점

기존 토큰 방식은 토큰이 탈취되면 탈취된 토큰을 파악할 수 없다는 단점이 있습니다.

하지만 토큰 로테이션 방식은 토큰을 갱신할 때마다 이전 토큰을 파기하고 새로운 토큰을 발급하기 때문에 이전에 사용된 토큰을 사용한다는 것은 해당 토큰이 탈취되었다는 것을
의미합니다.

이를 통해 탈취된 토큰을 파악할 수 있습니다.

##### 예상되는 토큰 로테이션 단점

토큰 로테이션 방식은 토큰을 갱신할 때마다 이전 토큰을 파기하고 새로운 토큰을 발급하기 때문에 토큰에 관한 정보를 갱신해 주어야 합니다.

이는 Redis에 저장되는 토큰의 수가 증가하게 되고, Redis의 메모리를 많이 사용하게 됩니다.

### Redis

우선 해당 과정을 Redis를 통해 구현하는 이유는 다음과 같습니다.

멤버 서버는 여러 대의 인스턴스로 구성될 수 있습니다.

멤버 서버의 인스턴스가 여러 대로 구성되어 있으면, 여러 인스턴스가 공유할 수 있는 저장소가 필요합니다.

Redis는 멤버 서버의 인스턴스 간의 공유 저장소로 사용됩니다.

이를 통해 멤버 서버의 인스턴스를 확장할 수 있습니다.

**추후 고려 사항)**

Redis를 많은 인스턴스가 공유한다면 Redis의 쓰기와 읽기를 모두 처리하기에 많은 부하가 발생할 수 있다.

이를 해결하기 위해 Redis를 여러 개의 인스턴스로 구성하여 Redis의 쓰기와 읽기를 분리할 수 있다.

### 구현

RTR을 위해서는 Redis, Mysql이 필요합니다.

토큰이 발급 시 access 토큰(AT)와 refresh 토큰(RT)을 발급합니다.

RT는 Redis에 저장하고 AT와 RT를 클라이언트에게 전달합니다.

#### 토큰 갱신 (RT1 on Redis / AT1, RT1 -> AT2, RT2)

AT1이 만료되면 클라이언트는 RT1를 사용하여 AT를 갱신합니다.

이때 RT1이 만료되어 있지 않다면 RT1이 Redis에 저장되어 있는지 확인합니다.

RT1이 Redis에 저장되어 있다면 RT1를 사용하여 갱신된 AT2, RT2를 발급합니다.

이후 갱신에 RT1는 Redis에서 사용되었음을 표시합니다.

#### 토큰 갱신 (RT1, RT2 off Redis / AT2, RT2 -> AT3, RT3)

위에서 토큰을 한번 갱신한 이후 긴 시간 동안 토큰 갱신에 대한 요청이 없다면 Redis의 토큰을 Mysql로 옮깁니다.

즉, RT1, RT2를 Redis에서 삭제하고 RT1, RT2를 Mysql에 저장합니다.

이렇게 Mysql로 토큰 정보가 옮겨진 이후 토큰을 갱신하려고 하는 상황을 가정해 봅시다.

사용자가 RT2를 사용하여 토큰을 갱신하려 합니다.

이때 RT2는 Redis에 저장되어 있지 않습니다.

따라서 Mysql에 저장되어 있는 RT2를 사용하여 AT3, RT3를 발급합니다.

이후 갱신에 RT2는 Mysql에서 사용되었음을 표시합니다.

그리고 RT3를 Redis에 저장합니다.

#### 탈취 감지 (RT1)

RTR의 장점은 토큰 탈취를 감지할 수 있다는 것입니다.

RT1이 탈취되었다고 가정해 봅시다.

RT1이 탈취되었다는 것은 RT1이 Redis 혹은 Mysql에 탈취된 토큰이 이미 사용되었다고 기록되어 있음을 의미합니다.

따라서 RT1을 사용하여 토큰을 갱신하려고 하면 토큰 갱신에 실패합니다.

그렇게 탈취가 감지되면 해당 토큰의 사용자에게 알림을 보내고, 해당 토큰을 파기합니다.

_알림은 이메일을 통해 접속 ip 주소(RemoteAddr), 접속 시간 등의 정보를 제공합니다._

## Session

### 이메일 인증 과정 간 Session 활용 방안

이메일 인증 과정에서 Session을 활용해 보려 합니다.

활용 방안은 아래와 같습니다.

1. 인증 요청과 인증 검증 간 Session이 동일한지 파악하여 인증 요청과 인증 검증이 동일한 사용자에 의해 이루어졌는지 확인합니다.
2. 과도한 인증 요청을 방지하기 위해 인증 요청 시 Session 마다 인증 요청 횟수를 제한 합니다.

### 구현

#### 동일한 사용자 인증 여부 확인

인증 요청 시 해당 사용자가 임시 블랙 리스트에 있는지 확인합니다.

이메일 인증 요청 시 Session을 생성 혹은 조회합니다.

이 Session과 이메일, 검증 값, 요청 횟수, 인증 시도 횟수, 정보를 Redis에 저장합니다.

이메일 인증 검증 시 Session을 조회합니다.

이 Session을 통해 검증할 이메일, 검증 값을 조회합니다.

Redis에서 조회한 정보와 인증 검증 시 전달받은 정보를 비교합니다.

#### 인증 요청 횟수 제한

Session을 기준으로 인증 요청 횟수를 제한합니다.

인증 요청 횟수를 초과한다면 해당 사용자를 임시 블랙 리스트에 추가합니다.

임시 블랙 리스트는 Redis의 TTL 기능을 사용하여 일정 시간이 지나면 자동으로 삭제됩니다.