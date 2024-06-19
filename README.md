
# 🛍ShopingMall_E1I4TeamProject🛍

<br>

원데이 클래스 강의를 사고 팔수있는 강의 판매 사이트 입니다  

선생님으로 가입시 강의를 등록 및 판매하고 
일반회원으로 가입시 강의를 구매하고 
관리자는 선생님 및 회원들을 관리하는 쇼핑몰 사이트!

사용자 경험 중심의 웹사이트와
지속적인 참여 및 관리를 목적으로 제작했습니다

<br>


## 📌 목차 

- [프로젝트 기본설정](#-시연-영상)
- [팀원 소개](#-팀원-소개)


<br>

## 🔎프로젝트 소개

<details>
<summary>프로젝트 기본설정</summary>
<li> 2024/04/11~2024/05/10  </li>
<li> 원데이 클래스 강의판매 사이트 </li>
<li> 팀장 1명, 팀원 4명 (총 5인) _팀원으로 참여 </li>
<li> 프로젝트명 : E1i4TeamProject </li>
<li> 프로그래밍 언어 : JAVA </li>
<li> 프레임워크 : Springboot 2.7.11 </li>
<li> 데이터베이스 : MySql8 </li>
<li> 개발툴 : IntelliJ </li>
<li> 템플릿 엔진 : Thymeleaf (HTML + css) </li>

</details>

<details>
<summary> 팀원소개</summary>

- 👱‍박**(팀장) : DB설계, 회원CRUD(개인정보), OAuth2, Security, 
- 👱‍이**(팀원) : 상품목록, 상품상세, 장바구니(시간표), 구매, 구매리스트 
- 👱‍심**(팀원) : 게시판 CRUD(커뮤니티, 공지사항, 수강후기), exception 
- 👱‍조**(팀원) : INDEX 페이지 CSS ,1:1 문의내역, 덧글
- 👩‍🦰손예은(팀원) : 관리자페이지, Chatbot, 강사소개 페이지, INDEX 애니메이션 기능

</details>

<details>
<summary> 타임라인</summary>
  
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/1f2aa490-1614-40bc-881b-c4dfe19611bc)

</details>

<details>
<summary> DB설계 </summary>
  
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/1f2aa490-1614-40bc-881b-c4dfe19611bc)

</details>
<br>

## ⭐프로젝트 시안
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/781bfb06-6b04-43fc-b049-08595f52e7a7)

### ✔ 관리자 페이지 구현 ✔
<details>
<summary>　관리자 페이지 구현 시연 영상</summary>

![관리자 페이지 시안영상](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/e603ecf6-fcf9-4c7c-905c-581025a0a3d1)
  
</details>

<details>
<summary>관리자 페이지 구현 시안 </summary>
  ![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/bfe05a9b-06fb-4f4e-aa0a-5131b68de1db)

첫번쨰로 Spring Security의 타임리프 태그 라이브러리를 사용하여
권한이 admin일때만 관리자페이지에 접속할수있도록 하였습니다 
두번쨰로는 pageable ,subject, search를 매개변수로 받아와서 subject가 null이 아닌경우 subject에 따라 
이름, 전화번호, 이메일로 검색이 가능하게 만들었으며
회원조회와 선생님조회를 구분하기위해서는 권한과 subject 조건 두개를 and연산자를 이용하여
 findByRoleAndNameContains 으로 회원이 member이면서 이름에 대해 검색가능하게 구현하였습니다
또한 테이블에 너무 긴 글이 들어간 경우엔 말줄임 기능을 넣어보았습니다
<br>
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/d0294769-b666-4aa5-a164-48422e7fca12)

상품페이지는 param으로 subject1 subject2 seach pageable를 매겨변수를 불러와
미술/체육/음악/요리 등 카테고리별로 선택후 수강명/수강내용을 검색할수있게 select를 두개 구성하였으며
검색하고 페이지네이션된 결과를 반환하도록 하였습니다
<br>
![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/b1c0eaa2-8553-4131-aa76-37b7adc45270)

삭제는 다중선택삭제와 일반 삭제 가능하도록 했는데
다중선택삭제는 javascript에 selectedIds"라는 이름을 가진 input 요소 중에 체크된 것들을 모두 선택하고
가장 가까운 tr요소를 찾아 삭제하는 명령을 foreach함수를 써 반복하게 하여 선택삭제를 구현했으며
회원을 삭제하기 전에 사용자에게 확인 메시지를 표시하고, if else문으로 사용자의 응답에 따라 삭제를 진행하게 했습니다
</details>

### ✔ Chatbot 구현 ✔
<details>
<summary>Chatbot 구현 시연 영상</summary>
![chatBot](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/97b1952e-e28d-4b31-9215-7b600e8121bd)

</details>
<details>
<summary>Chatbot 구현 시안 설명</summary>


</details>

### ✔ 강사소개 구현 ✔
<details>
<summary>강사 소개 페이지 시연 영상</summary>

![강사 소개](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/82994cec-83e7-49eb-9329-b4cc5a03b7d4)


</details>
<details>
<summary>강사 소개 페이지 시안 설명</summary>

</details>

### ✔ INDEX 애니메이션 기능 구현 ✔
<details>
<summary>INDEX 애니메이션 기능 시연 영상</summary>

https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ceed1a15-8386-4b9f-a65f-ee33ccff51f6

</details>
<details>
&nbsp;<summary>INDEX 애니메이션 기능 시안 설명</summary>

</details>



