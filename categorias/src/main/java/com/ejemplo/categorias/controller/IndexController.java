package com.ejemplo.categorias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "categorias-api");
        response.put("status", "running");
        response.put("oauth_login", "/oauth2/authorization/category");
        return response;
    }

    @GetMapping("/authorized")
    public Map<String, Object> authorize(@AuthenticationPrincipal OAuth2User user) {
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("status", "authenticated");
            response.put("user", user.getName());
            response.put("authorities", user.getAuthorities());
            response.put("api_access", "/api/categorias");
        } else {
            response.put("status", "not authenticated");
            response.put("login_url", "/oauth2/authorization/category");
        }
        return response;
    }
}