package com.umc9th.peter.global.webhook.dto;

public record TextMessage(
        String text
) implements WebhookMessage {
}
