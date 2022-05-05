package com.example.customer.cotroller;

import com.example.customer.dto.CustomerDto.DtoRequst;
import com.example.customer.dto.CustomerDto.DtoResponse;
import com.example.customer.servicese.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/create")
    public ResponseEntity<DtoResponse> create(@RequestBody DtoRequst dtoRequst) throws IllegalAccessException {
        DtoResponse dtoResponse = customerService.create(dtoRequst);
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }


}
