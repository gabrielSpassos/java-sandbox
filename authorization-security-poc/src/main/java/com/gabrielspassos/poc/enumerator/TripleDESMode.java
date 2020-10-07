package com.gabrielspassos.poc.enumerator;

public enum TripleDESMode {

    ECB_NO_PADDING("DESede/ECB/NoPadding"),
    CBC_NO_PADDING("DESede/CBC/NoPadding");

    private String mode;

    TripleDESMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
