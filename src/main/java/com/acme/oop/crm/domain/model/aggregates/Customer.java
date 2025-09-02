package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

/**
 * Represents a customer aggregate in the CRM bounded context.
 *
 * @author Open Source Application Development Team
 * @version 1.0.0
 */
@Getter
public class Customer {
    private final CustomerId id;
    private String name;
    private String email;
    private Address address;

    /**
     * Creates a new Customer instance.
     * @param name      the customer name, it must not be null or blank.
     * @param email     the customer email, it must not be null or blank.
     * @param address   the customer address, it must not be null.
     */
    public Customer(String name, String email, Address address) {
        if (Objects.isNull(name) || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (Objects.isNull(email) || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");
        if (Objects.isNull(address))
            throw new IllegalArgumentException("Address cannot be null");
        this.id = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the contact information of the customer.
     * @param email     the customer email, it must not be null or blank.
     * @param address   the customer address, it must not be null.
     */
    public void updateContactInfo(@NonNull String email, @NonNull Address address) {
        if (email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");
        this.email = email;
        this.address = address;
    }

    /**
     * Returns the contact information of the customer.
     * @return the contact information of the customer, in the format: name <email>, address.
     */
    public String getContactInfo() {
        return String.format("%s <%s>, %s", name, email, address);
    }
}
