package com.aressa.oktaraserver.inventoryTransaction.controller;

import com.aressa.oktaraserver.inventoryTransaction.dto.InventoryTransactionDTO;
import com.aressa.oktaraserver.inventoryTransaction.model.InventoryTransaction;
import com.aressa.oktaraserver.inventoryTransaction.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class InventoryTransactionController {

    @Autowired
    InventoryTransactionService service;

    @PostMapping
    public InventoryTransactionDTO create(@RequestBody InventoryTransactionDTO trx) {
        return service.create(trx);
    }

    @GetMapping
    public List<InventoryTransaction> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public InventoryTransaction findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

