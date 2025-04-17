package com.aressa.oktaraserver.deliveryOder.mapper;

import com.aressa.oktaraserver.deliveryOder.dto.DeliveryOrderDTO;
import com.aressa.oktaraserver.deliveryOder.dto.DeliveryOrderItemDTO;
import com.aressa.oktaraserver.deliveryOder.model.DeliveryOrder;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderMapper {

    public static DeliveryOrderDTO toDTO(DeliveryOrder entity) {
        return DeliveryOrderDTO.builder()
                .doNumber(entity.getDoNumber())
                .doDate(entity.getDoDate())
                .destination(entity.getRecipientAddress())
                .recipientName(entity.getRecipientName())
                .items(entity.getItems().stream().map(item ->
                        DeliveryOrderItemDTO.builder()
                                .productName(item.getProduct().getName())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .unit(item.getUnit())
                                .build()).toList())
                .build();
    }
}

