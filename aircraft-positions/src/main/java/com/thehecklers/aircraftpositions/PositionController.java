package com.thehecklers.aircraftpositions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
public class PositionController {

    // Model 영역 호출...
    @NonNull   // 필수 파라미터 처리
    private final AircraftRepository repository;
    // Reactive Web 기능을 이용한 http://localhost:7634/aircraft 연결 클라이언트 객체 생성
//    private WebClient client = WebClient.create("http://localhost:7634/aircraft");

    // 매핑
    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        // @Controller 에서 매핑 메서드의 반환 타입은 View를 가리킴
        //  String -> view의 이름을 반환값으로 지정
        //  void -> uri값을 사용.

        // Model(com.springframework.ui)
        //  view로 데이터를 전달하는 인터페이스
        //   - addAttribute("전달키워드", 전달 Data)

//        repository.deleteAll(); // 기존 데이터 삭제

//        // planefinder API를 통한 정보 수집
//        client.get()
//                .retrieve()
//                .bodyToFlux(Aircraft.class)
//                .filter(plane -> !plane.getReg().isEmpty())
//                .toStream()
//                .forEach(repository::save);
//
        //model을 통한 view(템플릿)로 데이터 전달.
        model.addAttribute("currentPositions",repository.findAll());
        // 전달(이동)할 템플릿
        return "positions";
    }

}
