package com.example.stripee.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceItemCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private final String mapKey = "customer";

    @Value("${customer}")
    private String customer;

    @Value("${api.key}")
    private String apiKey;

    @PostMapping(value = {"/v1/invoiceitems"})
    public void addProductToInvoice() throws StripeException {

        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put(mapKey, customer);
        params.put(
                "price",
                "price_1Jn5kgFGz0vIeB6Th2wfKBs9"
        );

        InvoiceItem invoiceItem =
                InvoiceItem.create(params);
    }

    @GetMapping(value = {"/v1/invoiceitems"})
    public Object[] getAllItem() throws StripeException {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        InvoiceItemCollection invoiceItems = InvoiceItem.list(params);


        return invoiceItems.getData().stream().toArray();
    }
}
