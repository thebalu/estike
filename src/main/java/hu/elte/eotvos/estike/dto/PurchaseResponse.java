package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseResponse {
    Integer purchaseId;
    Integer customerId;
    Integer amount;
    Integer remainingBalance;
}
