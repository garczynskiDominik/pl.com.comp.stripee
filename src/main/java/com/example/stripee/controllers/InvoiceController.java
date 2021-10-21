package com.example.stripee.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InvoiceController {

    private final String mapKey = "customer";

    @Value("${customer}")
    private String customer;

    @Value("${api.key}")
    private String apiKey;


    @PostMapping(value = {"/v1/invoices"})
    public void addInvoice() {

        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put(mapKey, customer);
        try {
            Invoice invoice = Invoice.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = {"/v1/invoices"})
    public Object[] getAllInvoices() throws StripeException {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        InvoiceCollection invoices = Invoice.list(params);
        List<Invoice> list = invoices.getData();
        return null;
    }
}
