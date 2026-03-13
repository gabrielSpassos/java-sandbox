package com.gabrielspassos.llm.distillation.runner;

import com.gabrielspassos.llm.distillation.model.DistillationExample;
import com.gabrielspassos.llm.distillation.service.DatasetExporter;
import com.gabrielspassos.llm.distillation.service.DistillationDatasetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistillationRunner implements CommandLineRunner {

    private final DistillationDatasetService datasetService;
    private final DatasetExporter exporter;

    public DistillationRunner(
            DistillationDatasetService datasetService,
            DatasetExporter exporter
    ) {
        this.datasetService = datasetService;
        this.exporter = exporter;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> prompts = List.of(
                "Explain what Apache Kafka is",
                "Explain what Redis is",
                "Explain what Kubernetes is",
                "Explain what Spring Boot is"
        );

        List<DistillationExample> dataset =
                datasetService.generateDataset(prompts);

        exporter.export(dataset, "distillation-dataset.jsonl");

        System.out.println("Dataset generated!");
    }
}