package bertcoscia.Epicode_W18D4.controllers;

import bertcoscia.Epicode_W18D4.entities.Author;
import bertcoscia.Epicode_W18D4.exceptions.BadRequestException;
import bertcoscia.Epicode_W18D4.payloads.NewAuthorDTO;
import bertcoscia.Epicode_W18D4.payloads.NewAuthorRespDTO;
import bertcoscia.Epicode_W18D4.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public NewAuthorRespDTO save(@RequestBody @Validated NewAuthorDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return new NewAuthorRespDTO(this.authorsService.save(body).getId());
        }
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
