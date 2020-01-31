# 라떼는 말이야...

## Overview
  본 프로젝트의 이름인 `라떼는 말이야`는 스타벅스, 공차, 탐앤탐스 등 프렌차이즈 카페가 아닌 개인 카페를 운영하시는 자영업자들을 위한 Web 서비스 입니다. 프로젝트의 구성은 크게 프론트엔드는 Vue.js, 백엔드는 Spring boot로 구성되어 있습니다.

  클라이언트와 서버는 REST API를 기반으로 데이터 통신을 합니다.

본 프로젝트의 Git을 클론하고 싶으시면 아래와 같이 명령어를 수행하십시오.
```bash
$ git clone https://lab.ssafy.com/webmobile1-sub2/s02p12b106.git
```

## Front End
- Vue.js
- Bootstrap
- TOAST UI
- Vuex
- vue-router.js
- store.js



## Back End
본 프로젝트의 Back End는 Spring boot 프로젝트 기반으로 구성되어 있습니다.
본 프로젝트에서 사용한 Dependency는 아래와 같습니다.

- Spring Boot
Spring boot application

- Spring Data JPA
Spring용 ORM 프레임워크 

- Spring boot security
사용자 인증과 관련된 보안 요소 처리를 위한 라이브러리

- JWT(JSON Web Token)
로그인 및 인증을 위한 토큰 발급을 위해 

Database 관련 라이브러리
- Hibernate
- MySQL
- Mybatis

본 프로젝트의 Spring boot 프로젝트에 포함된 dependencies는 아래와 같습니다.  

In `pom.xml`
<!-- Spring Boot Web -->
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```
<!-- Spring Boot Security -->
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```
<!-- Spring Boot JPA -->
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

<!-- For Java 8 Date/Time Support -->
```xml
		<dependency>
			<groupId>com.fasterxml.jackson.datatype</groupId>
			<artifactId>jackson-datatype-jsr310</artifactId>
		</dependency>
```
<!-- Spring DevTools, Run 상태에서 변경사항 발생시 자동 적용 및 프로젝트 재실행 자동 -->
```xml
   		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
```
<!-- Mybatis : Java object <-> DB Relation mapping framework -->
```xml
     		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.1</version>
		</dependency>
```

<!-- MySQL : Database(RDBMS) -->
```xml
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```
<!-- Test -->
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```
<!-- Swagger : Composing API Documents -->
```xml
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
```

<!-- JSON Web Token -->
```xml
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
```


### Swagger
현재까지 구현된 본 프로젝트의 모든 REST API는 아래의 주소에서 확인 및 테스트 가능합니다.
http://192.168.31.111:8080/swagger-ui.html
