package com.sample.ccm.dto;

import com.sample.ccm.enumeration.ContactType;

import java.util.List;

public class GetContactDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private ContactType contactType;
    private String vatNumber;
    private AddressDTO address;
    private List<GetContactCompanyDTO> companies;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public List<GetContactCompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<GetContactCompanyDTO> companies) {
        this.companies = companies;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
