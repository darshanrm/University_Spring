package com.darshan.service;

import com.darshan.circuitBreaker.CommonService;
import com.darshan.entity.Student;
import com.darshan.feignClient.AddressFeignClient;
import com.darshan.repository.StudentRepository;
import com.darshan.request.CreateAddressRequest;
import com.darshan.request.CreateStudentRequest;
import com.darshan.response.AddressResponse;
import com.darshan.response.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//enable below annotation whenever @Value variable is referring to config server and can change at runtime
//@RefreshScope
@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;

    @Autowired
    CommonService commonService;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest){
//        AddressResponse addressResponse = createAddress(createStudentRequest.getStreet(),createStudentRequest.getCity());
//        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
//        createAddressRequest.setCity(createStudentRequest.getCity());
//        createAddressRequest.setStreet(createStudentRequest.getStreet());
//        AddressResponse addressResponse = addressFeignClient.createAddress(createAddressRequest);
        AddressResponse addressResponse = commonService.createStudentAddress(createStudentRequest);
        Student student = new Student();
        student.setFirst_name(createStudentRequest.getFirst_name());
        student.setLast_name(createStudentRequest.getLast_name());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddress_id(addressResponse.getId());
        student = studentRepository.save(student);
        return new StudentResponse(student,addressResponse);
    }

    public AddressResponse createAddress(String street,String city) {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setCity(city);
        createAddressRequest.setStreet(street);

        AddressResponse addressResponse =  webClient.post().uri("/create")
                .body(Mono.just(createAddressRequest),CreateAddressRequest.class)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();

        return addressResponse;
    }

    public StudentResponse getStudentById(long id){
        logger.info("get student by id");
        Student student = studentRepository.findById(id).get();
//        AddressResponse addressResponse = getAddressById(student.getAddress_id());
//        AddressResponse addressResponse = addressFeignClient.getAddress(student.getAddress_id());
//        AddressResponse addressResponse = getStudentAddressById(student.getAddress_id());
//        AddressResponse addressResponse = getStudentAddressById(student.getAddress_id());
        AddressResponse addressResponse = commonService.getStudentAddressById(student.getAddress_id());
        return new StudentResponse(student,addressResponse);
    }

    public AddressResponse getAddressById(long id){
        return webClient.get().uri("/getAddress/"+id)
                .retrieve()
                .bodyToMono(AddressResponse.class)
                .block();
    }

//    public AddressResponse getStudentAddressById(long id){
//        AddressResponse addressResponse = addressFeignClient.getAddress(id);
//        return addressResponse;
//    }
//
//    public AddressResponse createStudentAddress(CreateStudentRequest request){
//        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
//        createAddressRequest.setCity(request.getCity());
//        createAddressRequest.setStreet(request.getStreet());
//        AddressResponse addressResponse = addressFeignClient.createAddress(createAddressRequest);
//        return addressResponse;
//    }
}
