package com.darshan.service;

import com.darshan.entity.Address;
import com.darshan.repository.AddressRepository;
import com.darshan.request.CreateAddressRequest;
import com.darshan.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest){
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());
        return new AddressResponse(addressRepository.save(address));
    }

    public AddressResponse getAddressById(long id){
        System.out.println("Get Address By Id called ");
        return new AddressResponse(addressRepository.findById(id).get());
    }
}
