package com.gabrielspassos.task;

import com.gabrielspassos.request.WebhookRequest;

public class WebhookTask implements DelayedTask<WebhookRequest> {

    @Override
    public long maxDelayInMillis() {
        return 500;
    }

    @Override
    public long minDelayInMillis() {
        return 10;
    }

    @Override
    public boolean executeWithDelay(WebhookRequest input) {
        System.out.println("Executing webhook to userId " + input.getUserId() +
                " with account: " + input.getAccountId() + " and balance: " + input.getBalance());
        return true;
    }

}
