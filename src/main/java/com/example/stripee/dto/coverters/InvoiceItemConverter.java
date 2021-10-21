package com.example.stripee.dto.coverters;

import com.example.stripee.dto.InvoiceDto;
import com.example.stripee.dto.InvoiceItemDto;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceItemConverter {

    public InvoiceItemDto dtoToEntity(InvoiceItem invoiceItem) {
        InvoiceItemDto invoiceItemDto = new InvoiceItemDto();
        invoiceItemDto.setId(invoiceItem.getId());
        invoiceItemDto.setName(invoiceItem.getDescription());
        invoiceItemDto.getAmount(BigDecimal.valueOf(invoiceItem.getAmount()));
        return invoiceItemDto;
    }

    public List<InvoiceItemDto> dtoToEntity(List<InvoiceItem> invoiceItem) {
        return invoiceItem.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public InvoiceItem entityToDto(InvoiceItemDto invoiceItemDto) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(invoiceItemDto.getId());
        invoiceItem.setDescription(invoiceItemDto.getName());
        invoiceItem.setAmount(Long.valueOf(invoiceItemDto.getAmount()));

        return invoiceItem;
    }

    public List<InvoiceItem> entityToDto(List<InvoiceItemDto> invoiceItemDtos) {
        return invoiceItemDtos.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


}
