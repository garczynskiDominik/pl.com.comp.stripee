package com.example.stripee.controllers;

import com.example.stripee.dto.InvoiceItemDto;
import com.example.stripee.services.InvoiceItemService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @PostMapping(value = {"/v1/invoiceitems"})
    public void addProductToInvoice() throws StripeException {
        invoiceItemService.addItemToInvoice();
    }

    @GetMapping(value = {"/v1/invoiceitems"})
    public List<InvoiceItemDto> getAllItem() throws StripeException {
        return invoiceItemService.getItems();
    }
}
