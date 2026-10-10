package com.studentportal.controller;

import com.studentportal.model.Certification;
import com.studentportal.repository.CertificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {
    private final CertificationRepository repository;
    public CertificationController(CertificationRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Certification> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Certification one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certification not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Certification create(@RequestBody Certification item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Certification update(@PathVariable Long id, @RequestBody Certification item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certification not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certification not found");
        repository.deleteById(id);
    }
}
