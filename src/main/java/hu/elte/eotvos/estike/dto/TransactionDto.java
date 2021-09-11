package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Customer;
import hu.elte.eotvos.estike.persistence.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    Integer id;
    Integer userId;
    String userName;
    Integer amount;
    LocalDateTime happenedAt;
    LocalDateTime storedAt;

    public static TransactionDto fromTransaction(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .userId(transaction.getCustomer().getId())
                .userName(transaction.getCustomer().getName())
                .amount(transaction.getAmount())
                .storedAt(transaction.getStoredAt())
                .happenedAt(transaction.getHappenedAt())
                .build();
    }
}
