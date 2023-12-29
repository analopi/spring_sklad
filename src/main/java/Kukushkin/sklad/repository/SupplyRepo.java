package Kukushkin.sklad.repository;

import Kukushkin.sklad.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepo extends JpaRepository<SupplyEntity, Integer> {
}
