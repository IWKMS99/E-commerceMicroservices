package iwkms.ecommerce.services.inventory.kafka;

import iwkms.ecommerce.services.inventory.service.InventoryService;
import iwkms.ecommerce.libs.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent from Kafka for orderId: {}", event.getOrderId());
        try {
            inventoryService.reserveStock(event.getItems());
            log.info("Stock successfully reserved for orderId: {}", event.getOrderId());
        } catch (Exception e) {
            log.error("Failed to reserve stock for orderId: {}. Reason: {}", event.getOrderId(), e.getMessage());
            // TODO: Реализовать логику Dead Letter Queue (DLQ) для обработки сбойных сообщений.
        }
    }
}