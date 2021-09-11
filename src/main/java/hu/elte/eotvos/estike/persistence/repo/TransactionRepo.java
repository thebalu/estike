package hu.elte.eotvos.estike.persistence.repo;

import hu.elte.eotvos.estike.persistence.model.Purchase;
import hu.elte.eotvos.estike.persistence.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
