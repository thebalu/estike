package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailedCustomerDto {
    Integer id;
    String name;
    Integer balance;
    List<ProductDto> favoriteProducts;

    public static DetailedCustomerDto fromCustomer(Customer customer) {
        return DetailedCustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .balance(customer.getBalance())
                .build();
    }
}
