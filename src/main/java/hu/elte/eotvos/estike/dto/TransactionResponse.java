package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    Integer transactionId;
    Integer customerId;
    Integer amount;
    Integer newBalance;
}
