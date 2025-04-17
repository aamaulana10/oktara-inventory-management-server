package com.aressa.oktaraserver.deliveryOder.dto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderDTO {
    private String doNumber;
    private LocalDate doDate;
    private String destination;
    private String recipientName;
    private List<DeliveryOrderItemDTO> items;
}

