package com.example.stripee.services;

import com.example.stripee.dto.InvoiceDto;
import com.example.stripee.dto.coverters.InvoiceConverter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class InvoiceService {

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
            log.info("error while creating an invoice");
        }
        log.info("created invoice");
    }



    public List<InvoiceDto> getInvoices() {
        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 5);

        InvoiceCollection invoices = null;
        try {
            invoices = Invoice.list(params);
        } catch (StripeException e) {
            e.printStackTrace();
            log.info("error while returning invoices");
        }
        List<InvoiceDto> invoiceDtos = invoiceConverter.entityToDto(invoices.getData());

        log.info("return list invoices");
        return invoiceDtos;
    }
}

