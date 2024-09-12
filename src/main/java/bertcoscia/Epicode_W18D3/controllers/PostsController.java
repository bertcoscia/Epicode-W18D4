package bertcoscia.Epicode_W18D3.controllers;

import bertcoscia.Epicode_W18D3.entities.Post;
import bertcoscia.Epicode_W18D3.payloads.PostsPayload;
import bertcoscia.Epicode_W18D3.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Post save(@RequestBody PostsPayload payload) {
        Post body = this.postsService.createPost(payload);
        return this.postsService.save(body);
    }

    // PUT http://localhost:3001/blogPosts/{postId} + body
    @PutMapping("/{postId}")
    public Post findByIdAndUpdate(@PathVariable UUID postId, @RequestBody PostsPayload payload) {
        Post body = this.postsService.createPost(payload);
        return this.postsService.findByIdAndUpdate(postId, body);
    }

    // DELETE http://localhost:3001/blogPosts/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID postId) {
        this.postsService.findByIdAndDelete(postId);
    }


}
