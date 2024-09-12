package bertcoscia.Epicode_W18D4.controllers;

import bertcoscia.Epicode_W18D4.entities.Post;
import bertcoscia.Epicode_W18D4.exceptions.BadRequestException;
import bertcoscia.Epicode_W18D4.payloads.NewPostDTO;
import bertcoscia.Epicode_W18D4.payloads.NewPostRespDTO;
import bertcoscia.Epicode_W18D4.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogPosts")
public class PostsController {
    @Autowired
    private PostsService postsService;

    // GET http://localhost:3001/blogPosts
    @GetMapping
    public Page<Post> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.postsService.findAll(page, size, sortBy);
    }

    // GET http://localhost:3001/blogPosts/{postId}
    @GetMapping("/{postId}")
    public Post findById(@PathVariable UUID postId) {
        return this.postsService.findById(postId);
    }

    // POST http://localhost:3001/blogPosts + body
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewPostRespDTO save(@RequestBody @Validated NewPostDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return new NewPostRespDTO(this.postsService.save(body).getId());
        }
    }

    // PUT http://localhost:3001/blogPosts/{postId} + body
    @PutMapping("/{postId}")
    public Post findByIdAndUpdate(@PathVariable UUID postId, @RequestBody Post body) {
        return this.postsService.findByIdAndUpdate(postId, body);
    }

    // DELETE http://localhost:3001/blogPosts/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID postId) {
        this.postsService.findByIdAndDelete(postId);
    }

    // POST http://localhost:3001/blogPosts/{postId}/cover
    @PostMapping("/{postId}/cover")
    public void uploadCover(@RequestParam("cover")MultipartFile image, @PathVariable UUID postId) throws IOException {
        this.postsService.uploadImage(image, postId);
    }

}
