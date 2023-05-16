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
* DTO에서 Entity로 변경할 때, Setter 없이 Entity의 값을 변경해야 하는 부분에서 애먹었으나, 빌더패턴을 통해 해결함. Lombok의 @builder 어노테이션을 이용해서 빌더 패턴을 쉽게 구현할 수 있었음.
* Entity List를 DTO리스트로 변환하는 방법을 고민했음. DTO List를 Entity List로 변환하는 메소드를 작성해서 해결했음.
* 초반에는 주말에만 개발을 진행해서, 혼자서 개발하다보니 개발 의욕이 점점 사라지고 몇 개월동안 개발을 중단하게 됨. 마음을 다잡고 다시 개발을 진행하였고, 1일 1커밋을 해서라도 개발을 완료하기로 함.
* HTML data-uri로 속성을 주고, 자바스크립트에서 this.dataset.uri로 접근했으나, uri 속성을 인식하지 못했음. this를 사용하지 않고, element로 접근하니 해결됨.