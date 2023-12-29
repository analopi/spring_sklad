package Kukushkin.sklad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("key1", 22);
        return "index";
    }

}
