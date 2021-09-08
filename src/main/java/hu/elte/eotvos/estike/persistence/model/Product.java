package hu.elte.eotvos.estike.persistence.model;

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
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private String imageURL;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<PurchaseItem> purchaseItems;

    public enum ProductType {beer, longdrink, shortdrink, cocktail, other, wine, soda, meal}

    public static Product fromProductDto(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .type(dto.getType())
                .imageURL(dto.getImageURL())
                .build();
    }

}
