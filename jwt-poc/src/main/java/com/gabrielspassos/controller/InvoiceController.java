package com.gabrielspassos.controller;

import com.gabrielspassos.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getInvoice() {
        return invoiceService.getInvoice();
    }

    @PostMapping
    public String createInvoice() {
        return invoiceService.createInvoice();
    }

    @DeleteMapping
    public String deleteInvoice() {
        return invoiceService.deleteInvoice();
    }
}