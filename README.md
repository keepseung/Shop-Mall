# Shop-Mall
옷의 카테고리, 브랜드 가격 정보를 조회할 수 있는 서비스입니다.

### Prerequisites
- Java 17

### Dependencies
Dependence         |Version
-------------------|-------
spring-boot       |2.7.1
spring-boot-starter-data-jpa |
spring-boot-starter-web |
spring-boot-starter-validation |
com.h2database:h2 |
org.projectlombok:lombok |
springfox-swagger-ui | 3.0.0
yaml-resource-bundle | 1.1
----

## 특이사항
* API 리스트 확인 및 테스트하는 방법   
  서버 기동 이후 http://localhost:8080/swagger-ui/index.html 에서 가능합니다.    
* 샘플 데이터는 서버 기동시 com.seung.shopmall.config.AppConfig 파일에서 추가합니다.
* H2 데이터베이스의 인메모리 모드를 사용하므로 http://localhost:8080/h2-console 에서 데이터 확인 가능합니다.
* JPA의 JPQL을 사용해 저장되는 저장소는 언제든지 변경이 될 수 있게 했습니다.
* 빠르게 응답하기 위해 Product 테이블에 brand_id와 category 컬럼으로 복합 Index를 생성했습니다.
* API 응답이 실패 할 경우, 실패 값과 실패 사유를 전달하기 위해 exception.yml에 실패 코드, 실패 사유를 관리할 수 있게 했습니다.
  * 또한 RestControllerAdvice를 사용해 예외별 응답 바디를 처리할 수 있게 했습니다.
