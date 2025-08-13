package com.training.kafkaListener;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping; // Added
import org.springframework.web.bind.annotation.RestController; // Added

@Service
@RestController // Makes this class also handle HTTP requests
public class OrderUpdateConsumer {

    private final Cache<String, Boolean> processedEventCache;
    private String lastMessage = "No events yet"; // Stores last message for Postman

    public OrderUpdateConsumer(Cache<String, Boolean> processedEventCache) {
        this.processedEventCache = processedEventCache;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "orderUpdateKafkaListenerContainerFactory")
    public void consume(OrderUpdateEvent event) {
        String eventKey = event.getOrderId() + "_" + event.getNewStatus();

        if (processedEventCache.getIfPresent(eventKey) != null) {
            lastMessage = "Duplicate event skipped: " + eventKey;
            System.out.println(lastMessage);
            return;
        }

        lastMessage = "Processing event: " + event;
        System.out.println(lastMessage);
        processedEventCache.put(eventKey, true);
    }

    // Simple endpoint to fetch the last processed/duplicate message
    @GetMapping("/last-event")
    public String getLastMessage() {
        return lastMessage;
    }
}
