package com.test.zaptesting.service;

import com.smartsensesolutions.java.commons.base.repository.BaseRepository;
import com.smartsensesolutions.java.commons.base.service.BaseService;
import com.smartsensesolutions.java.commons.specification.SpecificationUtil;
import com.test.zaptesting.dao.Employee;
import com.test.zaptesting.dao.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService extends BaseService<Employee, Long> {


    private final EmployeeRepository employeeRepository;

    private final SpecificationUtil<Employee> specificationUtil;


    @Override
    protected BaseRepository<Employee, Long> getRepository() {
        return employeeRepository;
    }

    @Override
    protected SpecificationUtil<Employee> getSpecificationUtil() {
        return specificationUtil;
    }


}
