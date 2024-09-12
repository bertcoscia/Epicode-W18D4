package bertcoscia.Epicode_W18D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Name required")
        String name,
        @NotEmpty(message = "Surname required")
        String surname,
        @NotEmpty(message = "Email required")
        @Email(message = "Insert a valid email")
        String email,
        @NotEmpty(message = "Date of birth required")
        LocalDate birthDate
) {}
