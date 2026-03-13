package com.gabrielspassos.llm.distillation.model;

public record DistillationExample(
        String prompt,
        String completion
) {}
