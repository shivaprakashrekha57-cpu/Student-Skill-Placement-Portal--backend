package com.studentportal.controller;

import com.studentportal.model.Notice;
import com.studentportal.repository.NoticeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {
    private final NoticeRepository repository;
    public NoticeController(NoticeRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Notice> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Notice one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notice not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notice create(@RequestBody Notice item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Notice update(@PathVariable Long id, @RequestBody Notice item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notice not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notice not found");
        repository.deleteById(id);
    }
}
