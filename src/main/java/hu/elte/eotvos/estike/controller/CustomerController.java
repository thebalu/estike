package hu.elte.eotvos.estike.controller;

import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.TransactionDto;
import hu.elte.eotvos.estike.dto.TransactionRequest;
import hu.elte.eotvos.estike.dto.TransactionResponse;
import hu.elte.eotvos.estike.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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


    @PostMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse addBalance(@RequestBody TransactionRequest transactionRequest) {
        return customerService.addBalance(transactionRequest);
    }

    @GetMapping(value = "/transactions/{customerId}")
    public List<TransactionDto> listTransactions(@PathVariable Integer customerId) {
        return customerService.listTransactions(customerId);
    }
}
