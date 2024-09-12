package bertcoscia.Epicode_W18D3.controllers;

import bertcoscia.Epicode_W18D3.entities.Author;
import bertcoscia.Epicode_W18D3.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private AuthorsService authorsService;

    // GET http://localhost:3001/authors
    @GetMapping
    public List<Author> findAll() {
        return this.authorsService.findAll();
    }

    // POST http://localhost:3001/authors + body
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author save(@RequestBody Author body) {
        return this.authorsService.save(body);
    }

    // GET http://localhost:3001/authors/{authorId}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return this.authorsService.findById(authorId);
    }

    // PUT http://localhost:3001/authors/{authorId} + body
    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody Author body) {
        return this.authorsService.findByIdAndUpdate(authorId, body);
    }

    // DELETE http://localhost:3001/authors/{authorId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID authorId) {
        this.authorsService.findByIdAndDelete(authorId);
    }
}
