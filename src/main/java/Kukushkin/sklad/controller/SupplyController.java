package Kukushkin.sklad.controller;

import Kukushkin.sklad.entity.SupplyEntity;
import Kukushkin.sklad.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supply")
public class SupplyController {
    @Autowired
    private SupplyService supplyService;

    @PostMapping("/create")
    public ResponseEntity<?> createSupply(@RequestBody Map<String, Object> request) {
        try {
            Map<String, Object> response = supplyService.createSupply(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка: " + e);

        }

    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllSupplies() {
        List<Map<String, Object>> supplies = supplyService.getAllSupplies();
        return ResponseEntity.ok(supplies);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSupplyById(@PathVariable Integer id) {
        Map<String, Object> supply = supplyService.getOneSupply(id);
        return ResponseEntity.ok(supply);
    }

}
