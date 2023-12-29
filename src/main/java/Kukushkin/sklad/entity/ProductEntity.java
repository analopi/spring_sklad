package Kukushkin.sklad.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ProductEntity {
    // первичный ключ
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    // остальные поля
    private String name;
    private String description;
    private Double price;
    private Integer count;

    @OneToMany(mappedBy = "productId")
    private List<SupplyProduct> manySupplyProduct;

    @OneToMany(mappedBy = "productId")
    private List<ProductMovementEntity> manyProductMovements;

}