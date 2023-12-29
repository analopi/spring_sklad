package Kukushkin.sklad.controller;


import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody ProductEntity product) {
        try {

            return ResponseEntity.ok().body(productService.createProduct(product));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка: " + e);

        }
    }
    @GetMapping("/get")
    public ResponseEntity getProducts(){
        try {
            return ResponseEntity.ok().body(productService.getProducts());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка: " + e);

        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(productService.getProductById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody ProductEntity product) {
        try {
            return ResponseEntity.ok().body(productService.updateProduct(id, product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }



}
