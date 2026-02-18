package com.ecommerce.project.payload;

import com.ecommerce.project.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String email;
    List<OrderItemDTO> orderItems;
    LocalDate orderDate;
    PaymentDTO payment;
    private double totalAmount;
    private String orderStatus;
    private Long addressId;
}
