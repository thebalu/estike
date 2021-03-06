package hu.elte.eotvos.estike.persistence.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.elte.eotvos.estike.dto.CustomerDto;
import hu.elte.eotvos.estike.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer balance;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    // Count of how many of each product they have purchased
    private ObjectNode productMap;

    public static Customer fromCustomerDto(CustomerDto dto) {
        return Customer.builder()
                .name(dto.getName())
                .balance(dto.getBalance())
                .build();
    }

}
