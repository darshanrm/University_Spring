package com.darshan.feignClient;

import com.darshan.request.CreateAddressRequest;
import com.darshan.response.AddressResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "address-service",path = "/api/address")
public interface AddressFeignClient {
    @GetMapping("/getAddress/{id}")
    public AddressResponse getAddress(@PathVariable long id);

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest request);
}
