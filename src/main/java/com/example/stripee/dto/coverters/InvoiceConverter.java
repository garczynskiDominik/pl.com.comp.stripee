package com.example.stripee.dto.coverters;

import com.example.stripee.dto.InvoiceDto;
import com.stripe.model.Invoice;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceConverter {

    public InvoiceDto entityToDto(Invoice invoice) {
        InvoiceDto dtoInvoice = new InvoiceDto();
        dtoInvoice.setId(invoice.getId());
        dtoInvoice.setAmount(BigDecimal.valueOf(invoice.getAmountDue()));
        dtoInvoice.setCustomerEmail(invoice.getCustomerEmail());
        dtoInvoice.setCustomerName(invoice.getCustomer());
        return dtoInvoice;
    }

    public List<InvoiceDto> entityToDto(List<Invoice> invoice) {
        return invoice.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
