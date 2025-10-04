package com.ecommerce.project.Controller;

import com.ecommerce.project.payload.OrderDTO;
import com.ecommerce.project.payload.OrderRequestDTO;
import com.ecommerce.project.service.OrderService;
import com.ecommerce.project.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    AuthUtil authUtil;
    @Autowired
    OrderService orderService;
    @PostMapping("order/user/payment/{paymentMethod}")
    public ResponseEntity<OrderDTO> orderProducts(@PathVariable String paymentMethod,
                                                  @RequestBody OrderRequestDTO orderRequestDTO){
        String email=authUtil.loggedInEmail();
         OrderDTO order=orderService.palaceOrder(
                email,orderRequestDTO.getAddressId(),paymentMethod
                ,orderRequestDTO.getPgName(),
                orderRequestDTO.getPgStatus(),
                orderRequestDTO.getPgPaymentId(),
                orderRequestDTO.getPgResponseMessage()
        );

         return new ResponseEntity<OrderDTO>(order,HttpStatus.CREATED);
    }
}
