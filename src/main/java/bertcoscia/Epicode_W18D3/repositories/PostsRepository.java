package bertcoscia.Epicode_W18D3.repositories;

import bertcoscia.Epicode_W18D3.entities.Post;
import bertcoscia.Epicode_W18D3.payloads.PostsPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<Post, UUID> {

    boolean existsByTitle(String title);

    Post save(PostsPayload payload);
}
