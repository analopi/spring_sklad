package Kukushkin.sklad.service;

import Kukushkin.sklad.DTO.ProductMovementDTO;
import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.entity.ProductMovementEntity;
import Kukushkin.sklad.exception.UniversalException;
import Kukushkin.sklad.repository.ProductMovementRepo;
import Kukushkin.sklad.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductMovementService {
    @Autowired
    private ProductMovementRepo productMovementRepo;
    @Autowired
    private ProductRepo productRepo;


    public ProductMovementDTO createProductMovement( Map<String, Object> request) throws UniversalException {
        Integer count = (Integer) request.get("count");
        String movementType = (String) request.get("movementType");
        Integer productId = (Integer) request.get("productId");
        ProductEntity productDB = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Продукт не найден"));
        LocalDateTime date = LocalDateTime.now();

        if(movementType.equals("приход")){
            productDB.setCount(productDB.getCount() + count);
        } else if (movementType.equals("расход")){
            if(productDB.getCount()-count < 0){
                throw new UniversalException("Нельзя столько отнять у тебя будет меньше 0");
            }
            productDB.setCount(productDB.getCount() - count);
        }else {throw new UniversalException("Ты глупый?");}
        productDB = productRepo.save(productDB);

        ProductMovementEntity productMovement = new ProductMovementEntity(date, movementType, count, productDB);
        ProductMovementEntity productMovementDB = productMovementRepo.save(productMovement);
        return ProductMovementDTO.convertProductMovement(productMovementDB);
    }

    public List<ProductMovementDTO> getAllProductMovements() {
        return StreamSupport.stream(productMovementRepo.findAll().spliterator(), false)
                .map(ProductMovementDTO::convertProductMovement) // Использование вашего статического метода
                .collect(Collectors.toList());
    }

    public ProductMovementDTO getOneProductMovement(Integer id) throws UniversalException {
        ProductMovementEntity productMovement = productMovementRepo.findById(id)
                .orElseThrow(() -> new UniversalException("Движение продукта не найдено"));
        return ProductMovementDTO.convertProductMovement(productMovement); // Использование вашего статического метода
    }
}
