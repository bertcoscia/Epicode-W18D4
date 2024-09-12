package bertcoscia.Epicode_W18D4.services;

import bertcoscia.Epicode_W18D4.entities.Author;
import bertcoscia.Epicode_W18D4.entities.Post;
import bertcoscia.Epicode_W18D4.exceptions.BadRequestException;
import bertcoscia.Epicode_W18D4.exceptions.NotFoundException;
import bertcoscia.Epicode_W18D4.payloads.NewPostDTO;
import bertcoscia.Epicode_W18D4.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private AuthorsService authorsService;

    public Post save(NewPostDTO body) {
        if (this.postsRepository.existsByTitle(body.title())) throw new BadRequestException("There is already a post with the title " + body.title());
        Author author = this.authorsService.findById(body.authorId());
        Post newPost = new Post(body.category(), body.title(), body.content(), body.readingTime(), author);
        newPost.setCoverUrl("https://picsum.photos/200/300");
        return this.postsRepository.save(newPost);
    }

    public Page<Post> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.postsRepository.findAll(pageable);
    }

    public Post findById(UUID id) {
        return this.postsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Post findByIdAndUpdate(UUID id, Post body) {
        Post found = this.findById(id);
        if (this.postsRepository.existsByTitle(body.getTitle()) && !found.getId().equals(id))throw new BadRequestException("There is already a post with the title " + body.getTitle());
        found.setCoverUrl(body.getCoverUrl());
        found.setAuthor(body.getAuthor());
        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        return this.postsRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Post found = this.findById(id);
        this.postsRepository.delete(found);
    }

}
