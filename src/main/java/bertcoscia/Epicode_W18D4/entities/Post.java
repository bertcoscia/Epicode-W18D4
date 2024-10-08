package bertcoscia.Epicode_W18D4.entities;

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
    private String category;
    private String title;
    @Column(name = "cover_url")
    private String coverUrl;
    private String content;
    @Column(name = "reading_time")
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Post(String category, String title, String content, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }
}
