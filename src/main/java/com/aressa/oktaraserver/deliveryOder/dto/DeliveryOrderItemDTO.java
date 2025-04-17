package com.aressa.oktaraserver.deliveryOder.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderItemDTO {
    private String productName;
    private int quantity;
    private String unit;
    private BigDecimal price;
}

