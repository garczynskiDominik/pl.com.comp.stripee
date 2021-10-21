package com.example.stripee.services;

import com.example.stripee.dto.InvoiceItemDto;
import com.example.stripee.dto.coverters.InvoiceItemConverter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceItemCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addItemToInvoice() throws StripeException {
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

    public List<InvoiceItemDto> getItems() throws StripeException {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 5);

        InvoiceItemCollection invoiceItems = InvoiceItem.list(params);
        List<InvoiceItemDto> invoiceItemDtos = invoiceItemConverter.entityToDto(invoiceItems.getData());

        return invoiceItemDtos;
    }
}
