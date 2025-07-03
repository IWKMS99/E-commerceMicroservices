package iwkms.ecommerce.services.inventory.controller;

import iwkms.ecommerce.libs.event.OrderCreatedEvent;
import iwkms.ecommerce.libs.event.OrderItemMessage;
import iwkms.ecommerce.services.inventory.dto.InventoryDto;
import iwkms.ecommerce.services.inventory.dto.StockUpdateDto;
import iwkms.ecommerce.services.inventory.entity.Inventory;
import iwkms.ecommerce.services.inventory.kafka.KafkaProducerService;
import iwkms.ecommerce.services.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InventoryDto> updateStock(@Valid @RequestBody StockUpdateDto stockUpdateDto) {
        Inventory updatedInventory = inventoryService.addStock(
                stockUpdateDto.productId(),
                stockUpdateDto.quantity()
        );
        return ResponseEntity.ok(new InventoryDto(updatedInventory.getProductId(), updatedInventory.getQuantity()));
    }

    @PostMapping("/test-kafka/{productId}/{quantity}")
    public ResponseEntity<String> testKafkaEvent(
            @PathVariable UUID productId,
            @PathVariable int quantity
    ) {
        OrderItemMessage item = new OrderItemMessage(productId, 5);
        OrderCreatedEvent event = new OrderCreatedEvent(
                this,
                UUID.randomUUID(),
                UUID.randomUUID(),
                Collections.singletonList(item)
        );

        kafkaProducerService.sendOrderCreatedEvent(event);

        return ResponseEntity.ok("Test event sent for product " + productId);
    }
}