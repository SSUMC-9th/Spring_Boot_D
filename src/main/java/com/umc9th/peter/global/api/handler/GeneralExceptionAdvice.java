package com.umc9th.peter.global.api.handler;

import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.code.GeneralErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;
import com.umc9th.peter.global.webhook.dto.TextMessage;
import com.umc9th.peter.global.webhook.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.text.MessageFormat.format;

@RestControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionAdvice {

    private final WebhookService webhookService;

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<String>> handleException(GeneralException e) {
        BaseErrorCode code = e.getCode();
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, code.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e, HttpServletRequest request) {
        String template = """
                ### Internal Server Error
                {0}: {1}
                {2}""";
        String message = format(template, request.getMethod(), request.getRequestURI(), e.getMessage());
        webhookService.sendWebhook(new TextMessage(message));

        GeneralErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

}
