package com.aressa.oktaraserver.deliveryOder.model;

import com.aressa.oktaraserver.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "delivery_order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_order_id")
    private DeliveryOrder deliveryOrder;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;

    private Integer quantity;
    private String unit;
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;
}

