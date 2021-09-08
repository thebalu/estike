package hu.elte.eotvos.estike.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hu.elte.eotvos.estike.persistence.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    Integer id;
    String name;
    Integer price;
    Product.ProductType type;
    String imageURL;

    public static ProductDto fromProduct(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .type(product.getType())
                .imageURL(product.getImageURL())
                .build();
    }
}
