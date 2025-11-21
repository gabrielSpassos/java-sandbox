package com.gabrielspassos;


import com.gabrielspassos.queue.QueueManager;
import com.gabrielspassos.request.WebhookRequest;
import com.gabrielspassos.task.WebhookTask;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    static void main() {
        IO.println("Queue Delay POC");
        var request1 = new WebhookRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), BigDecimal.TWO);
        var request2 = new WebhookRequest(request1.getUserId(), request1.getAccountId(), BigDecimal.TEN);
        var request3 = new WebhookRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new BigDecimal(5));
        var request4 = new WebhookRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), BigDecimal.ONE);

        QueueManager queueManager = new QueueManager();

        queueManager.executeTask(new WebhookTask(), request1);
        queueManager.executeTask(new WebhookTask(), request2);
        queueManager.executeTask(new WebhookTask(600), request3);
        queueManager.executeTask(new WebhookTask(), request4);
    }
}
