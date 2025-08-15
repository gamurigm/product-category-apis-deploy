package com.ejemplo.categorias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "categorias-api");
        response.put("status", "running");
        // Endpoint público, sin login
        return response;
    }

}