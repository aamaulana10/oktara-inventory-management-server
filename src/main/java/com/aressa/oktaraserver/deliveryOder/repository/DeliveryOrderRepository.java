package com.aressa.oktaraserver.deliveryOder.repository;

import com.aressa.oktaraserver.deliveryOder.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
}
