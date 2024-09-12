package bertcoscia.Epicode_W18D3.payloads;

import bertcoscia.Epicode_W18D3.entities.Author;
import bertcoscia.Epicode_W18D3.enums.PostCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PostsPayload {
    private PostCategory category;
    private String title;
    private String coverUrl;
    private String content;
    private int readingTime;
    private UUID authorId;
}
