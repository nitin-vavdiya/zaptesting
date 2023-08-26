package com.test.zaptesting.controller;


import com.smartsensesolutions.java.commons.FilterRequest;
import com.test.zaptesting.dao.Employee;
import com.test.zaptesting.dto.EmployeeRequest;
import com.test.zaptesting.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.RequestFilter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(examples = {

                    @ExampleObject(name = "Create employee", value = """
                                                        {
                                                          "name": "John Doe",
                                                          "email": "john.doe@gmail.com",
                                                          "address": "932, Juliane-Hagen-Ring, Baierring, Warburg, Germany",
                                                          "mobileNumber": "6912345678",
                                                          "bio": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
                                                        }
                            """)
            })
    })
    @ApiResponse(responseCode = "201", content = {
            @Content(examples = {
                    @ExampleObject(name = "Success response", value = """
                                                                    {
                                                                       "name": "John Doe",
                                                                       "email": "john.doe@gmail.com",
                                                                       "address": "932, Juliane-Hagen-Ring, Baierring, Warburg, Germany",
                                                                       "mobileNumber": "6912345678",
                                                                       "bio": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
                                                                     }          
                            """)
            })
    })
    @Operation(summary = "Create Employee", description = "Create Employee in database and upon success it will return employee details ")
    @PostMapping(path = "employee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> add(@RequestBody EmployeeRequest employeeRequest){
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(Employee.of(employeeRequest)));
    }


    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
            @Content(examples = {

                    @ExampleObject(name = "Gel all employee who's name contains John", value = """
                                                        {
                                                          "page": 0,
                                                          "size": 10,
                                                          "sort": {
                                                            "column": "name",
                                                            "sortType": "DESC"
                                                          },
                                                          "criteria": [
                                                            {
                                                              "column": "name",
                                                              "operator": "like",
                                                              "values": [
                                                                "John"
                                                              ]
                                                            }
                                                          ]
                                                        }
                            """)
            })
    })
    @ApiResponse(responseCode = "200", content = {
            @Content(examples = {
                    @ExampleObject(name = "Success response", value = """
                                                                    {
                                                                      "content": [
                                                                        {
                                                                          "name": "John Doe",
                                                                          "email": "john.doe@gmail.com",
                                                                          "address": "932, Juliane-Hagen-Ring, Baierring, Warburg, Germany",
                                                                          "mobileNumber": "6912345678",
                                                                          "bio": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
                                                                        }
                                                                      ],
                                                                      "pageable": {
                                                                        "pageNumber": 0,
                                                                        "pageSize": 10,
                                                                        "sort": {
                                                                          "empty": false,
                                                                          "sorted": true,
                                                                          "unsorted": false
                                                                        },
                                                                        "offset": 0,
                                                                        "paged": true,
                                                                        "unpaged": false
                                                                      },
                                                                      "last": true,
                                                                      "totalPages": 1,
                                                                      "totalElements": 1,
                                                                      "size": 10,
                                                                      "number": 0,
                                                                      "sort": {
                                                                        "empty": false,
                                                                        "sorted": true,
                                                                        "unsorted": false
                                                                      },
                                                                      "first": true,
                                                                      "numberOfElements": 1,
                                                                      "empty": false
                                                                    }      
                            """)
            })
    })
    @Operation(summary = "Get list og employee", description = "Get list of employee with filter and pagination")
    @PostMapping(path = "employee/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Employee>> list(@RequestBody FilterRequest requestFilter){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filter(requestFilter));
    }

    @ApiResponse(responseCode = "200", content = {
            @Content(examples = {
                    @ExampleObject(name = "Success response", value = """
                                                                    {
                                                                        "name": "John Doe",
                                                                        "email": "john.doe@gmail.com",
                                                                        "address": "932, Juliane-Hagen-Ring, Baierring, Warburg, Germany",
                                                                        "mobileNumber": "6912345678",
                                                                        "bio": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
                                                                      }
                            """)
            })
    })
    @Operation(summary = "Get employee by id", description = "Get employee details by id")
    @GetMapping(path = "employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getById(
            @Parameter(description = "Employee id", name = "id", examples = {@ExampleObject(name = "id", value = "1")})
            @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.get(id));
    }


    @Operation(summary = "Delete employee by id", description = "Delete employee by id")
    @DeleteMapping(path = "employee/{id}")
    public ResponseEntity deletedById(
            @Parameter(description = "Employee id", name = "id", examples = {@ExampleObject(name = "id", value = "1")})
            @PathVariable("id") long id){
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
