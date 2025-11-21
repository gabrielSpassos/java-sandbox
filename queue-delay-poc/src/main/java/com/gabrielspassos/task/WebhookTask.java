package com.gabrielspassos.task;

import com.gabrielspassos.request.WebhookRequest;

public class WebhookTask implements DelayedTask<WebhookRequest> {

    private final long timeout;

    public WebhookTask() {
        this.timeout = 300;
    }

    public WebhookTask(long timeout) {
        this.timeout = timeout;
    }

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
        try {
            Thread.sleep(timeout);
            System.out.println("Executing webhook to userId " + input.getUserId() +
                    " with account: " + input.getAccountId() + " and balance: " + input.getBalance());
            return true;
        } catch (InterruptedException e) {
            System.out.println("Timeout while processing webhook to userId " + input.getUserId() +
                    " with account: " + input.getAccountId() + " and balance: " + input.getBalance());
            return false;
        }
    }

}
