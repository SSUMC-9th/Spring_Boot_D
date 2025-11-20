package com.umc9th.peter.global.webhook.service;

import com.umc9th.peter.global.webhook.dto.WebhookMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class WebhookService {

    private final WebClient client;

    private final boolean enabled;

    public WebhookService(
            WebClient.Builder builder,
            @Value("${webhook.url}") String webhookUrl
    ) {
        enabled = !webhookUrl.isEmpty();
        this.client = builder.baseUrl(webhookUrl).build();
    }

    public void sendWebhook(WebhookMessage content) {
        if (enabled) {
            client.post()
                    .uri("")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(content)
                    .retrieve()
                    .toBodilessEntity()
                    .subscribe(
                            null,
                            error -> log.warn("Webhook 전송 실패, {}", error.getMessage()),
                            () -> log.debug("Webhook 전송 성공, {}", content)
                    );
        }
    }

}
