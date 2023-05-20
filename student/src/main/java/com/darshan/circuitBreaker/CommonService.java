package com.darshan.circuitBreaker;

import com.darshan.feignClient.AddressFeignClient;
import com.darshan.request.CreateAddressRequest;
import com.darshan.request.CreateStudentRequest;
import com.darshan.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name="addressService",fallbackMethod = "fallbackGetStudentAddressById")
    public AddressResponse getStudentAddressById(long id){
        AddressResponse addressResponse = addressFeignClient.getAddress(id);
        return addressResponse;
    }

    @CircuitBreaker(name="addressService",fallbackMethod = "fallbackCreateStudentAddress")
    public AddressResponse createStudentAddress(CreateStudentRequest request){
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setCity(request.getCity());
        createAddressRequest.setStreet(request.getStreet());
        AddressResponse addressResponse = addressFeignClient.createAddress(createAddressRequest);
        return addressResponse;
    }

    public AddressResponse fallbackGetStudentAddressById(long id, Throwable ex){
        return new AddressResponse();
    }
    public AddressResponse fallbackCreateStudentAddress(CreateStudentRequest request) {
        return new AddressResponse();
    }

    }
