package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Purchase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseDto {
    Integer id;
    Integer customerId;
    String customerName;
    Integer totalPrice;
    LocalDateTime happenedAt;
    LocalDateTime storedAt;
    List<PurchaseItemDto> items;

    public static PurchaseDto fromPurchase(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .customerId(purchase.getCustomer().getId())
                .customerName(purchase.getCustomer().getName())
                .totalPrice(purchase.getTotalPrice())
                .storedAt(purchase.getStoredAt())
                .happenedAt(purchase.getHappenedAt())
                .items(purchase
                        .getPurchaseItems()
                        .stream()
                        .map(item -> new PurchaseItemDto(item.getId(), item.getQuantity(), item.getPriceAtPurchase()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Value
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class PurchaseItemDto {
        Integer itemId;
        Integer quantity;
        Integer priceAtPurchase;
    }
}
