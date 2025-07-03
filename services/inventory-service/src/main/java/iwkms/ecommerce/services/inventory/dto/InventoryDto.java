package iwkms.ecommerce.services.inventory.dto;

import java.util.UUID;

public record InventoryDto(UUID productId, int quantity) {}