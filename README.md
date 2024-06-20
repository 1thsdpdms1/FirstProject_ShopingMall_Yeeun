
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

* [🔎프로젝트 소개](#프로젝트-소개)
    + [✔ 프로젝트 기본설정 ✔](#프로젝트-기본설정)
    + [✔ Chatbot 구현 ✔](#Chatbot-구현)
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

--------------------------------------------------

# ✈AdminPage_GroupAirTeamProject✈

<br>
항공사 관리자 페이지로
이해하기 쉬운 UI와 부서 간의 의사소통이 원할하도록한 커뮤니티
또한 권한에 따라 관여할수있는 부분 제한하였습니다

사원, 부장, 대표이사 권한으로 나뉘어져있으며
부서관리, 결제관리, 일정관리, 근태관리, 급여관리, 사원관리 등등 관리프로그램

영화API, 날씨 API, 버스API를 사용하여 사용자의 편의성을 높여보았습니다

<br>


## 📌 목차

- [프로젝트 기본설정](#-시연-영상)
# Paste Your Document In Here

## And a table of contents

will be generated

## On the right

side of this page.


- [팀원 소개](#-팀원-소개)


<br>

## ⚙프로젝트 소개

<details>
<summary>프로젝트 기본설정</summary>
<li> 2024/05/11~2024/06/07 </li>
<li> 항공 관리자 페이지 </li>
<li> 팀장 1명, 팀원 4명 (총 5인) _팀장으로 참여 </li>
<li> 프로젝트명 : GroupAir TeamProject </li>
<li> 프로그래밍 언어 : JAVA </li>
<li> 프레임워크 : Springboot 2.7.11 </li>
<li> 데이터베이스 : MySql8 </li>
<li> 개발툴 : IntelliJ </li>
<li> 템플릿 엔진 : Thymeleaf (HTML + css) </li>
</details>

<details>
<summary> 기술스택 </summary>
<img src="https://img.shields.io/badge/javaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot**&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/amazonwebservices-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white">
<img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
<img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
<img src="https://img.shields.io/badge/amazonecs-FF9900?style=for-the-badge&logo=amazonecs&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">



</details>

<details>
<summary> 팀원소개</summary>

- 👩‍🦰손예은(팀장) : CI/CD, DB설계, 로그인, naver(oauth, Mail, 조직연동), 사원관리
- 👱‍서**(팀원) : 회사일정(캘린더), 게시판 관리(파일 덧글 공지사항)
- 👱‍박**(팀원) : db설계, 근태관리, 급여관리, 항공편관리(map)
- 👱‍조**(팀원) : chatbot, 부서관리(부서등록)
- 👱‍정**(팀원) : chatbot, 부서관리(부서등록)


</details>


<details>
<summary> 타임라인</summary>

![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/c491040a-4bc0-43e9-9675-2a06484d6661)

</details>

<details>
<summary> DB설계 </summary>

![image](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/ea0ccc32-1659-4d5e-845d-26a46bcbff69)


</details>
<br>


<br>
## ⭐프로젝트 시안

![0index](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/db119b68-1233-4c54-aada-19e26998e761)


### ✔ 1. 로그인 페이지 구현 ✔
<details>
<summary>로그인 페이지 구현 시연 영상</summary>

![로그인](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/3dd99fdb-f2d5-4347-b780-07eb49913627)


</details>

<details>
<summary>로그인 페이지 구현 시안 </summary>

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/be6cd692-0bf4-4d4f-a77c-b4a98c5c544b" width="700" height="400"/>

- admin이 등록한 사원만 로그인이 가능하며 컨셉에 맞는 로고와 색상을 사용해 디자인
- 로그인 실패 시에는 예외처리를 위한 스프링 시큐리티의 커스텀 인증실패 핸들러를 사용
- 발생한 예외에 따라 적절한 메세지를 설정
- 로그인 실패시 로그인창 하단에 예외문구를 띄워 처리

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/5c1c70f6-6ba7-437d-9e1d-406e29c03b2f" width="700" height="400"/>

- 로그인시 자신의 정보가 잘 기억이 나지 않는다면 아이디 찾기와 비밀번호 찾기 가능
- 아이디 찾기는 자신의 이름과 전화번호를 입력하게 하고 그 정보를 가지고 findUserEmailByNameAndPhone을 사용하여 이메일(즉 아이디)를 찾아
alert창에 나오도록 함

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/31f6aeb1-ab88-4cbd-9a7a-e1cccb5f8fe5" width="700" height="400"/>

- 비밀번호 찾기는 이메일과 이름을 입력하게 하여 그 정보를 가지고 findUserPwByUserEmailAndName을 사용하여 그 회원을 찾고 새비밀번호로 변경하도록
- 이 과정이 끝나고 로그인시도를 할수있도록 로그인페이지로 이동하게 설정

</details>

### ✔ 2. 회원등록 페이지 구현 ✔
<details>
<summary>회원등록 구현 시연 영상</summary>

![회원등록](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/d0fb066b-f951-44fc-ae44-57fa2ac0b4f7)

</details>

<details>
<summary>회원등록 구현 시안 </summary>

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/0ab7a1f7-413d-4223-9bba-540187f8b6a3" width="700" height="400"/>

- admin권한으로 로그인시 회원 등록메뉴가 추가로 보여지며 회원을 등록시킬수있습니다
- 페이지의 기능들을 소개하자면 이메일 유효성 검사 및 초기 비밀번호 설정,부서연동 ,카카오 주소 api사용, 전화번호 자동 하이픈 기능이 있습니다

<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/a8d28892-f1bd-4ec6-b885-14106e56702d" width="700" height="400"/>

- 이메일을 입력하고 버튼을 누르면초기 비밀번호로 사용될 6자리의숫자를 랜덤함수를 이용해 만들고 입력받은 메일주소를 매개변수로한 매서드가 실행
- MimeMessageHelper는 이메일의 속성을 설정할수있는데 이때 수신자를 입력받은 메일주소로 설정, 내용에는 초기 비밀번호에 사용할 랜덤한 숫자를 포함한 메세지를 작성
- 그외 발신자와 이메일의 제목을 설정
- 발신이 완료되면 alert창과 함께 비밀번호 input창에 초기 비밀번호 값이 입력
  <img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/952718cf-2e9c-468d-844c-c748e7652d92" width="700" height="400"/>

- 주소입력시 카카오api를 사용하여 통일된 주소값을 가지도록 구현
- 우편번호찾기버튼을 클릭시 카카오 주소검색창이 뜨고 우편번호와 주소값이 input창에 입력
- 상세주소는 직접 입력할수있으며 위 주소와 상세주소를 합친 주소를 만들어 합친주소를 db에 저장

</details>

### ✔ 3. 회원관리 페이지 구현 ✔
<details>
<summary>회원등록 구현 시연 영상</summary>

![회원디테일](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/6e3c2d0d-9cd8-496f-9383-d3ee2a981f7c)

</details>

<details>
<summary>회원등록 구현 시안 </summary>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/65a17770-addd-41a4-9d60-75ac817e5bf6" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/3010afbe-2b53-43e1-9d3a-d8c3ebdf8f12" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/b883b030-df45-4a52-bd27-68d95083c450" width="700" height="400"/>
</details>


### ✔ 4. NAVER API 연동 구현 ✔
<details>
<summary> NAVER API 연동 시연 영상</summary>

![네이버](https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/96a948d0-07f1-4625-8088-f524f15b0065)

</details>

<details>
<summary> NAVER API 연동 시안 </summary>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/be052358-a031-490a-8b61-10930ba24942" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/2e1e1442-558d-4335-b5c9-a3922b37945c" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/548c880f-211b-4698-bda5-41e0c9904ed5" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/849ec12c-b34c-4f6d-8206-40a5813fdcc2" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/cdb9263e-9560-4150-9576-8fabba21b7bb" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/37645692-79d7-4979-83bf-99dc63a215fe" width="700" height="400"/>
<img src="https://github.com/1thsdpdms1/FirstProject_ShopingMall_Yeeun/assets/154856610/98eb68f9-994b-4c00-8cc0-13f391ffe24e" width="700" height="400"/>
</details>

