package Kukushkin.sklad.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date supplyDate;
    private Double price;

    @OneToMany(mappedBy = "supplyId")
    private List<SupplyProduct> manySupplyProduct;

    public SupplyEntity() {
    }

    public SupplyEntity(Date supplyDate, Double price) {
        this.supplyDate = supplyDate;
        this.price = price;
    }
}