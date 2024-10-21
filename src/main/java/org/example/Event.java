package org.example;

/**
 * Represents an event associated with a customer and workload.
 *
 * @param customerId the ID of the customer associated with the event
 * @param workloadId the ID of the workload associated with the event
 * @param timestamp  the timestamp of when the event occurred
 * @param eventType  the type of the event
 */
public record Event(String customerId, String workloadId, long timestamp, String eventType) {
}
