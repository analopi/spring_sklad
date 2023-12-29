package Kukushkin.sklad.repository;

import Kukushkin.sklad.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}