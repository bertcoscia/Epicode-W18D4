package bertcoscia.Epicode_W18D3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Could not find the record with id " + id);
    }
}
