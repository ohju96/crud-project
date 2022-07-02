# crud project
---
## 💡 안내
- 멘토링을 위한 코드입니다.
- 간단한 CRUD 프로그래밍을 알려줍니다.

## 🏹 사용 기술
- Spring Boot : `2.7.1`
- MyBatis : `2.2.2`
- Thymeleaf
- H2 Database : `1.4.200 `

## 📚 폴더
- crud-test : 멘토링 코딩 파일
- hi-test : 수업 자료 파일

## ⚙ H2 database setting
- Generic H2 (Embedded) : `jdbc:h2:~/crud` <- 최초 접속
- Generic H2 (Embedded) : `jdbc:h2:tcp://localhost/~/crud` <- 이후 접속
```java
create table user(
	id varchar(255),
	pw varchar(255)
)
```
