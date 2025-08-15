package com.ejemplo.categorias.controller;

import com.ejemplo.categorias.model.Categoria;
import com.ejemplo.categorias.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        Categoria creada = categoriaService.crear(categoria);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        return categoriaService.actualizar(id, categoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
