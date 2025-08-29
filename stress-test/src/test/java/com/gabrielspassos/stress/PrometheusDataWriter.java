package com.gabrielspassos.stress;

import io.gatling.core.actor.Cancellable;
import io.gatling.core.stats.writer.ConsoleData;
import io.gatling.core.stats.writer.DataWriter;
import io.gatling.core.stats.writer.DataWriterMessage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

public class PrometheusDataWriter extends DataWriter<ConsoleData> {

    public PrometheusDataWriter() {
        super("custom-prometheus");
        System.out.println("Custom Prometheus Data Writer instantiated");
    }

    @Override
    public ConsoleData onInit(DataWriterMessage.Init init) {
        var runMessage = init.runMessage();
        System.out.println("Custom Metrics Writer initialized for run: " + runMessage.runId() + " - " + runMessage.simulationClassName());
        Cancellable dummyTimer = () -> {
            System.out.println("Cancelled!");
            return true;
        };
        return new ConsoleData(System.currentTimeMillis(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"), dummyTimer);
    }

    @Override
    public void onFlush(ConsoleData data) {
        try {
            System.out.println("Flushed!");
            pushToPrometheus(data);
        } catch (Exception e) {
            System.out.println("Error to push data to prometheus! " + e.getMessage());
        }
    }

    @Override
    public void onCrash(String cause, ConsoleData data) {
        System.out.println("Crash! Cause: " + cause);
    }

    @Override
    public void onStop(ConsoleData data) {
        System.out.println("Stopped!");
    }

    @Override
    public void onMessage(DataWriterMessage.LoadEvent message, ConsoleData data) {
        System.out.println("Load Event: " + message);
    }

    private void pushToPrometheus(ConsoleData data) throws Exception {
        var successCount = data.globalRequestCounters().successfulCount();
        var errorsCount = data.globalRequestCounters().failedCount();

        // Format in Prometheus exposition format
        String metricData = String.format("%s %s\n%s %s\n", "dungeon_requests_total_success", successCount, "dungeon_requests_total_errors", errorsCount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9091/metrics/job/dungeon_stress_test"))
                .PUT(HttpRequest.BodyPublishers.ofString(metricData))
                .header("Content-Type", "text/plain; version=0.0.4")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException("Failed to push metrics: HTTP " + response.statusCode());
        }
    }

}
