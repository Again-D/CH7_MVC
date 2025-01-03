package com.thehecklers.aircraftpositions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

// 작성하는 WebSocketHandler 클래스는 상속받은 TextWebSocketHandler 클래스의 부모 AbstractWebSocketHandler 클래스의 구현체
// WebSocketHandler 인터페이스의 관계를 가진 클래스로 구현..

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    // web socket 세션 저장 리스트 생성 - 소켓연결 정보 저장한 객체 리스트
    private final List<WebSocketSession> sessionList = new ArrayList<>();

    // 저장소 불러오기
    @NonNull
    private final AircraftRepository repository;

    // websocket session리스트 정보를 전달하기 위한 Getter
    public List<WebSocketSession> getSessionList(){
        return sessionList;
    }

    // 연결 후 작업
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    // 연결된 이후 데이터(메시지) 작업 - 메시지 처리 작업을 진행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    // 연결 종료 후 작업
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
