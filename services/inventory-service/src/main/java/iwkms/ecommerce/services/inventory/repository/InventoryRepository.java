package iwkms.ecommerce.services.inventory.repository;

import iwkms.ecommerce.services.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
}