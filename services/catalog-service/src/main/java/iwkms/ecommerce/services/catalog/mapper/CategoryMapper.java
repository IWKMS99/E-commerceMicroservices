package iwkms.ecommerce.services.catalog.mapper;

import iwkms.ecommerce.services.catalog.dto.CategoryDto;
import iwkms.ecommerce.services.catalog.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}