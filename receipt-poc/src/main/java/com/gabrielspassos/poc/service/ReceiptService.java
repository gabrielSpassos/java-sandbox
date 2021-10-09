package com.gabrielspassos.poc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptService {

    private static final String RECEIPT_SPLIT_REGEX = "\n";
    private static final Integer LINES_PER_POSITION = 10;

    public static List<String> createReceiptSplittedContent() throws IOException {
        List<String> receiptSplittedContent = new ArrayList<>();

        String receiptContent = FileService.readFile("receipt.txt");
        List<String> lines = Arrays.asList(receiptContent.split(RECEIPT_SPLIT_REGEX));

        String receiptGroup = createReceiptGroup(lines);

        receiptSplittedContent.add(receiptGroup);

        return receiptSplittedContent;
    }

    private static String createReceiptGroup(List<String> lines) {
        return lines.stream().limit(LINES_PER_POSITION).collect(Collectors.joining());
    }
}
