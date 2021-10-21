package com.example.stripee.controllers;

import com.example.stripee.dto.InvoiceDto;
import com.example.stripee.services.InvoiceService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;


    @PostMapping(value = {"/v1/invoices"})
    public void addInvoice() {
        invoiceService.createInvoice();
    }

    @GetMapping(value = {"/v1/invoices"})
    public List<InvoiceDto> getAllInvoices() throws StripeException {
        return invoiceService.getInvoices();
    }
}
