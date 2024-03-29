package com.market.connect.services;

import com.market.connect.models.dtos.CustomerDTO;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerValidationService customerValidationService;

    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationService customerValidationService, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerValidationService.validateUniqueCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
        log.info("Customer with id {}, saved in database", savedCustomer.getId());

        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getFilteredCustomers(Boolean isActive, String city, String subscription) {
        List<Customer> customers = customerRepository.findFilteredCustomers(isActive, city, subscription);
        log.info("Number of customers: " + customers.size());

        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
    }
}
