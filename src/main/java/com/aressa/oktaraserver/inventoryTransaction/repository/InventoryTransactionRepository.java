package com.aressa.oktaraserver.inventoryTransaction.repository;

import com.aressa.oktaraserver.inventoryTransaction.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
}
