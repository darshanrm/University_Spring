package com.darshan.controller;

import com.darshan.request.CreateAddressRequest;
import com.darshan.response.AddressResponse;
import com.darshan.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest request){
        return addressService.createAddress(request);
    }

    @GetMapping("/getAddress/{id}")
    public AddressResponse getAddress(@PathVariable long id){
        return addressService.getAddressById(id);
    }
}
