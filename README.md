# π§ͺcrud project
---
## π‘ μλ΄
- λ©ν λ§μ μν μ½λμλλ€.
- κ°λ¨ν CRUD νλ‘κ·Έλλ°μ μλ €μ€λλ€.

## πΉ μ¬μ© κΈ°μ 
- Spring Boot : `2.7.1`
- MyBatis : `2.2.2`
- Thymeleaf
- H2 Database : `1.4.200 `

## π ν΄λ
- crud-test : λ©ν λ§ μ½λ© νμΌ
- hi-test : μμ μλ£ νμΌ

## β H2 database setting
- Generic H2 (Embedded) : `jdbc:h2:~/crud` <- μ΅μ΄ μ μ
- Generic H2 (Embedded) : `jdbc:h2:tcp://localhost/~/crud` <- μ΄ν μ μ
```java
create table user(
	id varchar(255),
	pw varchar(255)
)
```
