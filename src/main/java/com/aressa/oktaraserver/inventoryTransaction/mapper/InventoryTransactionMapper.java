package com.aressa.oktaraserver.inventoryTransaction.mapper;

import com.aressa.oktaraserver.inventoryTransaction.dto.InventoryTransactionDTO;
import com.aressa.oktaraserver.inventoryTransaction.model.InventoryTransaction;
import com.aressa.oktaraserver.inventoryTransaction.model.TransactionType;
import com.aressa.oktaraserver.product.dto.ProductDTO;
import com.aressa.oktaraserver.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class InventoryTransactionMapper {
    public static InventoryTransactionDTO toDTO(InventoryTransaction inventoryTransaction) {
        return InventoryTransactionDTO.builder()
                .item(
                        inventoryTransaction.getItem() != null
                                ? inventoryTransaction.getItem().getName()
                                : null)
                .description(inventoryTransaction.getDescription())
                .quantity(inventoryTransaction.getQuantity())
                .transactionType(
                        inventoryTransaction.getTransactionType() != null
                                ? inventoryTransaction.getTransactionType().name()
                                : null
                )
                .transactionDate(inventoryTransaction.getTransactionDate())
                .price(inventoryTransaction.getPrice())
                .createdAt(inventoryTransaction.getCreatedAt())
                .createdBy(
                        inventoryTransaction.getCreatedBy() != null
                                ? inventoryTransaction.getCreatedBy().getFullName()
                                : null)
                .build();
    }
}
