package com.darshan.response;

import com.darshan.entity.Student;

public class StudentResponse {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private AddressResponse addressResponse;

    public StudentResponse(Student student, AddressResponse addressResponse) {
        this.id=student.getId();
        this.first_name=student.getFirst_name();
        this.last_name=student.getLast_name();;
        this.email=student.getEmail();
        this.addressResponse=addressResponse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressResponse getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }
}
