package com.aressa.oktaraserver.deliveryOder.controller;

import com.aressa.oktaraserver.deliveryOder.dto.DeliveryOrderDTO;
import com.aressa.oktaraserver.deliveryOder.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @PostMapping
    public ResponseEntity<DeliveryOrderDTO> create(@RequestBody DeliveryOrderDTO dto) {
        try {
            return ResponseEntity.ok(deliveryOrderService.create(dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrderDTO>> getAll() {
        return ResponseEntity.ok(deliveryOrderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryOrderService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryOrderDTO> update(@PathVariable Long id, @RequestBody DeliveryOrderDTO dto) {
        try {
            return ResponseEntity.ok(deliveryOrderService.update(id, dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
