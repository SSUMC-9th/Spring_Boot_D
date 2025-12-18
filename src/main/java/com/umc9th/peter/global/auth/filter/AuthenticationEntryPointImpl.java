package com.umc9th.peter.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED,
                null
        );
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
