package Kukushkin.sklad.controller;
import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.entity.ProductMovementEntity;
import Kukushkin.sklad.exception.UniversalException;
import Kukushkin.sklad.service.ProductMovementService;
import Kukushkin.sklad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/productmovement")
public class ProductMovementController {
    @Autowired
    private ProductMovementService productMovementService;

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody Map<String, Object> request) {
        try {
            return ResponseEntity.ok().body(productMovementService.createProductMovement(request));
        }
        catch (UniversalException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка: " + e);

        }

    }
    @GetMapping("/get")
    public ResponseEntity getAllProductMovements(){
        try {
            return ResponseEntity.ok().body(productMovementService.getAllProductMovements());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка: " + e);

        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getOneProductMovement(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(productMovementService.getOneProductMovement(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
