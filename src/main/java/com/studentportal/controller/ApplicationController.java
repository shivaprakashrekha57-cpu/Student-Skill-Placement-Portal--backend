package com.studentportal.controller;

import com.studentportal.model.Application;
import com.studentportal.repository.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository repository;
    public ApplicationController(ApplicationRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Application> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Application one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Application create(@RequestBody Application item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Application update(@PathVariable Long id, @RequestBody Application item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found");
        repository.deleteById(id);
    }
}
