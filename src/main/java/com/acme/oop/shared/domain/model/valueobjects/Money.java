package com.acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents a monetary value object.
 * This value is immutable and shared across the bounded contexts.
 * @param amount    the monetary amount, it must not be null or negative
 * @param currency  the monetary currency, it must not be null
 */
public record Money(BigDecimal amount, Currency currency) {
    public Money {
        if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be null or negative");
        if (Objects.isNull(currency))
            throw new IllegalArgumentException("Currency cannot be null");
        if (amount.scale() > currency().getDefaultFractionDigits())
            throw new IllegalArgumentException("Amount scale cannot be greater than currency fraction digits");
    }

    /**
     * Returns a Money value object with zero amount and USD currency.
     * @return a Money instance with zero amount and USD currency
     */
    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }

    /**
     * Adds two money values.
     * @param other the money to add
     * @return a new Money instance with the sum of the amounts and the same currency
     */
    public Money add(Money other) {
        if (!currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add money with different currency");
        return new Money(amount.add(other.amount), currency);
    }

    /**
     * Multiplies the amount by the given multiplier.
     * @param multiplier the multiplier to multiply the amount by
     * @return a new Money instance with the amount multiplied by the multiplier and the same currency
     */
    public Money multiply(int multiplier) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)), currency);
    }
}
