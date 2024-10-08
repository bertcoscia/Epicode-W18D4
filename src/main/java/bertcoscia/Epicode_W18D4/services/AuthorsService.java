package bertcoscia.Epicode_W18D4.services;

import bertcoscia.Epicode_W18D4.entities.Author;
import bertcoscia.Epicode_W18D4.exceptions.BadRequestException;
import bertcoscia.Epicode_W18D4.exceptions.NotFoundException;
import bertcoscia.Epicode_W18D4.payloads.NewAuthorDTO;
import bertcoscia.Epicode_W18D4.repositories.AuthorsRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.cloudinary.utils.ObjectUtils.emptyMap;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Author save(NewAuthorDTO body) {
        if (this.authorsRepository.existsByEmail(body.email())) throw new BadRequestException("Email already used");
        Author newAuthor = new Author(body.name(), body.surname(), body.email(), body.birthDate());
        newAuthor.setAvatarUrl("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());
        return this.authorsRepository.save(newAuthor);
    }

    public List<Author> findAll() {
        return this.authorsRepository.findAll();
    }

    public Author findById(UUID id) {
        return this.authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author findByIdAndUpdate(UUID id, Author body) {
        Author found = this.findById(id);
        if (this.authorsRepository.existsByEmail(body.getEmail()) && !found.getId().equals(id)) throw new BadRequestException("Email already used");
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setBirthDate(body.getBirthDate());
        found.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
        return this.authorsRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        this.authorsRepository.delete(found);
    }

    public void uploadImage(MultipartFile file, UUID authorId) throws IOException {
        Author found = this.findById(authorId);
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarUrl(url);
        this.authorsRepository.save(found);
    }


}
