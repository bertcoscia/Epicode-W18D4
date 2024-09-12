package bertcoscia.Epicode_W18D3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate birthDate;
    @Column(name = "avatar_url")
    private String avatarUrl;

}
