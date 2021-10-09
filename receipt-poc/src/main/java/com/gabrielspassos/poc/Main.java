package com.gabrielspassos.poc;

import com.gabrielspassos.poc.service.ReceiptService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> receiptSplittedContent = ReceiptService.createReceiptSplittedContent();
        for (String line: receiptSplittedContent) {
            System.out.println(line);
        }
    }
}
