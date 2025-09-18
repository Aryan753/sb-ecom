package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
     ModelMapper modelMapper;
    @Autowired
     AddressRepository addressRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address=modelMapper.map(addressDTO,Address.class);
        List<Address> addressList=user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);
        Address savedAddress=addressRepository.save(address);

        return modelMapper.map(savedAddress,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddress() {
        List<Address> addressList=addressRepository.findAll();
        List<AddressDTO> addressDTOList=addressList.stream().map(address->modelMapper.map(address,AddressDTO.class))
                .collect(Collectors.toList());
        return addressDTOList;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address=addressRepository.findById(addressId).
                orElseThrow(()-> new ResourceNotFoundException("Address","addressId",addressId));

        return modelMapper.map(address,AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddress(User user) {
        List<Address> addressList=user.getAddresses();
        List<AddressDTO> addressDTOList=addressList.stream().map(address->modelMapper.map(address,AddressDTO.class))
                .collect(Collectors.toList());
        return addressDTOList;
    }


}
