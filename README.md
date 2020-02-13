![npm](https://img.shields.io/npm/v/npm?style=for-the-badge) ![SSAFY](https://img.shields.io/badge/SSAFY-2%EA%B8%B0-informational?style=for-the-badge)




# :coffee: 라떼는 말이야...

## Overview
  본 프로젝트의 이름인 `라떼는 말이야`는 스타벅스, 공차, 탐앤탐스 등 프렌차이즈 카페가 아닌 개인 카페를 운영하시는 자영업자들을 위한 Web 서비스 입니다. 프로젝트의 구성은 크게 프론트엔드는 Vue.js, 백엔드는 Spring boot로 구성되어 있습니다.

  클라이언트와 서버는 REST API를 기반으로 데이터 통신을 합니다.

본 프로젝트의 Git을 클론하고 싶으시면 아래와 같이 명령어를 수행하십시오.
```bash
$ git clone https://lab.ssafy.com/webmobile1-sub2/s02p13b106.git
```



## Front End
- Vue.js
- Bootstrap
- TOAST UI
- Vuex
- vue-router.js
- store.js

#### 1. 개발 환경

> Vuejs로 구성한 SPA입니다.
>
> ```bash
> # ./frontend/
> $ npm i -g @vue/cli && npm i
> ```

| ![](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABmJLR0QA/wD/AP+gvaeTAAAEzUlEQVRoge1YXW8bRRQ9d9frxknsxKYkhgQS54FCQRVRo9hOQnksqlB/AEJ8SaBKSE7TNqVN4mRoKqACpVQC8UJpQZQHxEMFRe0bD5VwxYdUECKiLQhI2xhK4jhRnPpjd3iwLDnxzuz6oxISPm+7586998zMzhwbqKOOOuqo4/8MKn549dLRC+DYKQr+7eICbl5eMeVUTeFtj7ge/Wj0xI+ygs+8Obzt75/WLutZg8z4zu0t6B7wihNwfDk1MPFk4VFZzyn7AGRFY7uCXjibVFNOzxp0e8H4VNY8AKz9o58VNe9sVHFfX4tseEYx+P7iF+sEsNDYzxx4TzRadSq4PyiencTc2pZnj408L+Kfe2Pfi0vX0wER3z3ohepURDQ48HZ0KPpL8buS6E05lQG4JUri39oMd5tTWCF5M32CMVaSl33FHIkbt4+Dc9OhHn8D7t7SJGwewF8NmuO1jS9LCh1+7HCCiCaEaQgIPO4DkekuwOqtjPtXR/Ldje+vXVw6mVrImHdIQM8OrzAnABCng4f6DiUtBQCAEcy+D9C3omQefwM2P9AoLLb4e+qlPTMjHYXnPTMjHYk/154Wxbc/6EZz+yZhPoC+N8LZj80YUwGMmME53wvAfL0BdA/6oGrmM5ZJ6crSov5Z4TmxYHyeSemmtVRNQVe4VdI8DIL+MiNmmJHiNQPAYtNnCPSUiJ/7Lok/YglTjlTCPb2tOx061+Z+SJ7jhvlcBIZ86Oj1yNo4NRWeeEFEij95ACD9FQCrIrqj1wNXi8OU4zrH6o3Uh4n51GlR865WDfduc8s6WOZqbkwWIBXAQuw6QK8LB6uE7iGfcHxyPu1fiWc2i/ieHT6QKtkEREdYP4vLepSvAABvwv0WgKsi/q6eRni7XFZpSuALWI675l10v2OVx1JAZFckzTk/KIsJDPmgKNLPaR1IJQQGJXYhHxWJ7IqkrXJZCgAANhA9S8AFEd/o0+CX7+V16Oz1wOXVhDwB56bC4+ft5LIlIJ+UW/ikVqFPKoazSUXndrnfMRRlvyygGLYFRMPRWU4ouWELyPsk6XkOAOgekPsdgB9nwbErdvuyLSCP3BQA4ang3yq/Ue34HU56id+RoSwBLMSWAT4pDJB5Ght+B4TRfA37KHMFAB7ST1r5JLNZbn+o2cLv4NJkcNzU78hQtgBGzCCDhiHzSRv2uepU0BWSHpsG5xgmImFOEcoWAACTg2MxAJ+I+I0njdUJxQmn2cDEN5X0UpEAAOBq7gAA4X4tnPU27ogVzSH5/WGBigWwfhYn4JiIz9+2PstbmgA23jc+X2kfFQsAAMOTk/okX8Bl5XeutiY8wrvFDqoSwB5mGRBGK89Aw3b8jjRDNYMLOBI7ep4DT5Q57Iup8MTuamtXtQIFWPkkE2S4ohyoRe2aCIiGo7MAWXr3AjjHTDl+R4aaCAAATlkGiU8qQhxKTvgrr1zUTAALsWUCRa3iiKhsvyNDzQQAgBHKfgBAfKMSj0WDY2dqWbOmAvI+SRH9n2Rwg/ZW4ndkqKkAIO+TOLjZLJ+q1O/IUHMBAABVH8V6n7Ts0Ky/j0pwRwSwfhYnQtFJw6vyOzLcmRUAYLhzMwCuAJj1a22274j/FFhsejf7erpci1FHHXWUgX8BcmVo3cljsxQAAAAASUVORK5CYII=) Vuejs with @vue-cli | 1. vuex<br />2. vue-router<br />3. axios<br />4. vue-session<br />5. jwt-decode |
| :----------------------------------------------------------: | ------------------------------------------------------------ |
| ![](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABmJLR0QA/wD/AP+gvaeTAAAC8UlEQVRoge2YT0gUURzHv2/cSdl1W6PdEjcsyoJCLNBMTYoicBFNkqBLl4rQjirUIahLSEHszdKITkZ0KcGEtUNUqGWaHaItQROMDcM/qOvqSuu+LonYzNt132veXOZzm/m9+b7vd+bNvDcPsLCwsDATkqxBXVmghoA2UIoiEDhkmAJFBMAA0qi/raeyM1HThAHqygK3AXrtv5pLEQLa3NpXeZ1dZ1BXFqgBaIcxtlKDUKW69V3FC72awrwItME4S6lBSbyRVWMGoEChMXa4KGIVmAEAZBpghBcnq2ATUW3t9SVtQ+MUi+EYZqeiGAvO4dPrCQT7p0CpSM9rCAXYCEQhcLhUOFwqvHucKK/egdBIGO13vmAsOCusn2gIGYY3z4mme8UoPJktrGVKAACwqQou3ChA9i6xV82QIVR/NLDuOMNhw8HybTjXuB/2THWtc1XBibO5eHI3yN2XlCcQjcTQ3/0TT/1fNbUDxW4hbalD6HPvpOZcljtdSFNqgM06ZqNLK0Ka0gLYVAW1V/Zpzv8aj4jpCl3NoOL87nXHzi2bkF/qQfZO7Wq8rysk1JchAc7o3Gk9hj9Ooz8gFsC0eeBz7yRarg5hJSa2pjAtQH6pGxdvFiDLkyGkY1oAohAcOrYdTS3FyLDzj2QpMzEAuLam44gvB6cv74VNXbtvHq8dx2tz0d3+nasvaU9gbnoZLx+PofPhiKZWdIp/USd9CL3X+ep4cuzcetIDEKKzj5B0c4eN9AAlvhzNubmpZW49w//IVnG501Hi86LqUp6mNjw0w61rSICN/CuvEo9TvHk2zt2XafPAKl2PRhAaDXNfL20I/cvSQgzP7w/jbccPIR0pASgFFsO/MT+zjNBoGN8GpjH4agLRSExYWyiA3owrm0TvwII0F8mZZxXYASgGDbHCB9MLO0Aa9RtihQMKwvTCDNDWU9lJQJuNsZQStx70+bpYxaSrkPrS7qq/+/OHIW/HegHABwriT2TewsLCwnz+AA/Twv7KzxMPAAAAAElFTkSuQmCC) Mark-up dependency | 1. moment.js<br />2. vue-parallaxy<br />3. vue2-google-maps<br />4. count-up.js<br />5. TOAST UI editor by NHN <br />6. fontawesome<br />7. Bootstrap4 |

#### 2. UFD (User Flow Diagram)

* (추가예정)

#### 3. 화면 구성

* (추가예정)

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
http://http://13.125.168.55/swagger-ui.html