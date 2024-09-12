package bertcoscia.Epicode_W18D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Name required")
        String name,
        @NotEmpty(message = "Surname required")
        String surname,
        @NotEmpty(message = "Email required")
        @Email(message = "Insert a valid email")
        String email,
        @NotNull(message = "Date of birth required") // NotEmpty si usa solo con numeri e stringhe
        LocalDate birthDate
) {}
