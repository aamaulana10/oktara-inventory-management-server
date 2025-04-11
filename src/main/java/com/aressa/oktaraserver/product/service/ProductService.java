package com.aressa.oktaraserver.product.service;

import com.aressa.oktaraserver.product.dto.ProductDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDateTime now = LocalDateTime.now();

        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product.setCreatedBy(user);
        product.setUpdatedBy(user);

        product = productRepository.save(product);

        return ProductMapper.toDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductMapper.toDTO(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
