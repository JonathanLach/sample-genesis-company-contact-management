package com.sample.ccm.dto;

import java.util.ArrayList;
import java.util.List;

public class GetCompanyDTO {

    private Long id;

    private String name;

    private String vatNumber;

    private AddressDTO address;

    private List<GetCompanyContactDTO> contacts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<GetCompanyContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<GetCompanyContactDTO> contacts) {
        this.contacts = contacts;
    }
}
