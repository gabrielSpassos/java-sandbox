package com.gabrielspassos.client;

import com.gabrielspassos.client.request.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {

    private static final Logger logger = LoggerFactory.getLogger(NotificationClient.class);

    private final RestClient restClient;

    public NotificationClient(@Qualifier("notificationRestClientBuilder") RestClient.Builder restClientBuilder,
                              @Value("${notification.api.url}") String url) {
        this.restClient = restClientBuilder.baseUrl(url).build();
    }

    public boolean notifyUser(NotificationRequest request) {
        try {
            ResponseEntity<Void> response = restClient.post()
                    .uri("/v1/notify")
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            logger.error("Error to notify user {}", request.userId(), e);
            throw e;
        }
    }
}
