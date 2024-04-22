package org.spring.e1i4TeamProject.chatbot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatBotController {

    @MessageMapping("/hello") // /app2/hello
    @SendTo("/topic/greetings") //stompClient.subscribe
    public BotMessage greeting(ClientMessage message) throws Exception {
        Thread.sleep(50);
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDay = today.format(formatter);
        String formattedtime = today.format(DateTimeFormatter.ofPattern("a H:mm"));

        // 처음 실행 되는 -> 답장문
        return new BotMessage(
            "<div class='flex center date' >" + formattedDay + "</div>" +
                "<div class='msg bot flex'>" +
                "<div class='icon'>" +
                "<img src='/images/chat.png'  th:alt=\"#{chat}\" />" +
                "</div>" +
                "<div class='message'>" +
                "<div class='part'>" +
                "<p style='text-align:center'>안녕하세요, 쳇봇입니다. <br> 궁금한 점은 저에게 물어보세요!</p>" +
                "</div>" +
                "<div class='part'>" +
                "<p>아래는 자주하는 질문 내용을 클릭해 주세요.</p>" +
                "<div class='flex center menu'>" +
                "<div class='menu-item'><span onclick='menuclickFn(event)'>기업소개</span></div>" +
                "<div class='menu-item'><span onclick='menuclickFn(event)'>상품문의</span></div>" +
                "</div>" +
                "</div>" +
                "<div class='time'>" +
                formattedtime +
                "</div>" +
                "</div>" +
                "</div>");
    }

    @MessageMapping("/message") // /app2/message
    @SendTo("/topic/message")//stompClient.subscribe
    public BotMessage message(ClientMessage message) throws Exception {

        Thread.sleep(100); // 응답딜레이 시간
        LocalDateTime today = LocalDateTime.now();
        String formattedtime = today.format(DateTimeFormatter.ofPattern("a H:mm"));
        // 요청 data를 전달만 ->
        String responseText = message.getContent() + " 대한 답장입니다.";
        String text = "";

        if (responseText.contains("기업소개")) {
            text = "기업소개 입니다";
        } else if (responseText.contains("상품문의")) {
            text = "어떤 상품이 궁금하시나요?";

        }

        return new BotMessage(
            "<div class='msg bot flex'>" +
                "<div class='icon'>" +
                "<img src='/images/chat.png'  th:alt=\"#{chat}\" />" +
                "</div>" +
                "<div class='message'>" +
                "<div class='part'>" +
                "<p>" + responseText + "</p>" +
                "<div class=text>" + text +
                "</div>" +
                "<div class='time'>" +
                formattedtime +
                "</div>" +
                "</div>" +
                "</div>");
    }

    ///////////////////////////////////////////////////////////////////////////////////
    }
