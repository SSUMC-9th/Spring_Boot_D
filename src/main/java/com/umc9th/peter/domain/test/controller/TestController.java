package com.umc9th.peter.domain.test.controller;

import com.umc9th.peter.domain.test.dto.TestResponse;
import com.umc9th.peter.domain.test.service.TestService;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public ApiResponse<TestResponse.Testing> test() {
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, new TestResponse.Testing("This is Test!"));
    }

    @GetMapping("/exception")
    public ApiResponse<TestResponse.Exception> throwException(
            @RequestParam Long flag
    ) {
        testService.checkFlag(flag);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, new TestResponse.Exception("This is Exception Test!"));
    }

    @GetMapping("/notification")
    public ApiResponse<TestResponse.Exception> exceptionNotification() throws Exception {
        throw new Exception("Exception notification test!");
    }

}
