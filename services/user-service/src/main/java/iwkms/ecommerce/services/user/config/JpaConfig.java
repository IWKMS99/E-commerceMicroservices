package iwkms.ecommerce.services.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "iwkms.ecommerce.services.user.repository")
@EntityScan(basePackages = "iwkms.ecommerce.services.user.entity")
public class JpaConfig {
}