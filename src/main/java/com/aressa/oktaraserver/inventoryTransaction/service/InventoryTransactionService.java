package com.aressa.oktaraserver.inventoryTransaction.service;

import com.aressa.oktaraserver.inventoryTransaction.dto.InventoryTransactionDTO;
import com.aressa.oktaraserver.inventoryTransaction.mapper.InventoryTransactionMapper;
import com.aressa.oktaraserver.inventoryTransaction.model.InventoryTransaction;
import com.aressa.oktaraserver.inventoryTransaction.model.TransactionType;
import com.aressa.oktaraserver.inventoryTransaction.repository.InventoryTransactionRepository;
import com.aressa.oktaraserver.product.mapper.ProductMapper;
import com.aressa.oktaraserver.product.model.Product;
import com.aressa.oktaraserver.product.repository.ProductRepository;
import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryTransactionService {

    @Autowired
    InventoryTransactionRepository inventoryTransactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    public InventoryTransactionDTO create(InventoryTransactionDTO trx) {

        InventoryTransaction inventoryTransaction = modelMapper.map(trx, InventoryTransaction.class);

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product item = productRepository.findByName(trx.getItem())
                .orElseThrow(() -> new RuntimeException("Item not found" + trx.getItem()));

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDate localDateNow = LocalDate.now();

        inventoryTransaction.setCreatedAt(localDateTimeNow);
        inventoryTransaction.setTransactionDate(localDateNow);
        inventoryTransaction.setCreatedBy(user);
        inventoryTransaction.setItem(item);
        inventoryTransaction.setTransactionType(TransactionType.valueOf(trx.getTransactionType()));

        inventoryTransaction = inventoryTransactionRepository.save(inventoryTransaction);

        return InventoryTransactionMapper.toDTO(inventoryTransaction);
    }

    public List<InventoryTransaction> findAll() {
        return inventoryTransactionRepository.findAll();
    }

    public InventoryTransaction findById(Long id) {
        return inventoryTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteById(Long id) {
        inventoryTransactionRepository.deleteById(id);
    }
}

