package org.example;

public class Customer {
    private String customerId;
    private long consumption = 0;

    public Customer(String customerId, long consumption) {
        this.customerId = customerId;
        this.consumption = consumption;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getConsumption() {
        return consumption;
    }

    public void setConsumption(long consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", consumption=" + consumption +
                '}';
    }
}
