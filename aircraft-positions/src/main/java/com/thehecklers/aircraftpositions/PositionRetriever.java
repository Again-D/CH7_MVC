package com.thehecklers.aircraftpositions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

// RabbitMQ에 있는 게시글을 소비할 클래스
@AllArgsConstructor
@Configuration
public class PositionRetriever {

    // 불러올 정보를 처리할 repository를 불러옴.
    private final AircraftRepository repo;

    // RabbitMQ에 있는 게시물을 읽어올 빈(Bean)을 등록
    @Bean
    Consumer<List<Aircraft>> retrieveAircraftPositions() {
        return acList -> {
          repo.deleteAll();             // H2 DB에 기존 저장되어 있는 정보 삭제
          repo.saveAll(acList);         // 새로운 데이터를 추가
//          System.out.println(repo.findAll().toString());
//          repo.findAll().forEach(System.out::println); // 콘솔에 출력....
          sendPositions();
        };
    }
    
    // WebSocket 통신을 위한 session 관리를 위한 핸들러 호출
    private final WebSocketHandler handler;

    // WebSocketHandler를 이용한 소켓 메시지 전송 처리 메서드 구현
    private void sendPositions() {
        if (repo.count() > 0) {
            for (WebSocketSession sessionInList : handler.getSessionList()) {
                try {
                    // WebSocketSession을 통한 메시지 전송
                    sessionInList.sendMessage(
                            new TextMessage(repo.findAll().toString())
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
}
