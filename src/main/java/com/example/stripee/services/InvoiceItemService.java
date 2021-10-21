package com.example.stripee.services;

import com.example.stripee.dto.InvoiceItemDto;
import com.example.stripee.dto.coverters.InvoiceItemConverter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceItemCollection;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class InvoiceItemService {

    private InvoiceItemConverter invoiceItemConverter;

    public InvoiceItemService(InvoiceItemConverter invoiceItemConverter) {
        this.invoiceItemConverter = invoiceItemConverter;
    }

    private final String mapKey = "customer";

    @Value("${customer}")
    private String customer;

    @Value("${api.key}")
    private String apiKey;

    public void addItemToInvoice() {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put(mapKey, customer);
        params.put(
                "price",
                "price_1Jn5kgFGz0vIeB6Th2wfKBs9"
        );

        try {
            InvoiceItem invoiceItem =
                    InvoiceItem.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
            log.info("error while adding a product to the invoice");
        }
        log.info("add item to invoice");
    }

    public List<InvoiceItemDto> getItems() {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 5);

        InvoiceItemCollection invoiceItems = null;
        try {
            invoiceItems = InvoiceItem.list(params);
        } catch (StripeException e) {
            e.printStackTrace();
            log.info("error when returning products from an invoice");
        }
        List<InvoiceItemDto> invoiceItemDtos = invoiceItemConverter.entityToDto(invoiceItems.getData());
        log.info("return list items on invoice");

        return invoiceItemDtos;
    }
}
