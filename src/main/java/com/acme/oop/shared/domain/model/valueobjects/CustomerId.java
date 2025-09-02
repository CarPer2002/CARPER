package com.acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a customer ID value object.
 * @param value the customer ID, it must not be null
 */
public record CustomerId(UUID value) {

    /**
     * Creates a new CustomerId instance.
     * @param value the customer ID, it must not be null
     */
    public CustomerId {
        if (Objects.isNull(value))
            throw new IllegalArgumentException("Customer ID cannot be null");
    }

    /**
     * Creates a new CustomerId instance with a random UUID value.
     */
    public CustomerId() {
        this(UUID.randomUUID());
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object with the format: UUID value
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
