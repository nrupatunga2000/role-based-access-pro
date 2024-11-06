package com.example.rolebasedaccess.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/entity")
public class EntityController {

    @GetMapping("/{id}")
    public String getEntity(@PathVariable String id) {
        return "GET Request is successful";
    }
    
    @PostMapping("/{id}")
    public String postEntity(@PathVariable String id) {
        return "POST Request is successful";
    }

    @PutMapping("/{id}")
    public String putEntity(@PathVariable String id) {
        return "PUT Request is successful";
    }

    @DeleteMapping("/{id}")
    public String deleteEntity(@PathVariable String id) {
        return "DELETE Request is successful";
    }
}