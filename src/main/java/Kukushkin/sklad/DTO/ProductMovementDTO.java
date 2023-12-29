package Kukushkin.sklad.DTO;

import Kukushkin.sklad.entity.ProductMovementEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductMovementDTO {
    private LocalDateTime movementDate;
    private String movementType;
    private Integer count;

    public ProductMovementDTO() {
    }

    public ProductMovementDTO(ProductMovementEntity productMovementEntity) {
        this.movementDate = productMovementEntity.getMovementDate();
        this.movementType = productMovementEntity.getMovementType() ;
        this.count = productMovementEntity.getCount();
    }

    public static ProductMovementDTO convertProductMovement(ProductMovementEntity productMovementEntity){

        return new ProductMovementDTO(productMovementEntity);

    }
}

