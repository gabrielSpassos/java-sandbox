package com.gabrielspassos;

import com.gabrielspassos.worker.WorkerManager;

public class Main {

    public static void main(String[] args) {
        WorkerManager workerManager = WorkerManager.getWorkerManager();

        workerManager.execute();
    }

}