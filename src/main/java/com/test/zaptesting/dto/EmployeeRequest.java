package com.test.zaptesting.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String name;

    private String email;

    private String address;

    private String mobileNumber;

    private String bio;
}
