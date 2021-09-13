package hu.elte.eotvos.estike.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Preconditions;
import hu.elte.eotvos.estike.dto.PurchaseDto;
import hu.elte.eotvos.estike.dto.PurchaseRequest;
import hu.elte.eotvos.estike.dto.PurchaseResponse;
import hu.elte.eotvos.estike.exception.NotFoundException;
import hu.elte.eotvos.estike.exception.ValidationException;
import hu.elte.eotvos.estike.persistence.model.Customer;
import hu.elte.eotvos.estike.persistence.model.Product;
import hu.elte.eotvos.estike.persistence.model.Purchase;
import hu.elte.eotvos.estike.persistence.model.PurchaseItem;
import hu.elte.eotvos.estike.persistence.repo.CustomerRepo;
import hu.elte.eotvos.estike.persistence.repo.ProductRepo;
import hu.elte.eotvos.estike.persistence.repo.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public PurchaseResponse buy(PurchaseRequest purchaseRequest) {
        Customer customer = customerRepo
                .findById(purchaseRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer with id " + purchaseRequest.getCustomerId() + " not found."));

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setHappenedAt(purchaseRequest.getHappenedAt());

        List<PurchaseItem> items = new ArrayList<>();
        int totalPrice = 0;
        ObjectNode productMap = customer.getProductMap();

        for (PurchaseRequest.ProductQuantity productQuantity : purchaseRequest.getProducts()) {
            Product product = productRepo
                    .findById(productQuantity.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product with id " + productQuantity.getProductId() + " not found."));

            if(productQuantity.getQuantity() <= 0) {
                throw new ValidationException("Quantity must be a positive integer.");
            }

            PurchaseItem item = PurchaseItem.builder()
                    .purchase(purchase)
                    .product(product)
                    .priceAtPurchase(product.getPrice())
                    .quantity(productQuantity.getQuantity())
                    .build();

            items.add(item);
            totalPrice += item.getPriceAtPurchase() * item.getQuantity();

            int count = item.getQuantity();
            if (productMap.hasNonNull(item.getId().toString())) {
                count += productMap.get(item.getId().toString()).asInt(0);
            }
            productMap.put(item.getId().toString(), count);
            customer.setProductMap(productMap);
        }

        purchase.setPurchaseItems(items);
        purchase.setTotalPrice(totalPrice);

        int balance = customer.getBalance();
        if (balance < purchase.getTotalPrice()) {
            throw new ValidationException("Customer's balance [" + balance
                    + "] is below the total price [" + purchase.getTotalPrice() + "].");
        }

        customer.setBalance(balance - purchase.getTotalPrice());

        Customer savedCustomer = customerRepo.save(customer);
        Purchase savedPurchase = purchaseRepo.save(purchase);

        return PurchaseResponse.builder()
                .purchaseId(savedPurchase.getId())
                .customerId(savedPurchase.getCustomer().getId())
                .amount(savedPurchase.getTotalPrice())
                .remainingBalance(savedCustomer.getBalance())
                .build();
    }

    public List<PurchaseDto> listPurchases() {
        return purchaseRepo
                .findAll()
                .stream()
                .map(PurchaseDto::fromPurchase)
                .collect(Collectors.toList());
    }
}
