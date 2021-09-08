package hu.elte.eotvos.estike.controller;

import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.ProductDto;
import hu.elte.eotvos.estike.service.CustomerService;
import hu.elte.eotvos.estike.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDto> index() {
        return customerService.listCustomers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
    }

}
