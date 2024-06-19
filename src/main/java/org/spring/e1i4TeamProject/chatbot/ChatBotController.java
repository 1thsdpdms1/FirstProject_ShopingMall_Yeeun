package org.spring.e1i4TeamProject.chatbot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatBotController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public BotMessage greeting(ClientMessage message) throws Exception {
        Thread.sleep(50);
        LocalDateTime today=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDay=today.format(formatter);
        String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));

        return new BotMessage(
            "<div class='flex center date' >"+formattedDay+"</div>"+
                "<div class='msg bot flex'>"+
                "<div class='icon'>"+
                "<img src='/images/chat.png'  th:alt=\"#{chat}\" />" +
                "</div>"+
                "<div class='message'>"+
                "<div class='part'>"+
                "<p style='text-align:center'>안녕하세요, E1I4 쳇봇입니다. <br> 궁금한 점은 저에게 물어보세요!</p>"+
                "</div>" +
                "<div class='part'>"+
                "<p>자주하는 질문입니다 아래 내용을 클릭해 주세요.</p>"+
                "<div class='center'>"+
                "<div class='center-item'><span onclick='menuclickFn(event)'>기업소개</span></div>"+
                "<div class='center-item'><span onclick='menuclickFn(event)'>상품문의</span></div>"+
                "</div>"+
                "</div>"+
                "<div class='time'>"+
                formattedtime+
                "</div>"+
                "</div>"+
                "</div>");
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public BotMessage message(ClientMessage message) throws Exception {
        Thread.sleep(100); // 응답딜레이 시간
        LocalDateTime today=LocalDateTime.now();
        String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));
        // 요청 data를 전달만 ->
        String responseText=message.getContent()+" 대한 답장입니다.";
        String src="";
        String text="";
        if(responseText.contains("기업")){
            src="/images/logo.png"    ;
            text="E1I4는 원데이 클래스 강의를 판매하는 사이트입니다.<br>" +
                " mbti E 1명, I 4명이 모여 여러분의 취미를 찾아드리기 위해 설립했습니다 ";
        }else if(responseText.contains("상품")){
            text="[상단메뉴] - [shop] - [전체] <br> 클릭하시면 모든 상품을 확인할수있으며,<br>" +
                " 더 자세한 상품문의를 원하신다면 <br>선생님께 문의하시길 바랍니다";
        }
        return new BotMessage(
            "<div class='msg bot flex'>"+
                "<div class='icon'>"+
                "<img src='/images/chat.png'  th:alt=\"#{chat}\" />" +
                "</div>"+
                "<div class='message'>"+
                "<div class='part'>"+
                "<p>"+responseText+"</p>"+
                "<img src='"+src+"'  th:alt=\"#{src}\" />" +
                "<div class=text>"+text+"</div>"+
                "</div>"+
                "<div class='time'>"+
                formattedtime+
                "</div>"+
                "</div>"+
                "</div>");
    }

}
