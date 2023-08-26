package com.test.zaptesting.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartsensesolutions.java.commons.base.entity.BaseEntity;
import com.test.zaptesting.dto.EmployeeRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial", nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String bio;

    public static Employee of(EmployeeRequest employeeRequest){
            return Employee.builder()
                    .name(employeeRequest.getName())
                    .email(employeeRequest.getEmail())
                    .address(employeeRequest.getAddress())
                    .mobileNumber(employeeRequest.getMobileNumber())
                    .bio(employeeRequest.getBio())
                    .build();
    }
}
