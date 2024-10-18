package org.example;

public record Event(String customerId, String workloadId, long timestamp, String eventType) {
}
