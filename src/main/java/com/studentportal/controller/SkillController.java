package com.studentportal.controller;

import com.studentportal.model.Skill;
import com.studentportal.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillRepository repository;
    public SkillController(SkillRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Skill> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Skill one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Skill create(@RequestBody Skill item) { return repository.save(item); }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill item) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found");
        repository.deleteById(id);
    }
}
