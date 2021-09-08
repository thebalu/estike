package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseRequest {
    Integer customerId;
    List<ProductQuantity> products;

    @Value
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProductQuantity {
        Integer productId;
        Integer quantity;
    }
}
