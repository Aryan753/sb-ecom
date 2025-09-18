package com.ecommerce.project.Controller;


import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user=authUtil.loggedInUser();
        AddressDTO savedAddress=addressService.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddress(){
        List<AddressDTO> addressDTOList=addressService.getAddress();
        return new ResponseEntity<List<AddressDTO>>(addressDTOList,HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
       AddressDTO addressDTO=addressService.getAddressById(addressId);
        return new ResponseEntity<AddressDTO>(addressDTO,HttpStatus.OK);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddress(){
        User user=authUtil.loggedInUser();
        List<AddressDTO> addressDTOList=addressService.getUserAddress(user);
        return new ResponseEntity<List<AddressDTO>>(addressDTOList,HttpStatus.OK);
    }
}
