package iwkms.ecommerce.services.catalog.service;

import iwkms.ecommerce.services.catalog.dto.CreateProductDto;
import iwkms.ecommerce.services.catalog.dto.ProductDto;
import iwkms.ecommerce.services.catalog.entity.Category;
import iwkms.ecommerce.services.catalog.entity.Product;
import iwkms.ecommerce.services.catalog.mapper.ProductMapper;
import iwkms.ecommerce.services.catalog.repository.CategoryRepository;
import iwkms.ecommerce.services.catalog.repository.ProductRepository;
import iwkms.ecommerce.libs.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto findProductById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public List<ProductDto> findProductsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto createProduct(CreateProductDto createDto) {
        Category category = categoryRepository.findById(createDto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot create product. Category not found with id: " + createDto.categoryId()));

        Product product = new Product();
        product.setName(createDto.name());
        product.setDescription(createDto.description());
        product.setPrice(createDto.price());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }
}