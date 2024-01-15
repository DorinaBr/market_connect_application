package com.market.connect.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.connect.models.dtos.CustomerDTO;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;
    private final ObjectMapper objectMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationService customerValidationService, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerValidationService.validateUniqueCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(objectMapper.convertValue(customerDTO, Customer.class));
        log.info("Customer with id {}, saved in database.", savedCustomer.getId());

        return objectMapper.convertValue(savedCustomer, CustomerDTO.class);
    }
}
