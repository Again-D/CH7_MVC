package com.thehecklers.aircraftpositions;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// WebSocket 연결 수락을 위한 설정
@Configuration      // 설정으로 Bean 등록
@EnableWebSocket    // WebSocket 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    // 작업한 WebSocketHandler 클래스 호출
    private final WebSocketHandler handler;

    // 생성자로 의존성 주입
    public WebSocketConfig(WebSocketHandler handler) {this.handler = handler;}

    // WebSocketHandler 등록 처리..
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws");  // setAllowedOrigins("*")은 기본 값이므로 생략 가능
        // WebSocketHandler(작업한 것) 빈은 "ws://<호스트명:호스트포트번호>/ws" 로 연결됨
    }
}
