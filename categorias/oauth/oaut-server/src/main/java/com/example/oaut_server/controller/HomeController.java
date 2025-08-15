package com.example.oaut_server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        response.put("service", "oauth2-authorization-server");
        response.put("status", "running");
        response.put("port", "9000");

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            response.put("user_authenticated", true);
            response.put("username", auth.getName());
            response.put("message", "Usuario autenticado correctamente");
            response.put("next_step",
                    "El usuario debe iniciar OAuth flow desde la aplicación cliente en http://localhost:8089/oauth2/authorization/category");
        } else {
            response.put("user_authenticated", false);
            response.put("message", "No hay usuario autenticado");
            response.put("login_endpoint", "/login");
        }

        response.put("authorization_endpoint", "/oauth2/authorize");
        response.put("token_endpoint", "/oauth2/token");
        response.put("jwks_endpoint", "/.well-known/jwks.json");

        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "oauth2-authorization-server");
        return response;
    }

    @GetMapping("/debug")
    public Map<String, Object> debug() {
        Map<String, Object> response = new HashMap<>();
        response.put("endpoints", Map.of(
                "authorization", "/oauth2/authorize",
                "token", "/oauth2/token",
                "jwks", "/.well-known/jwks.json",
                "userinfo", "/userinfo"));
        response.put("client_config", Map.of(
                "client_id", "category",
                "redirect_uri", "http://localhost:8089/login/oauth2/code/category",
                "scopes", "openid,profile,read,write"));
        return response;
    }
}
