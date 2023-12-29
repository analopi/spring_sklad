package Kukushkin.sklad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class ProductMovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime movementDate;
    private String movementType;
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private ProductEntity productId;

    public ProductMovementEntity() {
    }

    public ProductMovementEntity(LocalDateTime movementDate, String movementType, Integer count, ProductEntity productId) {
        this.movementDate = movementDate;
        this.movementType = movementType;
        this.count = count;
        this.productId = productId;
    }
}
