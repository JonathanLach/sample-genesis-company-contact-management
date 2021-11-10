package com.sample.ccm.dto;

import javax.validation.constraints.NotBlank;

public class AddressDTO {

    @NotBlank
    private String streetAddress;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String countryCode;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
