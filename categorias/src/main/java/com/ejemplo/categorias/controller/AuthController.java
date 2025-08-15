package com.ejemplo.categorias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "categorias-resource-server");
    }
}
