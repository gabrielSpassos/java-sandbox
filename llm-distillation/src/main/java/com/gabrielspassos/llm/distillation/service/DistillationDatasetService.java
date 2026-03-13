package com.gabrielspassos.llm.distillation.service;

import com.gabrielspassos.llm.distillation.model.DistillationExample;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistillationDatasetService {

    private final TeacherGenerator teacher;

    public DistillationDatasetService(TeacherGenerator teacher) {
        this.teacher = teacher;
    }

    public List<DistillationExample> generateDataset(List<String> prompts) {
        return prompts.stream()
                .map(p -> new DistillationExample(
                        p,
                        teacher.generateAnswer(p)
                ))
                .toList();
    }
}
