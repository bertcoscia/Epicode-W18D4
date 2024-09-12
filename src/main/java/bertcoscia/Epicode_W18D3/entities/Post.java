package bertcoscia.Epicode_W18D3.entities;

import bertcoscia.Epicode_W18D3.enums.PostCategory;
import bertcoscia.Epicode_W18D3.payloads.PostsPayload;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private PostCategory category;
    private String title;
    @Column(name = "cover_url")
    private String coverUrl;
    private String content;
    @Column(name = "reading_time")
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Post(PostCategory category, String title, String content, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }
}
