package hu.elte.eotvos.estike.service;

import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.ProductDto;
import hu.elte.eotvos.estike.persistence.model.Customer;
import hu.elte.eotvos.estike.persistence.model.Product;
import hu.elte.eotvos.estike.persistence.repo.CustomerRepo;
import hu.elte.eotvos.estike.persistence.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<CustomerDto> listCustomers() {
        return customerRepo.findAll().stream().map(CustomerDto::fromCustomer).collect(Collectors.toList());
    }

    public void createCustomer(CustomerDto customerDto) {
        customerRepo.save(Customer.fromCustomerDto(customerDto));
    }


}
