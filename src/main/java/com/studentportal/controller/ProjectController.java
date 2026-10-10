package com.studentportal.controller;

import com.studentportal.model.Project;
import com.studentportal.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository repository;
    public ProjectController(ProjectRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Project> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Project one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        repository.deleteById(id);
    }
}
