let stompClient = null;
//id="btn-chat-open" 클릭하면
function openChat(){
    setConnectStated(true);// 잡속 버튼 클릭시
    connect(); // 연결 1
}
// 연결 해제
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();// 종료
    }
    setConnectStated(false);
    console.log("Disconnected");
}

// 잡속 버튼 클릭시
function setConnectStated(isTrue){
    if(isTrue){
    // ture일 경우
        $("#btn-chat-open").hide(); // 보이지 않게
        $("#chat-disp").show();    // 보이게
    }else{
    // 연결해제시
        $("#btn-chat-open").show(); // 보이게
        $("#chat-disp").hide();// 보이지 않게
    }
    $("#chat-content").html(""); // 초기화
}
//버튼클릭시 접속
function connect() {
    // 웹소켓 연결
    let  socket = new SockJS('/chatEndpoint');
    // Stomp 연결
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        //1. 브라우저에서 메시지를 수신하려면 STOMP 클라이언트가 먼저 대상을 구독
        // @MessageMapping("/hello") -> 처음 연결 시 -> 구독
        stompClient.send("/app2/hello", {},JSON.stringify({'content': 'guest'}));
        //2. subscribe()방법을 사용하여 대상에 가입
        //2개의 필수 인수를 사용. destination목적지에 해당하는 문자열, callback,
        stompClient.subscribe('/topic/greetings', function (botMessage) {
           // 구독시 -> , 처음 실행
            showMessage(JSON.parse(botMessage.body).message);
        });
       // /app2/message 전송에 대한 서버 응답
        stompClient.subscribe('/topic/message', function (botMessage) {
            showMessage(JSON.parse(botMessage.body).message);
        });

    });
}

// $("#chat-content")에 추가 -> 처음 +1+2,,,,
function showMessage(message) {
    $("#chat-content").append(message);
    //대화창 스크롤을 항상 최하위에 배치
    $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

function inputTagString(text){
    let  now=new Date();
    let  ampm=(now.getHours()>11)?"오후":"오전";
    let  time= ampm + now.getHours()%12+":"+now.getMinutes();
    let  message=`
		<div class="msg user flex end" style="justify-content: right">
			<div class="message">
				<div class="part"  style="text-align: right">
					<p style="margin: 0">${text}</p>
				</div>
				<div class="time" style="text-align: right">${time}</div>
			</div>
		</div>
	`;
    return message; // 시간+메시지
}

// <div class='menu-item'><span onclick='menuclickFn(event)'>상품문의</span></div>
//메뉴클릭시 메뉴 텍스트 화면에 표시 ->  메시지 전송
function menuclickFn(event){
    let  text=event.target.innerText.trim();
    let  message=inputTagString(text);
    showMessage(message);
    //  @MessageMapping("/message")
    stompClient.send("/app2/message", {}, JSON.stringify({'content': text}));
}
//전송버튼 클릭이되면 질문을 텍스트 화면에 표현
function msgSendClickFn(){
    let  question=$("#question").val().trim();
    if(question=="" || question.length<2) return;
    let  message=inputTagString(question);
    showMessage(message);
    $("#question").val("");
      //  @MessageMapping("/message")
    stompClient.send("/app2/message", {}, JSON.stringify({'content': question}));
}


//$(function(){
//    $("#question").keyup(qKeyupFn); // window load시 처음 실행
//});
//엔터가 입력이되면 질문을 텍스트 화면에 표현
//function qKeyupFn(event){
//    if(event.keyCode!=13) return; // enter키 제외하고
//    msgSendClickFn() // 실행
//}
