package com.gabrielspassos.llm.distillation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.llm.distillation.model.DistillationExample;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.List;

@Component
public class DatasetExporter {

    private final ObjectMapper mapper = new ObjectMapper();

    public void export(List<DistillationExample> dataset, String path) throws Exception {

        try (FileWriter writer = new FileWriter(path)) {

            for (DistillationExample ex : dataset) {
                writer.write(mapper.writeValueAsString(ex));
                writer.write("\n");
            }
        }
    }
}