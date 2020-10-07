package com.gabrielspassos.poc.enumerator;

import javax.crypto.spec.IvParameterSpec;
import java.security.spec.AlgorithmParameterSpec;

public enum TripleDESMode {

    ECB_NO_PADDING("DESede/ECB/NoPadding", null),
    CBC_NO_PADDING("DESede/CBC/NoPadding", new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}));

    private String mode;
    private AlgorithmParameterSpec parameterSpec;

    TripleDESMode(String mode, AlgorithmParameterSpec parameterSpec) {
        this.mode = mode;
        this.parameterSpec = parameterSpec;
    }

    public String getMode() {
        return mode;
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return parameterSpec;
    }
}
