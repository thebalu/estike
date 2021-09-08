package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Customer;
import hu.elte.eotvos.estike.persistence.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    Integer id;
    String name;
    Integer balance;

    public static CustomerDto fromCustomer(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .balance(customer.getBalance())
                .build();
    }
}
