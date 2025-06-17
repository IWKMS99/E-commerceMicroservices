package iwkms.ecommerce.libs.event;

import java.io.Serializable;
import java.util.UUID;

public record OrderItemMessage(
        UUID productId,
        int quantity
) implements Serializable {}