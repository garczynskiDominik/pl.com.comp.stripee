package com.example.stripee.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDto {

    private String id;
    private BigDecimal amount;
    private String customerEmail;
    private String customerName;
}
