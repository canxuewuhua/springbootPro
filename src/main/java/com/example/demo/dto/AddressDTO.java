package com.example.demo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String province;

    private String city;

    private String county;

    public AddressDTO(String province, String city, String county) {
        this.province = province;
        this.city = city;
        this.county = county;
    }
}
