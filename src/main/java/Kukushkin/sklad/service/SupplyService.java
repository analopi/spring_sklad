package Kukushkin.sklad.service;

import Kukushkin.sklad.entity.ProductEntity;
import Kukushkin.sklad.entity.SupplyEntity;
import Kukushkin.sklad.entity.SupplyProduct;
import Kukushkin.sklad.repository.ProductRepo;
import Kukushkin.sklad.repository.SupplyProductRepo;
import Kukushkin.sklad.repository.SupplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SupplyService {
    @Autowired
    private SupplyRepo supplyRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SupplyProductRepo supplyProductRepo;


    public Map<String, Object> createSupply(Map<String, Object> request) throws ParseException {
        Double price = (Double) request.get("price");
        String dateString = (String) request.get("date");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatter.parse(dateString);

        // Получаем информацию о продуктах и извлекаем идентификаторы
        List<Map<String, Integer>> productsInfo = (List<Map<String, Integer>>) request.get("products");
        List<Integer> productIds = new ArrayList<>();
        for (Map<String, Integer> productInfo : productsInfo) {
            productIds.add(productInfo.get("id"));
        }

        // Загружаем продукты из репозитория
        List<ProductEntity> products = productRepo.findAllById(productIds);
        SupplyEntity supply = new SupplyEntity();
        supply.setSupplyDate(date);
        supply.setPrice(price);
        SupplyEntity supplyDB = supplyRepo.save(supply);

        List<Map<String, Object>> productsResult = new ArrayList<>();
        for (Map<String, Integer> productInfo : productsInfo) {
            ProductEntity product = products.stream()
                    .filter(p -> p.getId().equals(productInfo.get("id")))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            SupplyProduct supplyProduct = new SupplyProduct();
            supplyProduct.setCount(productInfo.get("count"));
            supplyProduct.setProductId(product);
            supplyProduct.setSupplyId(supplyDB);
            supplyProductRepo.save(supplyProduct);

            // Добавляем информацию о продукте в результат
            Map<String, Object> productResult = new HashMap<>();
            productResult.put("name", product.getName());
            productResult.put("count", productInfo.get("count"));
            productsResult.add(productResult);
        }

        // Создаем итоговый Map для возврата
        Map<String, Object> result = new HashMap<>();
        result.put("date", dateString);
        result.put("price", price);
        result.put("products", productsResult);

        return result;
    }

    public List<Map<String, Object>> getAllSupplies() {
        List<SupplyEntity> supplies = supplyRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (SupplyEntity supply : supplies) {
            Map<String, Object> supplyData = new HashMap<>();
            supplyData.put("date", new SimpleDateFormat("dd.MM.yyyy").format(supply.getSupplyDate()));
            supplyData.put("price", supply.getPrice());

            List<SupplyProduct> supplyProducts = supplyProductRepo.findBySupplyId(supply);
            List<Map<String, Object>> productsList = new ArrayList<>();

            for (SupplyProduct sp : supplyProducts) {
                Map<String, Object> productData = new HashMap<>();
                productData.put("name", sp.getProductId().getName());
                productData.put("count", sp.getCount());
                productsList.add(productData);
            }

            supplyData.put("products", productsList);
            result.add(supplyData);
        }

        return result;
    }

    public Map<String, Object> getOneSupply(Integer supplyId) throws NoSuchElementException {
        SupplyEntity supply = supplyRepo.findById(supplyId)
                .orElseThrow(() -> new NoSuchElementException("Supply not found"));

        Map<String, Object> supplyData = new HashMap<>();
        supplyData.put("date", new SimpleDateFormat("dd.MM.yyyy").format(supply.getSupplyDate()));
        supplyData.put("price", supply.getPrice());

        List<SupplyProduct> supplyProducts = supplyProductRepo.findBySupplyId(supply);
        List<Map<String, Object>> productsList = new ArrayList<>();

        for (SupplyProduct sp : supplyProducts) {
            Map<String, Object> productData = new HashMap<>();
            productData.put("name", sp.getProductId().getName());
            productData.put("count", sp.getCount());
            productsList.add(productData);
        }

        supplyData.put("products", productsList);
        return supplyData;
    }

}
