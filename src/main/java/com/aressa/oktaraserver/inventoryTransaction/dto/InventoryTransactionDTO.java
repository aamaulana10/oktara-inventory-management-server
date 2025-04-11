package com.aressa.oktaraserver.inventoryTransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransactionDTO {
    private String item;
    private int quantity;
    private String transactionType;
    private LocalDate transactionDate;
    private BigDecimal price;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
}
