package com.gabrielspassos.service.v2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @PreAuthorize("hasAuthority('invoice:read')")
    public String getInvoice() {
        return "Invoice Data";
    }

    @PreAuthorize("hasAuthority('invoice:write')")
    public String createInvoice() {
        return "Invoice Created";
    }

    @PreAuthorize("hasAuthority('invoice:delete')")
    public String deleteInvoice() {
        return "Invoice Deleted";
    }
}
