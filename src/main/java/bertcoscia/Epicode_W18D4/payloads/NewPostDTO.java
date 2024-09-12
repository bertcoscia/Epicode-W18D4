package bertcoscia.Epicode_W18D4.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record NewPostDTO(
        @NotEmpty(message = "Category required")
        String category,
        @NotEmpty(message = "Title required")
        String title,
        @NotEmpty(message = "Content required")
        String content,
        @NotEmpty(message = "Reading time required")
                @Min(1)
                @Max(15)
        int readingTime,
        @NotEmpty(message = "Author id required")
        UUID authorId
) {}
