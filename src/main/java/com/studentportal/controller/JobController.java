package com.studentportal.controller;

import com.studentportal.model.Job;
import com.studentportal.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repository;
    public JobController(JobRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Job> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Job one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job create(@RequestBody Job item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Job update(@PathVariable Long id, @RequestBody Job item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        repository.deleteById(id);
    }
}
