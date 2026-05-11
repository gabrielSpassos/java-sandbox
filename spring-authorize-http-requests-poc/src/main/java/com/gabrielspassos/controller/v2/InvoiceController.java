package com.gabrielspassos.controller.v2;

import com.gabrielspassos.service.v2.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v2")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public String getInvoice() {
        return invoiceService.getInvoice();
    }

    @PostMapping("/invoices")
    public String createInvoice() {
        return invoiceService.createInvoice();
    }

    @DeleteMapping("/invoices")
    public String deleteInvoice() {
        return invoiceService.deleteInvoice();
    }
}
