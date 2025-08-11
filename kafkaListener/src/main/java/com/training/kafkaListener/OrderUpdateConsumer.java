package com.training.kafkaListener;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderUpdateConsumer {

    private final Cache<String, Boolean> processedEventCache;

    public OrderUpdateConsumer(Cache<String, Boolean> processedEventCache) {
        this.processedEventCache = processedEventCache;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "orderUpdateKafkaListenerContainerFactory")
    public void consume(OrderUpdateEvent event) {
        String eventKey = event.getOrderId() + "_" + event.getNewStatus();

        if (processedEventCache.getIfPresent(eventKey) != null) {
            System.out.println("Duplicate event skipped: " + eventKey);
            return;
        }

        // Simulate actual processing
        System.out.println("Processing event: " + event);

        // After successful processing
        processedEventCache.put(eventKey, true);
    }
}
