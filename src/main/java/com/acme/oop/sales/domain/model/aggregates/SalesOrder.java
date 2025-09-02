package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * SalesOrder aggregate root representing a sales order in the system.
 */

public class SalesOrder {
    @Getter
    private final UUID id;
    @Getter
    private final CustomerId customerId;
    @Getter
    private LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    @Getter
    private Money totalAmount;

    /**
     * Constructor for creating a new SalesOrder.
     * @param customerId The {@Link CustomerId} ID of the customer placing the order.
     */

    public SalesOrder(@NonNull CustomerId customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }

    /**
     * Adds an item to the sales order.
     * @param productId The {@Link ProductId} ID of the product being added.
     * @param quantity The quantity of the product being added.
     * @param unitPrice The unit price of the product being added.
     */

    public void addItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice){
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than zero.");
        }

        SalesOrderItem newItem = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(newItem);
        totalAmount = calculateOrderTotal();

        // TODO: Calculate total amount
    }

    /**
     * Calculates the total amount of the sales order by summing up the amounts of all items.
     * @return The total amount of the sales order as a {@Link Money} object.
     */

    public Money calculateOrderTotal() {
        return this.items.stream().map(SalesOrderItem::calculateItemAmount).reduce(Money.zero(), Money::add);
    }

    /**
     * Sets the list of items in the sales order.
     * @return A list of {@Link SalesOrderItem} objects representing the items in the order.
     */

    public SalesOrder withOrderDate(@NonNull LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * Gets the total amount of the sales order as a string.
     * @return The total amount of the sales order as a string.
     */

    public String getOrderTotalAmountAsString() {
        return this.totalAmount + "" + this.totalAmount.currency().getCurrencyCode();
    }

}
