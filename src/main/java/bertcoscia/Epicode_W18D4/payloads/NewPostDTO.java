package bertcoscia.Epicode_W18D4.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewPostDTO(
        @NotEmpty(message = "Category required")
        String category,
        @NotEmpty(message = "Title required")
        String title,
        @NotEmpty(message = "Content required")
        String content,
        @NotNull(message = "Reading time required")
        int readingTime,
        @NotNull(message = "Author id required")
        UUID authorId
) {}
