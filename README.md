# SpringBootBasic Project
## 목적
###  Spring Boot를 이용해서 간단한 API 서버와 게시판을 개발 
* Jump to spring boot 기반( https://wikidocs.net/book/7601 / Github: https://github.com/pahkey/sbb3 )
* 스프링 MVC, JPA, QueryDSL을 이용해서 프로젝트를 진행하고, 이해하는 것을 목표로 함.

## 기능 요구사항
* [x] 게시물 작성 기능
* [x] 답글 작성 기능
* [x] 게시글 수정 기능
* [x] 답글 수정 기능
* [x] 게시글 삭제 기능
* [x] 답글 삭제 기능
* [x] 회원가입 기능
* [x] 로그인 기능
* [x] 로그아웃 기능
* [x] 페이징 기능
* [x] 검색 기능
* [x] 추천 기능
* [x] 마크다운 에디터 추가
* [x] 조회수 기능
* [x] 마이페이지 기능

## 개발 환경
- IDE: IntelliJ IDEA Ultimate
- OS: Windows11
- Java 11
- Spring Boot 2.7.4
- Spring Security
- Spring Data JPA
- QueryDSL
- H2 데이터베이스
- Lombok
- Gradle
- Thymeleaf
- BootStrap


## 개발 과정에서 어려웠던 점
### 동기부여의 문제
* 개요: 초반에는 주말에만 개발을 진행해서, 혼자서 개발하다보니 개발 의욕이 점점 사라지고 몇 개월동안 개발을 중단하게 됨. 마음을 다잡고 최대한 1일 1커밋을 지키는 것을 목표로 함.
### JavaScript 문법 문제
* HTML data-uri로 속성을 주고, 자바스크립트에서 this.dataset.uri로 접근했으나, uri 속성을 인식하지 못했음
*  해결방법: this를 사용하지 않고, element로 접근하니 해결됨
### Entity를 DTO로 변환
* 개요: 초기 개발시에는 DTO를 사용하지 않고 구현했었음. 그러다가 DTO를 사용해서 구현하는 경험이 좋을 것 같다는 생각이 들어 중간에 DTO를 추가하는 작업을 진행하였음.
* * 문제점1: Entity에는 Setter가 없는데, DTO로부터 값을 받아와서 Entity에 집어 넣어야 함.
* * 해결방법:빌더패턴을 통해 해결함. Lombok의 @builder 어노테이션을 이용해서 빌더 패턴을 쉽게 구현할 수 있었음.
* * 문제점2: 위의 해결방안을 통해, Entity를 DTO로 변환하는데 성공했다고 생각했음. 하지만 QuestionEntity와 QuestionDTO간 무한 순환 참조하는 버그에 걸림
* * 아이디어1: ModelMapper, MapStruct 등 라이브러리를 통해 해결해보고자 했지만, 실패했음.
* * 아이디어2: 서비스 레이어에서 toDTO와 toEntity를 구현하고, 여러 메서드를 구현해 해결하고자 했지만, 실패했음.
* * 아이디어3: 생각해보니 DTO에서는 List<AnswerDTO>를 받을 필요가 없다는 생각이 들어서, DTO 안에 내부 static class를 만들어 해결하고자 함.  
* * 해결방법: 아이디어 3을 이용해 서비스단에서 Entity와 DTO간 변환하는 로직을 구현해 해결에 성공했음.
### 추천 서비스 구현
* 문제점: 다대다 구조로 구현하려고 했으나, 양방향 구조로 구현하면 DTO를 만들때처럼 Stack Overflow 오류가 발생함.
* 해결방법: 질문추천, 답변추천 Entity를 따로 분리해 다대일 구조를 만들었음. 즉, 다대일 관계를 두 개를 만들어서, 다대다처럼 활용함. 
### 검색 서비스 구현
* 문제점: 질문한 사람, 제목, 내용에 키워드가 포함되어야 하는데, 일반적인 JPA로는 불가능할 것 같다는 생각이 들었음
* 아이디어1: @Query 어노테이션을 이용해서 구현할까 생각함. 하지만 sql을 사용하지 않고, ORM을 이용해서 객체지향적인 개발을 하고자 하는데 위반할 것 같다는 생각이 듦
* 해결방법: QueryDSL을 이용해서 구현함. 보다 객체지향적인 코드를 작성할 수 있게 되어 만족스러웠음.

