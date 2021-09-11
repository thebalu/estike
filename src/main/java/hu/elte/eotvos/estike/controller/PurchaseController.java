package hu.elte.eotvos.estike.controller;

import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.PurchaseRequest;
import hu.elte.eotvos.estike.dto.PurchaseResponse;
import hu.elte.eotvos.estike.service.CustomerService;
import hu.elte.eotvos.estike.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PurchaseResponse buy(@RequestBody PurchaseRequest purchaseRequest) {
        return purchaseService.buy(purchaseRequest);
    }

}
