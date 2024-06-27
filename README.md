
# 🛍ShopingMall_E1I4TeamProject🛍

<br>
원데이 클래스 강의를 사고 팔수있는 <span style="background-color: #F7BE81; color:black">강의판매사이트</span>입니다.


선생님으로 가입시 강의를 등록 및 판매하고 <br>
일반회원으로 가입시 강의를 구매하고<br>
관리자는 선생님 및 회원들을 관리하는 쇼핑몰 사이트!<br>

사용자 경험 중심의 웹사이트와 <br>
지속적인 참여 및 관리를 목적으로 제작했습니다

<br>


## 📌 목차

* [🔎프로젝트 소개](#프로젝트-소개)
  + [✔ 프로젝트 기본설정 ✔](#프로젝트-기본설정)
  + [✔ 기술스택 ✔](#프로젝트-기본설정)
  + [✔ 팀원소개 ✔](#Chatbot-구현)
  + [✔ 팀원소개 ✔](#팀원소개)
  + [✔ DB설계 ✔](#DB설계)
* [⭐프로젝트 시안](#프로젝트-시안)
    + [✔ 관리자 페이지 구현 ✔](#-관리자-페이지-구현-)
    + [✔ Chatbot 구현 ✔](#-chatbot-구현-)
    + [✔ 강사소개 구현 ✔](#-강사소개-구현-)
    + [✔ INDEX 애니메이션 기능 구현 ✔](#-index-애니메이션-기능-구현-)


<br>

## 🔎프로젝트 소개

<details>
<summary>프로젝트 기본설정</summary>

|제목|내용|
|------|---|
|일정|2024/04/11~2024/05/10|
|주제|원데이 클래스 강의판매 사이트|
|프로젝트명| E1i4 TeamProject|
|프로그래밍 언어|JAVA|
|프레임워크|Springboot 2.7.11|
|데이터베이스|MySql8|
|개발툴| IntelliJ|
|템플릿 엔진|Thymeleaf (HTML + css)|

</details>

<details>
<summary> 팀원소개</summary>

<table>
  <tbody>
    <tr>
      <th align="center"><a href=""><img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/a92bfe84-63f4-45b1-800c-4fc5c8512513" width="100px;" alt=""/><br /><sub><b>FE 팀장 : 박**</b></sub></a><br /></th>
      <th align="center"><a href=""><img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/e4f3a019-1688-4879-bf23-9e791e3b56a5" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 손예은</b></sub></a><br /></th>
      <th align="center"><a href=""><img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ea52beb1-8420-4f6b-9028-ff0f247dc895" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 이** </b></sub></a><br /></th>
      <th align="center"><a href=""><img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/becb61fa-7a36-43fc-a00c-aa20be5ec767" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 심 **</b></sub></a><br /></th>
      <th align="center"><a href=""><img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/c690bc9c-0d05-4067-a3d6-5ece66b61620" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 조** </b></sub></a><br /></th>
</tr>
<tr>

<td>DB설계, 회원CRUD(개인정보), <br>OAuth2, Security </td>
<td> 관리자페이지<br>, Chatbot, <br>강사소개 페이지, <br>INDEX 애니메이션 기능 </td>
<td> 상품목록, 상품상세,<br> 장바구니(시간표), 구매, <br>구매리스트 </td>
<td> 게시판 CRUD,<br> exception </td>
<td> INDEX 페이지 CSS ,<br>1:1 문의내역, 덧글</td>
</tr>
  </tbody>
</table>



</details>

<details>
<summary> 타임라인</summary>

![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/1f2aa490-1614-40bc-881b-c4dfe19611bc)

</details>

<details>
<summary> DB설계 </summary>

![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/f994dcb6-2c21-4ad8-a9f2-354c5b148f88)

</details>
<br>

## ⭐프로젝트 시안
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/781bfb06-6b04-43fc-b049-08595f52e7a7)
<br>

### ✔ 관리자 페이지 구현 ✔
<details>
<summary>관리자 페이지 구현 시연 영상</summary>


![관리자 페이지 시안영상](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/e603ecf6-fcf9-4c7c-905c-581025a0a3d1)

</details>

<details>
<summary>관리자 페이지 구현 시안 </summary>
  <img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/7abbca76-164e-4e32-a147-e775a2a6486c"  width="700" height="400"/>

- Spring Security의 타임리프 태그 라이브러리를 사용하여 권한이 admin일때만 관리자페이지에 접속 가능
- pageable ,subject, search를 매개변수로 받아와서 subject가 null이 아닌경우 subject에 따라 이름, 전화번호, 이메일로 검색이 가능
- 회원조회와 선생님조회를 구분하기위해서는 권한과 subject 조건 두개를 and연산자를 이용하여 findByRoleAndNameContains 으로 회원이 member이면서 이름에 대해 검색가능하게 구현
- 테이블에 너무 긴 글이 들어간 경우엔 말줄임 기능
  <br>

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/d0294769-b666-4aa5-a164-48422e7fca12"  width="700" height="400"/>

- 상품페이지는 param으로 subject1 subject2 seach pageable를 매겨변수를 불러와 미술/체육/음악/요리 등 카테고리별로 선택후 수강명/수강내용을 검색할수있게 select를 두개 구성
- 검색하고 페이지네이션된 결과를 반환
  <br>

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/b1c0eaa2-8553-4131-aa76-37b7adc45270"  width="700" height="400"/>

- 삭제는 다중선택삭제와 일반 삭제 가능
- 다중선택삭제는 javascript에 selectedIds"라는 이름을 가진 input 요소 중에 체크된 것들을 모두 선택하고
  가장 가까운 tr요소를 찾아 삭제하는 명령을 foreach함수를 써 반복하게 하여 선택삭제를 구현
- 회원을 삭제하기 전에 사용자에게 확인 메시지를 표시하고, if else문으로 사용자의 응답에 따라 삭제를 진행
</details>
<br>
<br>

### ✔ Chatbot 구현 ✔
<details>
<summary>Chatbot 구현 시연 영상</summary>

![chatBot](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/97b1952e-e28d-4b31-9215-7b600e8121bd)

</details>
<details>
<summary>Chatbot 구현 시안 설명</summary>

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/f965e795-2b38-46f2-a864-cacde6a7cd71" width="700" height="400"/>

- websocket은 기존의 단방향 HTTP프로토콜과 호환되어 양방향 통신을 제공하기 위해 개발된 프로토콜
- websocket 라이브러리를 주입하여 사용
- configureMessageBroker() 메서드는 메시지 브로커를 설정하고 /app2가 붙으면 서버로 전송, /topic이 붙으면 클라이언트에게 메세지 보내도록 활성화
- registerStompEndpoints() 메서드로 클라이언트와 서버간의 웹소켓 연결을 활성화

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ed233c53-4675-4464-9a30-bb7de21d5c1f"  width="700" height="400"/>

- @MessageMapping() 주소로 메세지가 오면 해당 매서드가 구현되며 @Sendto() 주소로 클라이언트에게 전송
- 처음 소켓연결시 연결이 성공하면  /app2/hello주소로 메세지를 보내 hello메서드를 실행시키도록 하여 기업소개, 상품소개를 선택할수있게 했으며 이는 topic/greetings주소로 클라이언트에게 전송
-
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/8966f99d-6527-4f5e-839a-5ed18900a1a6"  width="700" height="400"/>

- 기업소개 또는 상품소개 버튼을 클릭시 /app2/message주소로 메세지를 보내 message매서드를 실행시켜 그에대한 응답내용이 나오도록 함

</details>
<br>
<br>

### ✔ 강사소개 구현 ✔
<details>
<summary>강사 소개 페이지 시연 영상</summary>

![강사 소개](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/82994cec-83e7-49eb-9329-b4cc5a03b7d4)

</details>
<details>
<summary>강사 소개 페이지 시안 설명</summary>

  <img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/2ee0c3d2-998b-4d8a-af70-9abe71c56efb"  width="700" height="400"/>

- 강사 페이지는 모든 사용자가 선생님의 프로필을 볼수있도록 한 페이지
- JPQL을 사용하여 role이 seller인 모든회원을 검색하고 stream으로 엔티티를 dto로 변한후 리스트로 반환-
- 이 list를 html에 그리드를 사용하여 나타냈고 hover시 transform: scale를 사용해 강조효과
-  memberAttachFile==1일때와 아닐때를 구분하여 img가 없으면 기본이미지 나오도록 구현

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ebb030a2-9ec1-4a21-b43c-f6ded3bfae18"  width="700" height="400"/>

- 그리드를 선택하면 더 자세한 프로필을 볼수있는 디테일페이지가 나오고  프로필 하단에는 선생님이 등록한 상품인 강좌 list가 있으며
  바로가기로 상품페이지로 이동할수있게 구성
- 코드는 findbyid를 사용하여 id로 찾고 없으면 판매자가 존재하지 않는다는 예외를 뒀으며
- id를 찾았다면 찾은 엔티티를 dto로 반환
- admin권한이라면 목록으로 돌아가기버튼과 회원탈퇴버튼 두개가 보여 여기서도 삭제 진행이 가능

</details>
<br>
<br>

### ✔ INDEX 애니메이션 기능 구현 ✔
<details>
<summary>INDEX 애니메이션 기능 시연 영상</summary>

https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ceed1a15-8386-4b9f-a65f-ee33ccff51f6

</details>
<details>
&nbsp;<summary>INDEX 애니메이션 기능 시안 설명</summary>

  <img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/baff420b-f0c3-40e5-9a07-995d86c9e8d0"  width="700" height="400"/>

- for문을 통해 각이미지를 1000번 나열되게 하고 나열된 이미지에 leftMoveLoop라는 이름의 키프레임 애니메이션을 translateX()에 0을 넣어 위치를 고정시킴
- 이후 -100%를 넣어  가로길이의 100%만큼 왼쪽으로 이동하게 정의한후 1500초 동안 계속해서 재생되며 수평으로 움직이게 설정
</details>

