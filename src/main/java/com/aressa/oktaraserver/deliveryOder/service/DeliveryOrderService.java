package com.aressa.oktaraserver.deliveryOder.service;

import com.aressa.oktaraserver.deliveryOder.dto.DeliveryOrderDTO;
import com.aressa.oktaraserver.deliveryOder.mapper.DeliveryOrderMapper;
import com.aressa.oktaraserver.deliveryOder.model.DeliveryOrder;
import com.aressa.oktaraserver.deliveryOder.model.DeliveryOrderItems;
import com.aressa.oktaraserver.deliveryOder.repository.DeliveryOrderRepository;
import com.aressa.oktaraserver.product.model.Product;
import com.aressa.oktaraserver.product.repository.ProductRepository;
import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DeliveryOrderDTO create(DeliveryOrderDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DeliveryOrder order = DeliveryOrder.builder()
                .doNumber(dto.getDoNumber())
                .doDate(dto.getDoDate())
                .recipientAddress(dto.getDestination())
                .recipientName(dto.getRecipientName())
                .createdAt(LocalDateTime.now())
                .createdBy(user)
                .build();

        List<DeliveryOrderItems> items = dto.getItems().stream().map(itemDTO -> {
            Product product = productRepository.findByName(itemDTO.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductName()));

            return DeliveryOrderItems.builder()
                    .deliveryOrder(order)
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .unit(itemDTO.getUnit())
                    .price(itemDTO.getPrice())
                    .build();
        }).toList();

        order.setItems(items);

        DeliveryOrder saved = deliveryOrderRepository.save(order);
        return DeliveryOrderMapper.toDTO(saved);
    }

    public List<DeliveryOrderDTO> findAll() {
        return deliveryOrderRepository.findAll().stream()
                .map(DeliveryOrderMapper::toDTO)
                .toList();
    }

    public DeliveryOrderDTO findById(Long id) {
        DeliveryOrder order = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        return DeliveryOrderMapper.toDTO(order);
    }

    public DeliveryOrderDTO update(Long id, DeliveryOrderDTO dto) {
        DeliveryOrder existing = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        existing.setDoDate(dto.getDoDate());
        existing.setRecipientAddress(dto.getDestination());
        existing.setRecipientName(dto.getRecipientName());

        // Clear & Replace Items
        existing.getItems().clear();

        List<DeliveryOrderItems> updatedItems = dto.getItems().stream().map(itemDTO -> {
            Product product = productRepository.findByName(itemDTO.getProductName())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductName()));

            return DeliveryOrderItems.builder()
                    .deliveryOrder(existing)
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .price(itemDTO.getPrice())
                    .unit(itemDTO.getUnit())
                    .build();
        }).toList();

        existing.getItems().addAll(updatedItems);

        DeliveryOrder saved = deliveryOrderRepository.save(existing);
        return DeliveryOrderMapper.toDTO(saved);
    }

    public void delete(Long id) {
        DeliveryOrder order = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        deliveryOrderRepository.delete(order);
    }
}

