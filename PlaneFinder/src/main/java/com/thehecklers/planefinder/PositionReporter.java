package com.thehecklers.planefinder;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

// 항공기 위치 정보를 게시하기(RabbitMQ) 위한 클래스
@AllArgsConstructor
@Configuration
public class PositionReporter {
    // 게시할 정보.. PlaneFinerService 로 부터 얻음.
    private final PlaneFinderService pfservice;

    // rabbitMQ에 게시할 내용!
    @Bean
    Supplier<Iterable<Aircraft>> reportPositions() {
        return () -> {
            try {
                return pfservice.getAircraft();
            }catch (IOException e){
                e.printStackTrace();
            }
            return List.of();   // null 값을 가진 List 객체
        };
    }

}
