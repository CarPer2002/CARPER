package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidad que representa un item de una orden de venta.
 *
 * @author Open-Source Application Development Team
 * @version 1.0
 */

@Getter

public class SalesOrderItem {

    private final ProductId productId;
    @Setter private int quantity;
    @Setter private Money unitPrice;

    // Constructor:
    /** @param productId El {@Link ProductId} ID del producto.
     * @param quantity La cantidad del producto.
     * @param unitPrice El precio unitario del producto.
     */
    public SalesOrderItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {

        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if(unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than 0");
        }

        if(Objects.isNull(unitPrice.currency()) || Objects.isNull(unitPrice.currency().getCurrencyCode())) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /** Calcula el monto total del item (cantidad * precio unitario).
     * @return El monto total del item.
     */

    public Money calculateItemAmount(){
        return unitPrice.multiply(quantity);
    }

}
