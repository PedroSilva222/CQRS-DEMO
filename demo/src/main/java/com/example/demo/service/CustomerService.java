package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.valueObject.CustomerId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<User> findUserById(String id) {
        return customerRepository.findById(id);
    }
    public Boolean verifyIfUserExists(CustomerId user) {
            return customerRepository.existsById(user.getValue());
    }
}
