package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Order order;

    @NotBlank
    @Size(min = 4, message = "Payment must contains atleast 4 character ")
    private String paymentMethod;

    private String pgPaymentId;
    private String pgStatus;
    private String pgResponseMessage;
    private String pgName;

    private Payment(Long paymentId,String pgPaymentId,String pgStatus,String pgResponseMessage,String pgName ){
      this.paymentId = paymentId;
      this.pgPaymentId = pgPaymentId;
      this.pgStatus = pgStatus;
      this.pgResponseMessage = pgResponseMessage;
      this.pgName = pgName;

    }
}
