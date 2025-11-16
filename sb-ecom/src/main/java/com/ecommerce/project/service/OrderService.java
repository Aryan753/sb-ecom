package com.ecommerce.project.service;

import com.ecommerce.project.payload.OrderDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    @Transactional
    OrderDTO palaceOrder(String email, Long addressId, String paymentMethod, String pgName, String pgStatus, String pgPaymentId, String pgResponseMessage);
}
