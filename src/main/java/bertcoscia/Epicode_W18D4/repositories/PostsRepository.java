package bertcoscia.Epicode_W18D4.repositories;

import bertcoscia.Epicode_W18D4.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository extends JpaRepository<Post, UUID> {

    boolean existsByTitle(String title);
}
