package com.example.UMC9th.domain.test.converter;

import com.example.UMC9th.domain.test.dto.response.TestResDTO;

public class TestConverter {

    //객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ){
        return TestResDTO.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResDTO.Exception toExceptionDTO(
            String testing
    ){
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
