package Kukushkin.sklad.service;

import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public ProductEntity createProduct(ProductEntity product){
        productRepo.save(product);
        return product;
    }
    public List<ProductEntity> getProducts(){
        List<ProductEntity> products = StreamSupport.stream(productRepo.findAll().spliterator(), false).collect(Collectors.toList());
        return products;
    }
    public ProductEntity getProductById(Integer id){
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
    }

    public ProductEntity updateProduct(Integer id, ProductEntity productDetails){
        ProductEntity product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        return productRepo.save(product);
    }

}
