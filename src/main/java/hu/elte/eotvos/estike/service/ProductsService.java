package hu.elte.eotvos.estike.service;

import hu.elte.eotvos.estike.dto.ProductDto;
import hu.elte.eotvos.estike.persistence.model.Product;
import hu.elte.eotvos.estike.persistence.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductRepo productRepo;

    public List<ProductDto> listProducts() {
        return productRepo.findAll().stream().map(ProductDto::fromProduct).collect(Collectors.toList());
    }

    public void createProduct(ProductDto productDto) {
        productRepo.save(Product.fromProductDto(productDto));
    }


}
