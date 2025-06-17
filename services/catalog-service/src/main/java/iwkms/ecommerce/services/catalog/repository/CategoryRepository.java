package iwkms.ecommerce.services.catalog.repository;

import iwkms.ecommerce.services.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name); 
}