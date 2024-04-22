package org.spring.e1i4TeamProject.chatbot;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker //메시지 브로커가 지원하는 WebSocket 메시지 처리를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // 메모리 기반 메시지 브로커가
    // 접두사가 붙은 목적지에서 클라이언트에게 인사말 메시지를 다시 전달할 수 있도록 호출하는 것
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //서버에서 클라이언트로 메세지 보낼 때
        config.enableSimpleBroker("/topic");
        //클라이언트가 메세지를 서버쪽으로 전송할 때
        //  @MessageMapping("/hello")  <- stompClient.send("/app2/hello")
        config.setApplicationDestinationPrefixes("/app2");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 소켓 연결
        //  let  socket = new SockJS('/chatEndpoint');
        registry.addEndpoint("/chatEndpoint").withSockJS();
    }


}