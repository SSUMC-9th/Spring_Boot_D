package com.example.UMC9th.domain.test.dto.response;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    //우리가 만드는 인스턴스들은 모두 빌더 패턴을 사용한다고 생각하면 됨
    @Getter
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
    //DTO가 수많은 곳에서 사용 될 수 있기에 static class로 만들어 class를 매번 만들지 않고 범용적으로 사용할 수 있게 함
}
