package iwkms.ecommerce.services.catalog.mapper;

import iwkms.ecommerce.services.catalog.dto.ProductDto;
import iwkms.ecommerce.services.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDto toDto(Product product);
}