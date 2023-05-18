# SpringBootBasic Project
## 목적
###  Spring Boot를 이용해서 간단한 API 서버와 게시판을 개발 
* 이 프로젝트를 통해서 스프링 MVC 모델, JPA, JUnit을 이용한 테스트 코드 작성에 대한 이해 확립
* 컨트롤러를 이용해 URL과 매핑되는 메소드 관리
* JPA를 이용해 DB 제어

## 기획
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
* [ ] 검색 기능
* [ ] 추천 기능
* [ ] 앵커 기능
* [ ] 마크다운 기능

## 개발 환경
- IDE: IntelliJ IDEA Ultimate
- OS: Windows11
- Java 11
- Spring Boot 2.7.4
- H2 데이터베이스
- Gradle

## 개발 과정에서 어려웠던 점
* 초반에는 주말에만 개발을 진행해서, 혼자서 개발하다보니 개발 의욕이 점점 사라지고 몇 개월동안 개발을 중단하게 됨. 마음을 다잡고 최대한 1일 1커밋을 지키는 것을 목표로 함.
* HTML data-uri로 속성을 주고, 자바스크립트에서 this.dataset.uri로 접근했으나, uri 속성을 인식하지 못했음
* * 해결방안: this를 사용하지 않고, element로 접근하니 해결됨
* Entity를 DTO로 변환
* * 문제점: Entity에는 Setter가 없는데, DTO로부터 값을 받아와서 Entity에 집어 넣어야 함.
* * 해결방안:빌더패턴을 통해 해결함. Lombok의 @builder 어노테이션을 이용해서 빌더 패턴을 쉽게 구현할 수 있었음.
* * 문제점: 위의 해결방안을 통해, Entity를 DTO로 변환하는데 성공했다고 생각했음. 하지만 QuestionEntity와 QuestionDTO간 무한 순환 참조하는 버그에 걸림
* * 아이디어1: ModelMapper, MapStruct 등 라이브러리를 통해 해결해보고자 했지만, 실패했음.
* * 아이디어2: 서비스 레이어에서 toDTO와 toEntity를 구현하고, 여러 메서드를 구현해 해결하고자 했지만, 실패했음.
* * 아이디어3: 생각해보니 DTO에서는 List<AnswerDTO>를 받을 필요가 없다는 생각이 들어서, DTO 안에 내부 static class를 만들어 해결하고자 함.  
* * 해결방안: 아이디어 3을 이용해 서비스단에서 Entity와 DTO간 변환하는 로직을 구현해 해결에 성공했음.