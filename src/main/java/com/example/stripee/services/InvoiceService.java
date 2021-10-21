package com.example.stripee.services;

import com.example.stripee.dto.InvoiceDto;
import com.example.stripee.dto.coverters.InvoiceConverter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Autowired
    private final InvoiceConverter invoiceConverter;

    public InvoiceService(InvoiceConverter invoiceConverter) {
        this.invoiceConverter = invoiceConverter;
    }

    private final String mapKey = "customer";

    @Value("${customer}")
    private String customer;

    @Value("${api.key}")
    private String apiKey;



    public void createInvoice() {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put(mapKey, customer);
        try {
            Invoice invoice = Invoice.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    public List<InvoiceDto> getInvoices() throws StripeException {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        InvoiceCollection invoices = Invoice.list(params);
        List<InvoiceDto> invoiceDtos = invoiceConverter.entityToDto(invoices.getData());
        return invoiceDtos;
    }
}
