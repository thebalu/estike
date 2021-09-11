package hu.elte.eotvos.estike.service;

import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.ProductDto;
import hu.elte.eotvos.estike.dto.TransactionDto;
import hu.elte.eotvos.estike.dto.TransactionRequest;
import hu.elte.eotvos.estike.dto.TransactionResponse;
import hu.elte.eotvos.estike.exception.NotFoundException;
import hu.elte.eotvos.estike.exception.ValidationException;
import hu.elte.eotvos.estike.persistence.model.Customer;
import hu.elte.eotvos.estike.persistence.model.Product;
import hu.elte.eotvos.estike.persistence.model.Transaction;
import hu.elte.eotvos.estike.persistence.repo.CustomerRepo;
import hu.elte.eotvos.estike.persistence.repo.ProductRepo;
import hu.elte.eotvos.estike.persistence.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    public List<CustomerDto> listCustomers() {
        return customerRepo.findAll().stream().map(CustomerDto::fromCustomer).collect(Collectors.toList());
    }

    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        if (customerRepo.existsById(customerDto.getId())) {
            throw new ValidationException("Customer with id " + customerDto.getId() + "already exists.");
        }
        Customer saved = customerRepo.save(Customer.fromCustomerDto(customerDto));
        Transaction transaction = Transaction.builder()
                .customer(saved)
                .amount(saved.getBalance())
                .build();
        transactionRepo.save(transaction);
    }

    @Transactional
    public TransactionResponse addBalance(TransactionRequest transactionRequest) {
        Customer customer = customerRepo
                .findById(transactionRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer with id " + transactionRequest.getCustomerId() + " not found."));

        if (transactionRequest.getAmount() < 0 &&
                customer.getBalance() - transactionRequest.getAmount() < 0) {
            throw new ValidationException("Balance can't go below zero.");
        }

        Transaction transaction = Transaction.builder()
                .customer(customer)
                .amount(transactionRequest.getAmount())
                .happenedAt(transactionRequest.getHappenedAt())
                .build();

        Transaction saved = transactionRepo.save(transaction);
        customer.setBalance(customer.getBalance() + saved.getAmount());
        customerRepo.save(customer);

        return TransactionResponse.builder()
                .transactionId(saved.getId())
                .amount(saved.getAmount())
                .customerId(customer.getId())
                .newBalance(customer.getBalance())
                .build();
    }

    public List<TransactionDto> listTransactions(Integer customerId) {
        Customer customer = customerRepo
                .findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer with id " + customerId + " not found."));

        return customer.getTransactions().stream().map(TransactionDto::fromTransaction).collect(Collectors.toList());
    }
}
