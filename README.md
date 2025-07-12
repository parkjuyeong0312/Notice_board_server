# 🚀 Stack Sticker API

> ✨ **Stack Sticker**는  
> GitHub 사용자 프로필의 contribution, 사용 언어, 주요 repository를 분석하여  
> 마치 노트북에 스티커를 붙이듯  
> 랜덤하게 기술 스택 로고를 배치한 이미지를 생성해주는 API 서버입니다.
>
> README.md 등에서 `<img src="...">` 태그 하나로  
> 나만의 기술 스택 스티커 보드를 꾸밀 수 있습니다.  
>
> 🛠 **Spring Boot + Gradle + PostgreSQL + Redis + AWS** 기반으로 개발 중입니다.

---

## 📌 주요 기능

- ✅ GitHub Public 데이터 기반 분석  
- ✅ 사용 언어 TOP5 자동 스티커 배치  
- ✅ `?logo=python,java` 파라미터로 원하는 스택 추가  
- ✅ PNG / SVG 이미지 즉시 생성  
- ✅ README에 `<img src="https://...">` 삽입 시 자동 최신화  
- ✅ JWT 인증 후 Private repo 포함 가능 (예정)  
- ✅ Redis 캐싱으로 빠른 응답 & API Rate Limit 방지

---

## ⚙ 기술 스택

| 구성              | 내용                                 |
|-------------------|-------------------------------------|
| **Language**      | Java 17                             |
| **Framework**     | Spring Boot (Gradle)                |
| **Build Tool**    | Gradle 8.14.3 (Groovy DSL)          |
| **Test**          | JUnit Jupiter (JUnit 5)             |
| **DB**            | PostgreSQL (ERD 설계 예정)          |
| **Cache**         | Redis (GitHub API 캐싱)             |
| **API Doc**       | Swagger (springdoc-openapi 예정)    |
| **Deploy**        | AWS EC2 + Docker + RDS + ElastiCache|

---

## 🏗 프로젝트 구조

> `gradle init`으로 CLI 직접 초기화

- 타입: `Single application project`
- 언어: `Java`
- Java Version: `17`
- Build Script DSL: `Groovy`
- Test Framework: `JUnit Jupiter`
