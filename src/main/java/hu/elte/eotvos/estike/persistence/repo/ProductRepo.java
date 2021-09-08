package hu.elte.eotvos.estike.persistence.repo;

import hu.elte.eotvos.estike.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
