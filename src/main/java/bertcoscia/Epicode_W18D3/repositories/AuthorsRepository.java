package bertcoscia.Epicode_W18D3.repositories;

import bertcoscia.Epicode_W18D3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {

    boolean existsByEmail(String email);
}
