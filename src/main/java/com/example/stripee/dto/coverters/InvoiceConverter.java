package com.example.stripee.dto.coverters;

import com.example.stripee.dto.InvoiceDto;
import com.stripe.model.Invoice;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceConverter {

    public InvoiceDto dtoToEntity(Invoice invoice) {
        InvoiceDto dtoInvoice = new InvoiceDto();
        dtoInvoice.setId(invoice.getId());
        dtoInvoice.setAmount(BigDecimal.valueOf(invoice.getAmountDue()));
        dtoInvoice.setCustomerEmail(invoice.getCustomerEmail());
        dtoInvoice.setCustomerName(invoice.getCustomer());
        return dtoInvoice;
    }

    public List<InvoiceDto> dtoToEntity(List<Invoice> invoice) {
        return invoice.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public Invoice entityToDto(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setAmountDue(Long.valueOf(invoiceDto.getAmount()));
        invoice.setCustomerEmail(invoiceDto.getCustomerEmail());
        invoice.setCustomerName(invoiceDto.getCustomerName());

        return invoice;
    }

    public List<Invoice> entityToDto(List<InvoiceDto> invoiceDtos) {
        return invoiceDtos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
