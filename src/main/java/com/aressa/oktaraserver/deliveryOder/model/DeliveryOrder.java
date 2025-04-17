package com.aressa.oktaraserver.deliveryOder.model;

import com.aressa.oktaraserver.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "delivery_number", nullable = false, unique = true)
    private String doNumber;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate doDate;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryOrderItems> items = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private User createdBy;
}
