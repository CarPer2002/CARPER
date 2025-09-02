package com.acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;

/**
 * Represents a physical address value object.
 * This value is immutable and shared across the bounded contexts.
 * @param street        the address street, it must not be null or blank
 * @param number        the address number, it must not be null or blank
 * @param city          the address city, it must not be null or blank
 * @param postalCode    the address postal code, it must not be null or blank
 * @param country       the address country, it must not be null or blank
 */
public record Address(String street, String number, String city, String postalCode, String country) {
    public Address {
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Street cannot be null or blank");
        if (Objects.isNull(number) || number.isBlank())
            throw new IllegalArgumentException("Number cannot be null or blank");
        if (Objects.isNull(city) || city.isBlank())
            throw new IllegalArgumentException("City cannot be null or blank");
        if (Objects.isNull(postalCode) || postalCode.isBlank())
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        if (Objects.isNull(country) || country.isBlank())
            throw new IllegalArgumentException("Country cannot be null or blank");
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object with the format: street number, city, postal code, country
     */

    public String toString() {
        return String.format("%s %s, %s, %s, %s", street, number, city, postalCode, country);
    }
}
