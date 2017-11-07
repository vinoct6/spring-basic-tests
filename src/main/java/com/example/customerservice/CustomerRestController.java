package com.example.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {


    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer customerById(@PathVariable  Long id) {
        return customerRepository.findOne(id);
    }

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Customer> customers() {
        return customerRepository.findAll();
    }
}
