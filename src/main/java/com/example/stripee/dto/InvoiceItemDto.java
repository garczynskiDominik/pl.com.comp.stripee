package com.example.stripee.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InvoiceItemDto {

    private String id;
    private String name;
    private BigDecimal amount;
}
