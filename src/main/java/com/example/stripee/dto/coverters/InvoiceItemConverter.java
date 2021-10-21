package com.example.stripee.dto.coverters;

import com.example.stripee.dto.InvoiceItemDto;
import com.stripe.model.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceItemConverter {

    public InvoiceItemDto entityToDto(InvoiceItem invoiceItem) {
        InvoiceItemDto invoiceItemDto = new InvoiceItemDto();
        invoiceItemDto.setId(invoiceItem.getId());
        invoiceItemDto.setName(invoiceItem.getDescription());
        invoiceItemDto.setAmount(invoiceItem.getPrice().getUnitAmountDecimal());
        return invoiceItemDto;
    }

    public List<InvoiceItemDto> entityToDto(List<InvoiceItem> invoiceItem) {
        return invoiceItem.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
