package iwkms.ecommerce.services.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "iwkms.ecommerce.services.userservice.repository",
//        "iwkms.shop.ecommerce.catalog.repository",
//        "iwkms.shop.ecommerce.order.repository",
//        "iwkms.shop.ecommerce.inventory.repository",
//        "iwkms.shop.ecommerce.payment.repository"
})
public class JpaConfig {
}