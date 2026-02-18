package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repository.AddressRepository;
import com.ecommerce.project.repository.UserRepository;
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
    @Autowired
    UserRepository userRepository;

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

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDataBase=addressRepository.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address","addressId",addressId)
        );
        addressFromDataBase.setCity(addressDTO.getCity());
        addressFromDataBase.setCountry(addressDTO.getCountry());
        addressFromDataBase.setPincode(addressDTO.getPincode());
        addressFromDataBase.setStreet(addressDTO.getStreet());
        addressFromDataBase.setState(addressDTO.getState());
        addressFromDataBase.setBuilding(addressDTO.getBuilding());

        Address updatedAddress=addressRepository.save(addressFromDataBase);
        User user=addressFromDataBase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);
        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDataBase=addressRepository.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address","addressId",addressId)
        );
        User user=addressFromDataBase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDataBase);
        return "Address has been deleted successfully with id:"+addressId;
    }


}
