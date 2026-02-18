package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotBlank
    @Size(min = 5,message = "Street must be atleast 5 character ")
    private String street;
    @NotBlank
    @Size(min = 5,message = "Building must be atleast 5 character ")
    private String building;
    @NotBlank
    @Size(min = 3,message = "City must be atleast 3 character ")
    private String city;
    @NotBlank
    @Size(min = 2,message = "State must be atleast 2 character ")
    private String state;
    @NotBlank
    @Size(min = 2,message = "Country must be atleast 2 character ")
    private String country;
    @NotBlank
    @Size(min = 6,message = "Pincode must be atleast 6 character ")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String street, String building, String city, String state, String country, String pincode) {
        this.street = street;
        this.building = building;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
