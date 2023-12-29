package Kukushkin.sklad.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SupplyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "supply_id")
    private SupplyEntity supplyId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;



}