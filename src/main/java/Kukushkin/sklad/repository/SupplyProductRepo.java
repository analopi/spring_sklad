package Kukushkin.sklad.repository;

import Kukushkin.sklad.entity.SupplyEntity;
import Kukushkin.sklad.entity.SupplyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyProductRepo extends JpaRepository<SupplyProduct, Integer> {
    List<SupplyProduct> findBySupplyId(SupplyEntity supply);
}

