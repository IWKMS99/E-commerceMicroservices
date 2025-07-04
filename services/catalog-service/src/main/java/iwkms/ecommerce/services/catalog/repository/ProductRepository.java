package iwkms.ecommerce.services.catalog.repository;

import iwkms.ecommerce.services.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryId(Long categoryId);
}