package com.gabrielspassos.poc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptService {

    private static final String RECEIPT_SPLIT_REGEX = "@";
    private static final Integer LINES_PER_POSITION = 10;

    public static List<String> createReceiptSplittedContent() throws IOException {
        List<String> receiptSplitContent = new ArrayList<>();

        String receiptContent = FileService.readFile("receipt.txt");
        List<String> lines = Arrays.asList(receiptContent.split(RECEIPT_SPLIT_REGEX));

        for (int i = 0; i < lines.size(); i = i + LINES_PER_POSITION) {
            Integer nextSliceIndex = getNextSliceIndex(i, LINES_PER_POSITION, lines);
            List<String> subList = lines.subList(i, nextSliceIndex);
            String receiptGroup = createReceiptGroup(subList);
            receiptSplitContent.add(receiptGroup);
        }

        return receiptSplitContent;
    }

    private static String createReceiptGroup(List<String> lines) {
        return lines.stream().limit(LINES_PER_POSITION).collect(Collectors.joining());
    }

    private static Integer getNextSliceIndex(Integer currentIndex, Integer sliceIndex, List list) {
        int listSize = list.size();
        int intentLastIndex = currentIndex + sliceIndex;
        return Math.min(listSize, intentLastIndex);
    }
}
