package com.gabrielspassos.poc.utils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ConcurrencyUtils {

    public ListenableFuture<Integer> createListenableFutureWithCallback(Integer value) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ListeningExecutorService listening = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<Integer> asyncTask = listening.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            boolean isValueEven = value % 2 == 0;

            if (isValueEven) {
                throw new RuntimeException(String.format("Value %s is even", value));
            }

            return value;
        });
        Futures.addCallback(asyncTask, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("This is a Callback Message. Final result " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("This is a Callback Message. Error " + t);
            }
        }, executor);

        return asyncTask;
    }

    // old api
    public FutureTask<String> fetchConfigTask(String configKey) {
        return new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return String.format("%s.%d", configKey, new Random().nextInt(Integer.MAX_VALUE));
        });
    }

    // new api
    public ListenableFutureTask<String> fetchConfigListenableTask(String configKey) {
        return ListenableFutureTask.create(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return String.format("%s.%d", configKey, new Random().nextInt(Integer.MAX_VALUE));
        });
    }

}
