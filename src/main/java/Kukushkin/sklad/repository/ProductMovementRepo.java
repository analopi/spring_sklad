package Kukushkin.sklad.repository;

import Kukushkin.sklad.entity.ProductMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMovementRepo extends JpaRepository<ProductMovementEntity, Integer> {
}
